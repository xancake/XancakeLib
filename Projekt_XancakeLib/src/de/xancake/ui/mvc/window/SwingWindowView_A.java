package de.xancake.ui.mvc.window;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import de.xancake.ui.mvc.View_A;

public abstract class SwingWindowView_A<M, L extends WindowViewListener> extends View_A<M, L> implements WindowView<M, L> {
	protected JFrame myFrame;
	
	public SwingWindowView_A(String title) {
		myFrame = new JFrame(title);
		myFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				myListener.onViewOpened();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				myListener.onViewClosed();
			}
		});
		initComponents();
		initLayout(myFrame.getContentPane());
		initListeners();
	}
	
	protected abstract void initComponents();
	protected abstract void initLayout(Container content);
	protected abstract void initListeners();
	
	@Override
	public boolean isVisible() {
		return myFrame.isVisible();
	}

	@Override
	public void setVisible(boolean visible) {
		myFrame.setVisible(visible);
	}
}
