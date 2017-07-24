import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

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

	GridBagConstraints c;
	
	private GraphFrame graph = new GraphFrame();
	private int[] populations;
	
	private Mergesort mergesort = new Mergesort();
	private SelectionSort selectionsort = new SelectionSort();
	private InsertionSort insertionsort = new InsertionSort();
	
	public void activate() {
		frame1 = new JFrame("Sorting Algorithms");
		panel1 = new JPanel();
		panel2 = new JPanel();
		label1 = new JLabel("Generating Chaotic Sequence by Logistic Growth");
		label2 = new JLabel("The initial population: P=");
		label3 = new JLabel("The number of generations: k=");
		label4 = new JLabel("The reproductive rate: r=");
		label5 = new JLabel("The carrying capacity: CC=");
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

		c = new GridBagConstraints();
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
		c.fill = GridBagConstraints.BOTH;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		panel.add(component, c);
	}
	
	private void generatePopulations() {
		// Initial population
		int p = Integer.parseInt(field1.getText());
		// The number of generations
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

	class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				generatePopulations();
				automaticSorting();
				graph.activate();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "There is an error.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private int[] setList(int n) {
		int[] result = new int[n];
		for (int i = 0; i != n; i++) {
			result[i] = populations[i];
		}
		return result;
	}

	private void automaticSorting() {
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

		for (int i = 1; i <= maxlength; i++) {
			length = (int) Math.round(interval * i);

			start = System.nanoTime();
			mergesort.sort(setList(length));
			end = System.nanoTime();
			merge[i-1] = new DataPoint(length, end - start);

			start = System.nanoTime();
			selectionsort.sort(setList(length));
			end = System.nanoTime();
			selection[i-1] = new DataPoint(length, end - start);
			
			start = System.nanoTime();
			insertionsort.sort(setList(length));
			end = System.nanoTime();
			insertion[i-1] = new DataPoint(length, end - start);
		}
		graph.setData(merge, selection, insertion);
	}
}
