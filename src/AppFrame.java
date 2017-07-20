import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

public class AppFrame {
	
	JFrame frame1;

	JPanel panel1;
	JPanel panel2;
	JPanel panel3;

	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label8;
	JLabel label9;
	JLabel label10;
	JLabel label11;
	JLabel label12;

	JTextField field1;
	JTextField field2;
	JTextField field3;
	JTextField field4;
	JTextField field5;
	JTextField field6;
	JTextField field7;
	JTextField field8;

	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;

	JButton clear;

	JCheckBox box1;
	JCheckBox box2;
	JCheckBox box3;

	JFrame frame2;
	GraphPanel panel4;

	GridBagConstraints c;

	private long MergeSortTime;
	private long SelectionSortTime;
	private long InsertionSortTime;

	private boolean check1 = false;
	private boolean check2 = false;
	private boolean check3 = false;
	
	private int[] list;
	
	public void activate() {
		frame1 = new JFrame("Sorting Algorithms");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		label1 = new JLabel("Generating Chaotic Sequence by Logistic Growth");
		label2 = new JLabel("The initial population: P=");
		label3 = new JLabel("The number of generations: k=");
		label4 = new JLabel("The reproductive rate: r=");
		label5 = new JLabel("The carrying capacity: CC=");
		label6 = new JLabel("Output:");
		label7 = new JLabel("Sorted Sequence:");
		label8 = new JLabel("Time:");
		label9 = new JLabel("Sorted Sequence:");
		label10 = new JLabel("Time:");
		label11 = new JLabel("Sorted Sequence:");
		label12 = new JLabel("Time:");

		field1 = new JTextField(6);
		field2 = new JTextField(6);
		field3 = new JTextField(6);
		field4 = new JTextField(6);
		field5 = new JTextField(20);
		field6 = new JTextField(20);
		field7 = new JTextField(20);
		field8 = new JTextField(20);

		button1 = new JButton("Sort by Mergesort");
		button2 = new JButton("Sort by Selectin Sort");
		button3 = new JButton("Sort by Insertion Sort");
		button4 = new JButton("See a graph");
		button5 = new JButton("Draw a graph");
		clear = new JButton("Clear the list of data points");

		box1 = new JCheckBox("Add Mergesort to the graph");
		box2 = new JCheckBox("Add Selection sort to the graph");
		box3 = new JCheckBox("Add Insertion sort to the graph");

		frame1.getContentPane().add(BorderLayout.NORTH, panel1);
		frame1.getContentPane().add(BorderLayout.CENTER, panel2);
		frame1.getContentPane().add(BorderLayout.SOUTH, panel3);

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
		panelAddComponent(label6, panel1, 0, 5, 1);
		panelAddComponent(field5, panel1, 1, 5, 1);

		panel2.setLayout(new GridBagLayout());
		panelAddComponent(button1, panel2, 0, 0, 1);
		panelAddComponent(label8, panel2, 1, 0, 1);
		panelAddComponent(label7, panel2, 0, 1, 1);
		panelAddComponent(field6, panel2, 1, 1, 1);

		panelAddComponent(button2, panel2, 0, 2, 1);
		panelAddComponent(label10, panel2, 1, 2, 1);
		panelAddComponent(label9, panel2, 0, 3, 1);
		panelAddComponent(field7, panel2, 1, 3, 1);

		panelAddComponent(button3, panel2, 0, 4, 1);
		panelAddComponent(label12, panel2, 1, 4, 1);
		panelAddComponent(label11, panel2, 0, 5, 1);
		panelAddComponent(field8, panel2, 1, 5, 1);

		panel3.setLayout(new GridBagLayout());
		panelAddComponent(box1, panel3, 0, 0, 1);
		panelAddComponent(box2, panel3, 0, 1, 1);
		panelAddComponent(box3, panel3, 0, 2, 1);
		panelAddComponent(button4, panel3, 0, 3, 1);
		panelAddComponent(button5, panel3, 0, 4, 1);
		panelAddComponent(clear, panel3, 0, 5, 1);

		box1.addActionListener(new box1Listener());
		box2.addActionListener(new box2Listener());
		box3.addActionListener(new box3Listener());

		button1.addActionListener(new button1Listener());
		button2.addActionListener(new button2Listener());
		button3.addActionListener(new button3Listener());
		button4.addActionListener(new button4Listener());
		button5.addActionListener(new button5Listener());
		clear.addActionListener(new clearListener());

		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(500, 500);
		frame1.setVisible(true);
		
		go2();
	}
	
	public void go2() {
		frame2 = new JFrame("Comparison Graph");
		panel4 = new GraphPanel();

		frame2.getContentPane().add(BorderLayout.CENTER, panel4);

		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setSize(700, 700);
		frame2.setVisible(false);
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
		// The number of generations including the initial one
		int k = Integer.parseInt(field2.getText()) + 1;
		// Carrying capacity
		int cc = Integer.parseInt(field4.getText());
		// Reproductive rate
		double r = Double.parseDouble(field3.getText());

		list = new int[k];
		double[] auxiliaryList = new double[k];
		auxiliaryList[0] = p;// Initial population

		for (int i = 0; i < k - 1; i++) {
			auxiliaryList[i + 1] = r * auxiliaryList[i] * (1 - auxiliaryList[i] / cc);
		}
		for (int i = 0; i < k; i++) {
			list[i] = (int) Math.floor(auxiliaryList[i]);
		}
	}

	class box1Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (box1.isSelected()) {
				check1 = true;
			} else {
				check1 = false;
			}
		}
	}

	class box2Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (box2.isSelected()) {
				check2 = true;
			} else {
				check2 = false;
			}
		}
	}

	class box3Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (box3.isSelected()) {
				check3 = true;
			} else {
				check3 = false;
			}
		}
	}

	class button1Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				makeList();
				field5.setText(Arrays.toString(list));
				long start = System.nanoTime();
				mergeSort(list);
				long end = System.nanoTime();
				MergeSortTime = end - start;
				field6.setText(Arrays.toString(list));
				label8.setText("Time: " + MergeSortTime + " ns");
				if (check1 == true) {
					panel4.sampleX1.add(list.length);
					panel4.sampleY1.add(MergeSortTime);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "There is an error.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class button2Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				makeList();
				field5.setText(Arrays.toString(list));
				long start = System.nanoTime();
				selectionSort(list);
				long end = System.nanoTime();
				SelectionSortTime = end - start;
				field7.setText(Arrays.toString(list));
				label10.setText("Time: " + SelectionSortTime + " ns");
				if (check2 == true) {
					panel4.sampleX2.add(list.length);
					panel4.sampleY2.add(SelectionSortTime);
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "There is an error.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class button3Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				makeList();
				field5.setText(Arrays.toString(list));
				long start = System.nanoTime();
				insertionSort(list);
				long end = System.nanoTime();
				InsertionSortTime = end - start;
				field8.setText(Arrays.toString(list));
				label12.setText("Time: " + InsertionSortTime + " ns");
				if (check3 == true) {
					panel4.sampleX3.add(list.length);
					panel4.sampleY3.add(InsertionSortTime);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "There is an error.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class button4Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				panel4.repaint();
				frame2.setVisible(true);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "There is an error.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class button5Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				clearList();
				makeList();
				automaticSorting();
				panel4.repaint();
				frame2.setVisible(true);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "There is an error.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class clearListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			clearList();
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
		for (int i = 0; i < size1; i++) {
			left[i] = array[i];
		}
		return left;
	}

	// Returns the second half of the given array.
	private int[] rightHalf(int[] array) {
		int size1 = array.length / 2;
		int size2 = array.length - size1;
		int[] right = new int[size2];
		for (int i = 0; i < size2; i++) {
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

		for (int i = 0; i < result.length; i++) {
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
		for (int i = 0; i < n.length; i++) {
			min = n[i];
			index = i;
			for (int e = i + 1; e < n.length; e++) {
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
		for (int i = 0; i < n; i++) {
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
			panel4.sampleX3.add(length);
			panel4.sampleY3.add(end - start);

			start = System.nanoTime();
			mergeSort(setList(length));
			end = System.nanoTime();
			panel4.sampleX1.add(length);
			panel4.sampleY1.add(end - start);

			start = System.nanoTime();
			selectionSort(setList(length));
			end = System.nanoTime();
			panel4.sampleX2.add(length);
			panel4.sampleY2.add(end - start);
		}
	}

	private void clearList() {
		panel4.sampleX1.clear();
		panel4.sampleX2.clear();
		panel4.sampleX3.clear();
		panel4.sampleY1.clear();
		panel4.sampleY2.clear();
		panel4.sampleY3.clear();
	}
}
