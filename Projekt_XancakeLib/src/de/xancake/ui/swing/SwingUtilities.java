package de.xancake.ui.swing;

import java.awt.Component;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class SwingUtilities {
	private SwingUtilities() {}
	
	public static void setShortcutAction(JComponent component, int condition, KeyStroke keyStroke, String actionKey, Action action) {
		component.getInputMap(condition).put(keyStroke, actionKey);
		component.getActionMap().put(actionKey, action);
	}
	
	public static JPanel labelComponent(Component component, String label) {
		JLabel jlabel = new JLabel(label);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(jlabel);
		panel.add(component);
		return panel;
	}
}
