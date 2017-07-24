/** 
 * This class implements selection sort. 
 * */
public class SelectionSort implements Sorting {

	public void sort(int[] data) {
		int auxiliaryMemory = 0;
		int min = 0;
		int index = 0;
		int len = data.length;
		for (int i = 0; i != len; i++) {
			min = data[i];
			index = i;
			for (int j = i + 1; j != len; j++) {
				if (data[j] < min) {
					min = data[j];
					index = j;
				}
			}
			if (index != i) {
				auxiliaryMemory = data[i];
				data[i] = min;
				data[index] = auxiliaryMemory;
			}
		}
	}
}
