/**
 * This class implements mergesort.
 */
public class Mergesort implements Sorting {

	public void sort(int[] data) {
		mergesort(data, 0, data.length);
	}

	private void mergesort(int[] data, int start, int end) {
		if (end - start > 1) {
			int m = (start + end) / 2;
			mergesort(data, start, m);
			mergesort(data, m, end);
			merge(data, start, m, end);
		}
	}

	private void merge(int[] data, int start, int middle, int end) {
		int i = 0;
		int j = middle;
		int len = end - start;
		int[] result = new int[len];
		for (int k = 0; k != len; k++) {
			if (j == end || (i != middle && data[i] <= data[j])) {
				result[k] = data[i];
				i++;
			} else {
				result[k] = data[j];
				j++;
			}
		}
		System.arraycopy(result, 0, data, start, len);
	}
}
