/**
 * Title: Merge Sort
 * @author Pham.LongThanh
 * Date: November 26, 2013
 */

//Copied from: http://www.buildingjavaprograms.com/code-files/3ed/ch13/MergeSort.java
// This program implements the merge sort algorithm for
// arrays of integers.

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MergeSort 
{
	JFrame frame;
	JPanel panel1;
	JLabel label1;
	JTextField field1;
	JTextField result;
	JButton button1;
	
	private GridBagConstraints c;
	private int[] list;
	
    public static void main(String[] args) 
    {
    	MergeSort merge = new MergeSort();
    	merge.go();
    }
    
    public void go()
    {
    	frame = new JFrame("Merge Sort");
    	panel1 = new JPanel();
    	label1 = new JLabel("Input: ");
    	field1 = new JTextField(25);
    	result = new JTextField(25);
    	button1 = new JButton("Sort");
    	
    	c = new GridBagConstraints();
    	
    	frame.getContentPane().add(BorderLayout.CENTER, panel1);
    	panel1.setLayout(new GridBagLayout());
    	panelAddComponent(label1,0,0);
    	panelAddComponent(field1,1,0);
    	panelAddComponent(button1,1,1);
    	panelAddComponent(result,1,2);
    	
    	button1.addActionListener(new button1Listener());
    	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	    frame.setSize(400, 200);
  	    frame.setVisible(true);
    }
    
    public void panelAddComponent(Component component,int x, int y)
    {
  	  c.fill = GridBagConstraints.BOTH;
  	  c.gridx = x;
  	  c.gridy = y;
  	  c.gridwidth = 1;
  	  panel1.add(component,c);
    }
    
    class button1Listener implements ActionListener
    {
    	public void actionPerformed(ActionEvent event)
    	{
    		try
    		{
            readInput();
    		mergeSort(list);
    		result.setText("Result: "+Arrays.toString(list));
    		}
    		catch(Exception ex)
    		{
    			JOptionPane.showMessageDialog(null,"Please submit a correct input.","Error Message", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    
    public void readInput()
    {
		int index1 = 0;
		int index2 = 0;
		boolean number = false;
		int length = 1;
		String input = field1.getText();
		int length2 = input.length();
		for(int i=0;i<length2;i++)
		{
			if(input.charAt(i)==',')
			{
				length++;
			}
		}
		System.out.println(length);
		list = new int[length];
		for(int i=0;i<length2;i++)
		{
			if(number==false && isNumber(input.charAt(i)))
			{
				number = true;
				index1 = i;
			}
			if(i<length2-1)
			{
				if(number==true && !isNumber(input.charAt(i+1)))
				{
				number = false;
				list[index2] = Integer.parseInt(input.substring(index1,i+1));
				index2++;
				}
			}
			else
			{
				list[index2] = Integer.parseInt(input.substring(index1,i+1));
			}
		}
    }
    
    public boolean isNumber(char a)
    {
    	boolean result = false;
    	switch(a)
    	{
    	case '0':
    		result = true;
    	case '1':
    		result = true;
    	case '2':
    		result = true;
    	case '3':
    		result = true;
    	case '4':
    		result = true;
    	case '5':
    		result = true;
    	case '6':
    		result = true;
    	case '7':
    		result = true;
    	case '8':
    		result = true;
    	case '9':
    		result = true;
    	}
    	return result;
    }

    // Places the elements of the given array into sorted order
    // using the merge sort algorithm.
    // post: array is in sorted (nondecreasing) order
    public static void mergeSort(int[] array) 
    {
        if (array.length > 1) 
        {
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
    public static int[] leftHalf(int[] array) 
    {
        int size1 = array.length / 2;
        int[] left = new int[size1];
        for (int i = 0; i < size1; i++) 
        {
            left[i] = array[i];
        }
        return left;
    }
    
    // Returns the second half of the given array.
    public static int[] rightHalf(int[] array) 
    {
        int size1 = array.length / 2;
        int size2 = array.length - size1;
        int[] right = new int[size2];
        for (int i = 0; i < size2; i++) 
        {
            right[i] = array[i + size1];
        }
        return right;
    }
    
    // Merges the given left and right arrays into the given 
    // result array.  Second, working version.
    // pre : result is empty; left/right are sorted
    // post: result contains result of merging sorted lists;
    public static void merge(int[] result, 
                             int[] left, int[] right) 
    {
        int i1 = 0;   // index into left array
        int i2 = 0;   // index into right array
        
        for (int i = 0; i < result.length; i++) 
        {
            if (i2 >= right.length || (i1 < left.length && 
                    left[i1] <= right[i2])) 
            {
                result[i] = left[i1];    // take from left
                i1++;
            } 
            else 
            {
                result[i] = right[i2];   // take from right
                i2++;
            }
        }
    }
 }