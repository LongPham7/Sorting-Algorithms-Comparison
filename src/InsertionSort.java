
public class InsertionSort implements Sorting {
	
	public void sort(int[] data) {
		int memory = 0;
		for (int i = 1; i < data.length; i++) {
			memory = data[i];
			for (int e = i - 1; e >= 0; e--) {
				if (data[e] > memory) {
					data[e + 1] = data[e];
					if (e == 0) {
						data[0] = memory;
					}
				} else {
					data[e + 1] = memory;
					break;
				}
			}
		}
	}
}
