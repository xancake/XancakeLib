package de.xancake.ui.swing.components.colorchooser;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import de.xancake.ui.swing.components.SwingDemonstrator;

public class ColorChooserPanelDemo extends SwingDemonstrator {
	public static void main(String[] args) {
		final ColorChooserPanel  chooser = new ColorChooserPanel();
		final ColorChooserButton display = new ColorChooserButton();
		
		chooser.addColorChooserListener(new ColorChooserListener_I() {
			@Override
			public void onColorSelected(Color color) {
				display.setColor(color);
			}
		});
		
		chooser.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		display.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		display.setEnabled(false);
		display.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(chooser);
		panel.add(display);
		show(panel);
	}
}
