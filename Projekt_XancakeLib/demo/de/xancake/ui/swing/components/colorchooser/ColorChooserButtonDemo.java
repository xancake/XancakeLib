package de.xancake.ui.swing.components.colorchooser;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.xancake.ui.swing.components.SwingDemonstrator;

public class ColorChooserButtonDemo extends SwingDemonstrator {
	public static void main(String[] args) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
		panel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
		panel2.add(new JLabel("Test: "));
		panel2.add(new ColorChooserButton(Color.GREEN, true));
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.LINE_AXIS));
		panel3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
		panel3.add(new JLabel("Test Something: "));
		panel3.add(new ColorChooserButton(Color.ORANGE));
		
		panel.add(panel2);
		panel.add(panel3);
		panel.add(new ColorChooserButton(Color.BLUE, true));
		show(panel);
	}
}
