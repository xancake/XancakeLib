package de.xancake.ui.swing.components;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class SwingDemonstrator {
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {}
	}
	
	public static void show(JComponent content) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(content);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
