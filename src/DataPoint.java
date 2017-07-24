
public class DataPoint {
	
	private int size;
	private long time;

	public DataPoint(int size, long time) {
		this.size=  size;
		this.time = time;
	}
	
	public int getSize() {
		return size;
	}
	
	public long getTime() {
		return time;
	}
}
