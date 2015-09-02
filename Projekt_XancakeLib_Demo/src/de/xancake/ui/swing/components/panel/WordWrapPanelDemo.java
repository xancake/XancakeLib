package de.xancake.ui.swing.components.panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import de.xancake.ui.swing.components.SwingDemonstrator;
import de.xancake.ui.swing.components.panel.WordsWrappingPanel;

public class WordWrapPanelDemo extends SwingDemonstrator {
	public static void main(String[] args) {
		String[] text = new String[] {
				"[ANFANG]\n" +
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod " +
				"tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At " +
				"vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
				"no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit " +
				"amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut " +
				"labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam " +
				"et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata " +
				"sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur " +
				"sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore " +
				"magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo " +
				"dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est " +
				"Lorem ipsum dolor sit amet." +
				"\n\n" +
				"Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie " +
				"consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan " +
				"et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis " +
				"dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer " +
				"adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna " +
				"aliquam erat volutpat." +
				"\n\n" +
				"Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit " +
				"lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor " +
				"in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu " +
				"feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui " +
				"blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi." +
				"\n\n" +
				"Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id " +
				"quod mazim placerat facer possim assum. Lorem ipsum dolor sit amet, consectetuer " +
				"adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna " +
				"aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation " +
				"ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat." +
				"\n\n" +
				"Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie " +
				"consequat, vel illum dolore eu feugiat nulla facilisis." +
				"\n\n" +
				"At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
				"no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, " +
				"consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et " +
				"dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo " +
				"dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem " +
				"ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, At " +
				"accusam aliquyam diam diam dolore dolores duo eirmod eos erat, et nonumy sed tempor " +
				"et et invidunt justo labore Stet clita ea et gubergren, kasd magna no rebum. " +
				"sanctus sea sed takimata ut vero voluptua. est Lorem ipsum dolor sit amet. Lorem " +
				"ipsum dolor sit amet, consetetur." +
				"\n[ENDE]"
		};
		
		final WordsWrappingPanel wrapPanel = new WordsWrappingPanel(WordsWrappingPanel.LEADING, WordsWrappingPanel.LEADING, text);
		
		final JToggleButton autoUpdate = new JToggleButton("Auto Update");
		
		JComboBox<String> vertical = new JComboBox<String>(ALIGNMENT);
		vertical.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				wrapPanel.setVerticalAlignment(getIDFromString((String)e.getItem()));
				if(autoUpdate.isSelected())
					wrapPanel.repaint();
			}
		});
		
		JComboBox<String> horizontal = new JComboBox<String>(ALIGNMENT);
		horizontal.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				wrapPanel.setHorizontalAlignment(getIDFromString((String)e.getItem()));
				if(autoUpdate.isSelected())
					wrapPanel.repaint();
			}
		});
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wrapPanel.repaint();
			}
		});
		
		JPanel controls = new JPanel();
		controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));
		controls.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		controls.add(vertical);
		controls.add(horizontal);
		controls.add(autoUpdate);
		controls.add(update);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(wrapPanel);
		panel.add(controls);
		
		show(panel);
	}
	
	private static final String LEADING     = "Leading";
	private static final String CENTER      = "Center";
	private static final String TRAILING    = "Trailing";
	private static final String[] ALIGNMENT = new String[] {LEADING, CENTER, TRAILING};
	private static final int getIDFromString(String alignment) {
		if(LEADING.equals(alignment)) {
			return WordsWrappingPanel.LEADING;
		} else if(CENTER.equals(alignment)) {
			return WordsWrappingPanel.CENTER;
		} else if(TRAILING.equals(alignment)) {
			return WordsWrappingPanel.TRAILING;
		} else {
			throw new IllegalArgumentException("CANNOT BE!");
		}
	}
}
