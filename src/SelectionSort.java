
public class SelectionSort implements Sorting {

	public void sort(int[] data) {
		int auxiliaryMemory = 0;
		int min = 0;
		int index = 0;
		int len = data.length;
		for (int i = 0; i != len; i++) {
			min = data[i];
			index = i;
			for (int e = i + 1; e != len; e++) {
				if (data[e] < min) {
					min = data[e];
					index = e;
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
