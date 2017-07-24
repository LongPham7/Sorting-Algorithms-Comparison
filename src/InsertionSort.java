/**
 * This class implements insertion sort.
 */
public class InsertionSort implements Sorting {

	public void sort(int[] data) {
		int memory = 0;
		for (int i = 1; i < data.length; i++) {
			memory = data[i];
			for (int j = i - 1; j >= 0; j--) {
				if (data[j] > memory) {
					data[j + 1] = data[j];
					if (j == 0) {
						data[0] = memory;
					}
				} else {
					data[j + 1] = memory;
					break;
				}
			}
		}
	}
}
