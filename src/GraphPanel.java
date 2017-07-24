import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {

	// Default serial version UID
	private static final long serialVersionUID = 1L;

	private DataPoint[] mergesortData;
	private DataPoint[] selectionsortData;
	private DataPoint[] insertionsortData;

	public void setData(DataPoint[] merge, DataPoint[] selection, DataPoint[] insertion) {
		mergesortData = merge;
		selectionsortData = selection;
		insertionsortData = insertion;
	}

	@Override
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);

		Graphics2D g = (Graphics2D) g1;

		// Optimal distance from the left edge of a window to the left edge of a
		// graph
		final int WIDTH_HORIZONTAL = 115 + 5 * (Integer.toString(findYInterval()).length());
		// Interval of an abscissa
		int intervalX = findXInterval();
		// Interval of an ordinate
		int intervalY = findYInterval();

		// Draw the abscissa and ordinate
		g.setColor(Color.black);
		g.drawLine(WIDTH_HORIZONTAL, 550, WIDTH_HORIZONTAL + 500, 550);
		g.drawLine(WIDTH_HORIZONTAL, 550, WIDTH_HORIZONTAL, 50);

		// Draw scale bars for the abscissa.
		for (int i = 1; i != 11; i++) {
			g.drawLine(WIDTH_HORIZONTAL + 50 * i, 550, WIDTH_HORIZONTAL + 50 * i, 555);
		}

		// Draw scale bar for the ordinate.
		for (int i = 1; i != 11; i++) {
			g.drawLine(WIDTH_HORIZONTAL - 5, 550 - 50 * i, WIDTH_HORIZONTAL, 550 - 50 * i);
		}

		// Label numbers for the abscissa.
		for (int i = 0; i != 11; i++) {
			String n = Integer.toString(intervalX * i);
			g.drawString(n, WIDTH_HORIZONTAL - 5 + 50 * i, 575);
		}
		// Label numbers for the ordinate.
		for (int i = 0; i != 11; i++) {
			String n = Integer.toString(intervalY * i);
			g.drawString(n, 90, 555 - 50 * i);
		}

		g.setColor(Color.blue);
		String n = "blue: Mergesort";
		g.drawString(n, WIDTH_HORIZONTAL + 20, 600);
		g.setColor(Color.red);
		n = "red: Selection sort";
		g.drawString(n, WIDTH_HORIZONTAL + 20, 620);
		g.setColor(Color.green);
		n = "green: Insertion sort";
		g.drawString(n, WIDTH_HORIZONTAL + 20, 640);

		// Plot data points for mergesort.
		for (int i = 0; i != mergesortData.length; i++) {
			g.setColor(Color.blue);
			// Find x-coordinate.
			int x = WIDTH_HORIZONTAL + (50 * mergesortData[i].getSize() / intervalX);
			// Find y-coordinate.
			int y = 550 - (int) Math.round(mergesortData[i].getTime() * 50 / intervalY);

			// Plot a point (a circle of radius 5) at this coordinate.
			g.fillOval(x - 3, y - 3, 5, 5);
		}

		// Plot data points for selection sort.
		for (int i = 0; i != selectionsortData.length; i++) {
			g.setColor(Color.red);
			int x = WIDTH_HORIZONTAL + (50 * selectionsortData[i].getSize() / intervalX);
			// Find y-coordinate.
			int y = 550 - (int) Math.round(selectionsortData[i].getTime() * 50 / intervalY);

			// Plot a point (a circle of radius 5) at this coordinate.
			g.fillOval(x - 3, y - 3, 5, 5);
		}

		// Plot data points for insertion sort.
		for (int i = 0; i != insertionsortData.length; i++) {
			g.setColor(Color.green);
			int x = WIDTH_HORIZONTAL + (50 * insertionsortData[i].getSize() / intervalX);
			// Find y-coordinate.
			int y = 550 - (int) Math.round(insertionsortData[i].getTime() * 50 / intervalY);

			// Plot a point (a circle of radius 5) at this coordinate.
			g.fillOval(x - 3, y - 3, 5, 5);
		}

		g.setColor(Color.black);
		// Set a font for axis labels.
		g.setFont(new Font("Serif", Font.ITALIC, 13));
		// Label abscissa titles.
		g.drawString("Input Size", 225 + WIDTH_HORIZONTAL, 600);
		// Label ordinate titles.
		g.drawString("Run Time (ns)", 10, 300);
	}

	// Calculate an optimal abscissa-interval.
	public int findXInterval() {
		int result = 1;// Abscissa interval
		double max = 1;// Maximum population.

		for (int i = 0; i != mergesortData.length; i++) {
			max = Math.max(max, mergesortData[i].getSize());
		}
		for (int i = 0; i != selectionsortData.length; i++) {
			max = Math.max(max, selectionsortData[i].getSize());
		}
		for (int i = 0; i != insertionsortData.length; i++) {
			max = Math.max(max, insertionsortData[i].getSize());
		}
		result = (int) Math.ceil(((max) / 10));
		// If the abscissa-interval is larger than 100, take the two most significant
		// digits.
		if (result > 100) {
			int sd = Integer.parseInt(Integer.toString(result).substring(0, 2));
			for (int i = 0; i != Integer.toString(result).length() - 2; i++) {
				sd = sd * 10;
			}
			result = sd;
		}
		return result;
	}

	// Calculates an optimal ordinate-interval.
	public int findYInterval() {
		int result = 1;// Ordinate interval
		long max = 1;// Maximum population.

		for (int i = 0; i != mergesortData.length; i++) {
			max = Math.max(max, mergesortData[i].getTime());
		}
		for (int i = 0; i != selectionsortData.length; i++) {
			max = Math.max(max, selectionsortData[i].getTime());
		}
		for (int i = 0; i != insertionsortData.length; i++) {
			max = Math.max(max, insertionsortData[i].getTime());
		}
		result = (int) Math.ceil(((max) / 10.0));

		// If the ordinate-interval is larger than 100, take the two most significant
		// digits.
		if (result > 100) {
			int sd = Integer.parseInt(Integer.toString(result).substring(0, 2));
			for (int i = 0; i != Integer.toString(result).length() - 2; i++) {
				sd = sd * 10;
			}
			result = sd;
		}
		return result;
	}
}
