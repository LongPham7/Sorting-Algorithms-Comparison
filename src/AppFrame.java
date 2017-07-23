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

	JButton button4;

	GridBagConstraints c;
	
	private GraphFrame graph = new GraphFrame();
	
	private int[] list;
	
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

		button4 = new JButton("Generate numbers, sort them, and draw a graph");

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
		panelAddComponent(button4, panel2, 0, 0, 2);
		panelAddComponent(label6, panel2, 0, 1, 1);
		panelAddComponent(field5, panel2, 1, 1, 1);
		panelAddComponent(label7, panel2, 0, 2, 1);
		panelAddComponent(field6, panel2, 1, 2, 1);		

		button4.addActionListener(new button4Listener());

		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(450, 300);
		frame1.setVisible(true);
		
		graph.activate();
	}
	
	private void panelAddComponent(Component component, JPanel panel, int x, int y, int width) {
		c.fill = GridBagConstraints.BOTH;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		panel.add(component, c);
	}
	
	private void makeList() {
		// Initial population
		int p = Integer.parseInt(field1.getText());
		// The number of generations
		int k = Integer.parseInt(field2.getText());
		// Carrying capacity
		int cc = Integer.parseInt(field4.getText());
		// Reproductive rate
		double r = Double.parseDouble(field3.getText());

		list = new int[k];
		double[] auxiliaryList = new double[k];
		auxiliaryList[0] = p;// Initial population

		for (int i = 0; i != k - 1; i++) {
			auxiliaryList[i + 1] = r * auxiliaryList[i] * (1 - auxiliaryList[i] / cc);
		}
		for (int i = 0; i != k; i++) {
			list[i] = (int) Math.floor(auxiliaryList[i]);
		}
	}

	class button4Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				graph.clearList();
				makeList();
				automaticSorting();
				graph.frame.setVisible(true);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "There is an error.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	// Places the elements of the given array into sorted order
	// using the merge sort algorithm.
	// post: array is in sorted (nondecreasing) order
	private void mergeSort(int[] array) {
		if (array.length > 1) {
			// split array into two halves
			int[] left = leftHalf(array);
			int[] right = rightHalf(array);

			// recursively sort the two halves
			mergeSort(left);
			mergeSort(right);

			// merge the sorted halves into a sorted whole
			merge(array, left, right);
		}
	}

	// Returns the first half of the given array.
	private int[] leftHalf(int[] array) {
		int size1 = array.length / 2;
		int[] left = new int[size1];
		for (int i = 0; i != size1; i++) {
			left[i] = array[i];
		}
		return left;
	}

	// Returns the second half of the given array.
	private int[] rightHalf(int[] array) {
		int size1 = array.length / 2;
		int size2 = array.length - size1;
		int[] right = new int[size2];
		for (int i = 0; i != size2; i++) {
			right[i] = array[i + size1];
		}
		return right;
	}

	// Merges the given left and right arrays into the given
	// result array. Second, working version.
	// pre : result is empty; left/right are sorted
	// post: result contains result of merging sorted lists;
	private void merge(int[] result, int[] left, int[] right) {
		int i1 = 0; // index into left array
		int i2 = 0; // index into right array

		for (int i = 0; i != result.length; i++) {
			if (i2 >= right.length || (i1 < left.length && left[i1] <= right[i2])) {
				result[i] = left[i1]; // take from left
				i1++;
			} else {
				result[i] = right[i2]; // take from right
				i2++;
			}
		}
	}

	private void selectionSort(int[] n) {
		int auxiliaryMemory = 0;
		int min = 0;
		int index = 0;
		for (int i = 0; i != n.length; i++) {
			min = n[i];
			index = i;
			for (int e = i + 1; e != n.length; e++) {
				if (n[e] < min) {
					min = n[e];
					index = e;
				}
			}
			if (index != i) {
				auxiliaryMemory = n[i];
				n[i] = min;
				n[index] = auxiliaryMemory;
			}

		}
	}

	private void insertionSort(int[] n) {
		int memory = 0;
		for (int i = 1; i < n.length; i++) {
			memory = n[i];
			for (int e = i - 1; e >= 0; e--) {
				if (n[e] > memory) {
					n[e + 1] = n[e];
					if (e == 0) {
						n[0] = memory;
					}
				} else {
					n[e + 1] = memory;
					break;
				}
			}
		}
	}

	private int[] setList(int n) {
		int[] result = new int[n];
		for (int i = 0; i != n; i++) {
			result[i] = list[i];
		}
		return result;
	}

	private void automaticSorting() {
		double interval = 1.0;
		long start;
		long end;
		int length = 0;
		int maxlength = 100;
		if (list.length > 100) {
			interval = (double) list.length / 100;
		} else {
			maxlength = list.length;
		}

		for (int i = 1; i <= maxlength; i++) {
			length = (int) Math.round(interval * i);

			start = System.nanoTime();
			insertionSort(setList(length));
			end = System.nanoTime();
			graph.addX3(length);
			graph.addY3(end - start);

			start = System.nanoTime();
			mergeSort(setList(length));
			end = System.nanoTime();
			graph.addX1(length);
			graph.addY1(end - start);

			start = System.nanoTime();
			selectionSort(setList(length));
			end = System.nanoTime();
			graph.addX2(length);
			graph.addY2(end - start);
		}
	}
}
