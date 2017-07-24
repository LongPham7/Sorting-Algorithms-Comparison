/**
 * This class represents a data point to be displayed on a graph. It stores the
 * size of a sequence of integers and running time to sort it.
 */
public class DataPoint {

	private int size;
	private long time;

	public DataPoint(int size, long time) {
		this.size = size;
		this.time = time;
	}

	public int getSize() {
		return size;
	}

	public long getTime() {
		return time;
	}
}
