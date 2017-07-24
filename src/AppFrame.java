import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

/**
 * This class corresponds to a view in the MVC architecture, creating the main
 * frame where users can interact with the application. The users first input
 * parameters necessary for the discrete logistic growth model to generate an
 * unsorted sequence of integers. They can then click a button to sort the
 * sequence using three sorting algorithms: mergesort, selection sort, and
 * insertion sort. These algorithms are applied to subsequences of varying
 * length, and their execution time is measured. Finally, a graph comparing
 * performance of the three algorithms with respect to the running time is
 * displayed to the users.
 */
public class AppFrame {

	JFrame frame1;

	JPanel panel1;
	JPanel panel2;

	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;

	JTextField field1;
	JTextField field2;
	JTextField field3;
	JTextField field4;
	JTextField field5;
	JTextField field6;

	JButton button;

	private GraphFrame graph = new GraphFrame();
	private int[] populations;

	private Mergesort mergesort = new Mergesort();
	private SelectionSort selectionsort = new SelectionSort();
	private InsertionSort insertionsort = new InsertionSort();

	// Creates GUI components for the main frame.
	public void activate() {
		frame1 = new JFrame("Sorting Algorithms");
		panel1 = new JPanel();
		panel2 = new JPanel();
		label1 = new JLabel("Generating integers by discrete logistic growth");
		label2 = new JLabel("Initial population: P=");
		label3 = new JLabel("Number of generations: k=");
		label4 = new JLabel("Reproductive rate: r=");
		label5 = new JLabel("Carrying capacity: CC=");
		label6 = new JLabel("Unsorted sequence:");
		label7 = new JLabel("Sorted sequence:");

		field1 = new JTextField(5);
		field2 = new JTextField(5);
		field3 = new JTextField(5);
		field4 = new JTextField(5);
		field5 = new JTextField(20);
		field6 = new JTextField(20);

		button = new JButton("Generate numbers, sort them, and draw a graph");

		frame1.getContentPane().add(BorderLayout.NORTH, panel1);
		frame1.getContentPane().add(BorderLayout.CENTER, panel2);

		panel1.setLayout(new GridBagLayout());
		panelAddComponent(label1, panel1, 0, 0, 2);
		panelAddComponent(label2, panel1, 0, 1, 1);
		panelAddComponent(field1, panel1, 1, 1, 1);
		panelAddComponent(label3, panel1, 0, 2, 1);
		panelAddComponent(field2, panel1, 1, 2, 1);
		panelAddComponent(label4, panel1, 0, 3, 1);
		panelAddComponent(field3, panel1, 1, 3, 1);
		panelAddComponent(label5, panel1, 0, 4, 1);
		panelAddComponent(field4, panel1, 1, 4, 1);

		panel2.setLayout(new GridBagLayout());
		panelAddComponent(button, panel2, 0, 0, 2);
		panelAddComponent(label6, panel2, 0, 1, 1);
		panelAddComponent(field5, panel2, 1, 1, 1);
		panelAddComponent(label7, panel2, 0, 2, 1);
		panelAddComponent(field6, panel2, 1, 2, 1);

		button.addActionListener(new buttonListener());

		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(450, 300);
		frame1.setVisible(true);
	}

	private void panelAddComponent(Component component, JPanel panel, int x, int y, int width) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		panel.add(component, c);
	}

	// Generates an unsorted sequence of populations using the discrete
	// logistic model with the user-defined parameters.
	private void generatePopulations() {
		// Initial population
		int p = Integer.parseInt(field1.getText());
		// Number of generations
		int k = Integer.parseInt(field2.getText());
		// Carrying capacity
		int cc = Integer.parseInt(field4.getText());
		// Reproductive rate
		double r = Double.parseDouble(field3.getText());

		populations = new int[k];
		double[] auxiliaryList = new double[k];
		auxiliaryList[0] = p;// Initial population

		for (int i = 0; i != k - 1; i++) {
			auxiliaryList[i + 1] = r * auxiliaryList[i] * (1 - auxiliaryList[i] / cc);
		}
		for (int i = 0; i != k; i++) {
			populations[i] = (int) Math.floor(auxiliaryList[i]);
		}
	}

	// Action listener for a button to sort an input sequence and to display a graph
	class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				generatePopulations();
				displaySequences();
				measureTime();
				graph.activate();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Displays an unsorted and a sorted sequences.
	private void displaySequences() {
		int[] copy = copyList(populations.length);
		field5.setText(Arrays.toString(copy));
		mergesort.sort(copy);
		field6.setText(Arrays.toString(copy));
	}

	// Creates a new copy of the unsorted input sequence.
	private int[] copyList(int len) {
		int[] result = new int[len];
		System.arraycopy(populations, 0, result, 0, len);
		return result;
	}

	// Measures running time of sorting and sends the data to an object of
	// GraphFrame.
	private void measureTime() {
		double interval = 1.0;
		long start;
		long end;
		int length = 0;
		int maxlength = 100;

		if (populations.length > 100) {
			interval = (double) populations.length / 100;
		} else {
			maxlength = populations.length;
		}

		DataPoint[] merge = new DataPoint[maxlength];
		DataPoint[] selection = new DataPoint[maxlength];
		DataPoint[] insertion = new DataPoint[maxlength];

		for (int i = 0; i != maxlength; i++) {
			length = (int) Math.round(interval * (i + 1));

			start = System.nanoTime();
			mergesort.sort(copyList(length));
			end = System.nanoTime();
			merge[i] = new DataPoint(length, end - start);

			start = System.nanoTime();
			selectionsort.sort(copyList(length));
			end = System.nanoTime();
			selection[i] = new DataPoint(length, end - start);

			start = System.nanoTime();
			insertionsort.sort(copyList(length));
			end = System.nanoTime();
			insertion[i] = new DataPoint(length, end - start);
		}
		graph.setData(merge, selection, insertion);
	}
}
