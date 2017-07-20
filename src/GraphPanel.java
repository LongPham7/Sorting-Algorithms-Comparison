import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class GraphPanel extends JPanel {

	// Default serial version UID
	private static final long serialVersionUID = 1L;
	
	public ArrayList<Integer> sampleX1 = new ArrayList<Integer>();
	public ArrayList<Long> sampleY1 = new ArrayList<Long>();
	public ArrayList<Integer> sampleX2 = new ArrayList<Integer>();
	public ArrayList<Long> sampleY2 = new ArrayList<Long>();
	public ArrayList<Integer> sampleX3 = new ArrayList<Integer>();
	public ArrayList<Long> sampleY3 = new ArrayList<Long>();

	@Override
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);

		Graphics2D g = (Graphics2D) g1;

		// An optimized distance from the left edge of a window to the left edge of the
		// graph
		final int WIDTH_HORIZONTAL = 115 + 5 * (Integer.toString(findYInterval()).length());
		// Interval of abscissa
		int intervalX = findXInterval();
		// Interval of ordinate
		int intervalY = findYInterval();

		// Draw abscissa and ordinate
		g.setColor(Color.black);
		g.drawLine(WIDTH_HORIZONTAL, 550, WIDTH_HORIZONTAL + 500, 550);
		g.drawLine(WIDTH_HORIZONTAL, 550, WIDTH_HORIZONTAL, 50);

		// Draw scale bars for abscissa.
		for (int i = 1; i < 11; i++) {
			g.drawLine(WIDTH_HORIZONTAL + 50 * i, 550, WIDTH_HORIZONTAL + 50 * i, 555);
		}

		// Draw scale bar for ordinate.
		for (int i = 1; i < 11; i++) {
			g.drawLine(WIDTH_HORIZONTAL - 5, 550 - 50 * i, WIDTH_HORIZONTAL, 550 - 50 * i);
		}

		// Label numbers for abscissa.
		for (int i = 0; i < 11; i++) {
			String n = Integer.toString(intervalX * i);
			g.drawString(n, WIDTH_HORIZONTAL - 5 + 50 * i, 575);
		}
		// Label numbers for ordinate.
		for (int i = 0; i < 11; i++) {
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

		for (int i = 0; i < sampleX1.size(); i++) {
			g.setColor(Color.blue);
			// Find x-coordinate.
			int x = WIDTH_HORIZONTAL + (50 * sampleX1.get(i) / intervalX);
			// Find y-coordinate.
			int y = 550 - (int) Math.round(sampleY1.get(i) * 50 / intervalY);

			// Plot a point (a circle with radius of 5) on this point.
			g.fillOval(x - 3, y - 3, 5, 5);
		}

		for (int i = 0; i < sampleX2.size(); i++) {
			g.setColor(Color.red);
			int x = WIDTH_HORIZONTAL + (50 * sampleX2.get(i) / intervalX);
			// Find y-coordinate.
			int y = 550 - (int) Math.round(sampleY2.get(i) * 50 / intervalY);

			// Plot a point (a circle with radius of 5) on this point.
			g.fillOval(x - 3, y - 3, 5, 5);
		}

		for (int i = 0; i < sampleX3.size(); i++) {
			g.setColor(Color.green);
			int x = WIDTH_HORIZONTAL + (50 * sampleX3.get(i) / intervalX);
			// Find y-coordinate.
			int y = 550 - (int) Math.round(sampleY3.get(i) * 50 / intervalY);

			// Plot a point (a circle with radius of 5) on this point.
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

	// This method finds an optimized abscissa-interval.
	// @return int:an optimized abscissa-interval
	public int findXInterval() {
		int result = 1;// Ordinate interval
		double max = 1;// Maximum population.
		// Maximum is always either in the beginning or at the end of the array
		// "sample."
		for (int i = 0; i < sampleX1.size(); i++) {
			max = Math.max(max, sampleX1.get(i));
		}
		for (int i = 0; i < sampleX2.size(); i++) {
			max = Math.max(max, sampleX2.get(i));
		}
		for (int i = 0; i < sampleX3.size(); i++) {
			max = Math.max(max, sampleX3.get(i));
		}
		result = (int) Math.ceil(((max) / 10));
		// If ordinate-interval is larger than 100, take only 2 significant digits.
		if (result > 100) {
			int sd = Integer.parseInt(Integer.toString(result).substring(0, 2));
			for (int i = 0; i < Integer.toString(result).length() - 2; i++) {
				sd = sd * 10;
			}
			result = sd;
		}
		return result;
	}

	// This method finds an optimized ordinate-interval.
	// @return int:an optimized ordinate-interval
	public int findYInterval() {
		int result = 1;// Ordinate interval
		long max = 1;// Maximum population.
		// Maximum is always either in the beginning or at the end of the array
		// "sample."
		for (int i = 0; i < sampleY1.size(); i++) {
			max = Math.max(max, sampleY1.get(i));
		}
		for (int i = 0; i < sampleY2.size(); i++) {
			max = Math.max(max, sampleY2.get(i));
		}
		for (int i = 0; i < sampleY3.size(); i++) {
			max = Math.max(max, sampleY3.get(i));
		}
		result = (int) Math.ceil(((max) / 10.0));

		// If ordinate-interval is larger than 100, take only 2 significant digits.
		if (result > 100) {
			int sd = Integer.parseInt(Integer.toString(result).substring(0, 2));
			for (int i = 0; i < Integer.toString(result).length() - 2; i++) {
				sd = sd * 10;
			}
			result = sd;
		}
		return result;
	}
}
