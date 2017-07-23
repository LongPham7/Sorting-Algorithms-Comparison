import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GraphFrame {
	
	JFrame frame = new JFrame("Comparison Graph");
	GraphPanel panel = new GraphPanel();
	
	public void activate() {
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setVisible(false);
	}
	
	public void addX1(int length) {
		panel.sampleX1.add(length);
	}
	
	public void addY1(long n) {
		panel.sampleY1.add(n);
	}
	
	public void addX2(int length) {
		panel.sampleX2.add(length);
	}
	
	public void addY2(long n) {
		panel.sampleY2.add(n);
	}
	
	public void addX3(int length) {
		panel.sampleX3.add(length);
	}
	
	public void addY3(long n) {
		panel.sampleY3.add(n);
	}
	
	public void clearList() {
		panel.sampleX1.clear();
		panel.sampleY1.clear();
		panel.sampleX2.clear();
		panel.sampleY2.clear();
		panel.sampleX3.clear();
		panel.sampleY3.clear();
	}

}
