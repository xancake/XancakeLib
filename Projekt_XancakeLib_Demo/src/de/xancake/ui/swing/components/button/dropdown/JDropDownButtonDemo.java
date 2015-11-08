package de.xancake.ui.swing.components.button.dropdown;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import de.xancake.ui.swing.components.SwingDemonstrator;

public class JDropDownButtonDemo extends SwingDemonstrator {
	@SuppressWarnings("serial")
	private static class TestButton extends JButton {
		public TestButton(String text) {
			super(text);
			setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
		}
		public TestButton(Icon icon) {
			super(icon);
			setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		}
	}
	
	public static void main(String... args) throws Exception {
		LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for(LookAndFeelInfo info : infos) {
			System.out.println(info.getClassName());
		}
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		
		String[] values = new String[] {"Hallo", "Lars", "Welt", ":D", "Maus", "Tastatur", "Monitor", "Wand", "Haus", "Universität", "Ich bin ein ganz langer Text der blöd zu lesen ist!"};
		
		ActionListener listener = new ActionListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JDropDownButton) {
					JDropDownButton<String> dropDownButton = (JDropDownButton<String>)e.getSource();
					JOptionPane.showMessageDialog(null, dropDownButton.getSelectedItem());
				} else if(e.getSource() instanceof JComboBox) {
					JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
					JOptionPane.showMessageDialog(null, comboBox.getSelectedItem());
				}
			}
		};
		
		final JDropDownButton<String> toolButton = new JDropDownButton<String>(new ImageIcon(ClassLoader.getSystemResource("de/xancake/ui/swing/components/button/dropdown/shortestpath2.png")), new DefaultComboBoxModel<String>(values));
		final JDropDownButton<String> centerButton = new JDropDownButton<String>(new ImageIcon(ClassLoader.getSystemResource("de/xancake/ui/swing/components/button/dropdown/spanningtree2.png")), new DefaultComboBoxModel<String>(values));
		final JDropDownButton<String> buttonButton = new JDropDownButton<String>(new ImageIcon(ClassLoader.getSystemResource("de/xancake/ui/swing/components/button/dropdown/traversieren2.png")), new DefaultComboBoxModel<String>(values));
		final JDropDownButton<String> toolButton2 = new JDropDownButton<String>("Ein ganz langer Titel!", new ImageIcon(ClassLoader.getSystemResource("de/xancake/ui/swing/components/button/dropdown/shortestpath2.png")), new DefaultComboBoxModel<String>(values));
		final JDropDownButton<String> centerButton2 = new JDropDownButton<String>("Ein ganz langer Titel!", new ImageIcon(ClassLoader.getSystemResource("de/xancake/ui/swing/components/button/dropdown/spanningtree2.png")), new DefaultComboBoxModel<String>(values));
		final JDropDownButton<String> buttonButton2 = new JDropDownButton<String>("Ein ganz langer Titel!", new ImageIcon(ClassLoader.getSystemResource("de/xancake/ui/swing/components/button/dropdown/traversieren2.png")), new DefaultComboBoxModel<String>(values));
		final JComboBox<String> centerBox = new JComboBox<String>(new DefaultComboBoxModel<String>(values));
		
		toolButton.addActionListener(listener);
		centerButton.addActionListener(listener);
		buttonButton.addActionListener(listener);
		toolButton2.addActionListener(listener);
		centerButton2.addActionListener(listener);
		buttonButton2.addActionListener(listener);
		centerBox.addActionListener(listener);
		
		JToolBar toolbar = new JToolBar();
		toolbar.add(toolButton);
		toolbar.add(toolButton2);
		toolbar.add(new JButton(new ImageIcon(ClassLoader.getSystemResource("de/xancake/ui/swing/components/button/dropdown/shortestpath2.png"))));
		toolbar.add(new JButton("Test"));
		toolbar.add(Box.createGlue());
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		centerPanel.add(centerButton);
		centerPanel.add(centerButton2);
		centerPanel.add(new TestButton(new ImageIcon(ClassLoader.getSystemResource("de/xancake/ui/swing/components/button/dropdown/shortestpath2.png"))));
		centerPanel.add(new TestButton("Test"));
		centerPanel.add(centerBox);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(buttonButton);
		buttonPanel.add(buttonButton2);
		buttonPanel.add(new TestButton(new ImageIcon(ClassLoader.getSystemResource("de/xancake/ui/swing/components/button/dropdown/shortestpath2.png"))));
		buttonPanel.add(new TestButton("Test"));
		buttonPanel.add(Box.createHorizontalGlue());
		
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(toolbar, BorderLayout.NORTH);
		content.add(centerPanel, BorderLayout.CENTER);
		content.add(buttonPanel, BorderLayout.SOUTH);
		SwingDemonstrator.show(content);
	}
}
