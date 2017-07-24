import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GraphFrame {
	
	JFrame frame = new JFrame("Comparison Graph");
	GraphPanel panel = new GraphPanel();
	
	public void activate() {
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setVisible(true);
	}
	
	public void setData(DataPoint[] merge, DataPoint[] selection, DataPoint[] insertion) {
		panel.setData(merge, selection, insertion);
	}
}
