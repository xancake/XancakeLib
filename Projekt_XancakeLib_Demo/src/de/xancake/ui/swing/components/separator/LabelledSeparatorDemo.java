package de.xancake.ui.swing.components.separator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import de.xancake.ui.swing.components.SwingDemonstrator;

public class LabelledSeparatorDemo extends SwingDemonstrator {
	public static void main(String[] args) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new LabelledSeparator("Button"));
		panel.add(new JButton("Clickedy Click!"));
		panel.add(Box.createVerticalStrut(11));
		panel.add(new LabelledSeparator("ProgressBar"));
		panel.add(new JProgressBar());
		panel.add(Box.createVerticalStrut(11));
		panel.add(new NonSpaceEatingSeparator());
		panel.add(new JTextArea());
		show(panel);
	}
}
