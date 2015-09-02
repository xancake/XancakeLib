package de.xancake.ui.swing.components.button.dropdown;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class JDropDownButton<E> extends JComboBox<E> {
	private static final long serialVersionUID = -9203181637555493397L;
	
	private String myText;
	private Icon myIcon;
	private ButtonEditor myEditor;
	
	private boolean layingOut;
	
	public JDropDownButton(String text) {
		this(text, (Icon)null);
	}
	
	public JDropDownButton(Icon icon) {
		this(null, icon);
	}
	
	public JDropDownButton(String text, Icon icon) {
		myText = text;
		myIcon = icon;
		init();
	}
	
	public JDropDownButton(String text, ComboBoxModel<E> model) {
		super(model);
		myText = text;
		init();
	}
	
	public JDropDownButton(Icon icon, ComboBoxModel<E> model) {
		super(model);
		myIcon = icon;
		init();
	}
	
	public JDropDownButton(String text, Icon icon, ComboBoxModel<E> model) {
		super(model);
		myText = text;
		myIcon = icon;
		init();
	}
	
	private void init() {
		initComponents();
		initLayout();
		initListeners();
	}
	
	private void initComponents() {
		myEditor = new ButtonEditor(myText, myIcon);
		setEditor(myEditor);
		setEditable(true);
	}
	
	private void initLayout() {
//		setPrototypeDisplayValue(null);
		getPreferredSize().width = 32;
		setMaximumSize(getPreferredSize());
	}
	
	private void initListeners() {
		myEditor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == myEditor) {
					fireActionEvent();
				}
			}
		});
		addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				
			}
		});
	}
	
	private class ButtonEditor extends JButton implements ComboBoxEditor {
		private static final long serialVersionUID = -5064493946946894503L;
		
		public ButtonEditor(String text, Icon icon) {
			super(text, icon);
			setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0));
//			setBorder(null);
			setContentAreaFilled(false);
			setDefaultCapable(false);
		}
		
		@Override
		public Component getEditorComponent() {
			return this;
		}
		
		@Override
		public Object getItem() {
			return getSelectedItem();
		}
		
		@Override
		public void setItem(Object anObject) {}
		
		@Override
		public void selectAll() {}
	}
}
