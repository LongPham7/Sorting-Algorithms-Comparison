import java.awt.BorderLayout;

import javax.swing.JFrame;

/** 
 * This class serves as view in the MVC architecture and creates GUI components for
 * a frame where a graph is displayed. 
 * */
public class GraphFrame {
	
	JFrame frame = new JFrame("Comparison Graph");
	GraphPanel panel = new GraphPanel();
	
	// Creates GUI components.
	public void activate() {
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setVisible(true);
	}
	
	// Passes the data of running time to the panel. 
	public void setData(DataPoint[] merge, DataPoint[] selection, DataPoint[] insertion) {
		panel.setData(merge, selection, insertion);
	}
}
