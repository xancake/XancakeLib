package de.xancake.ui.mvc.window;

import de.xancake.ui.mvc.ControllerListener;
import de.xancake.ui.mvc.Controller;

public abstract class WindowController<M, L extends WindowViewListener, V extends WindowView<M, L>, T extends ControllerListener> extends Controller<M, L, V, T> {
	public WindowController(M model, V view) {
		super(model, view);
	}
	
	/**
	 * Zeigt die View an.
	 */
	public void show() {
		getView().setVisible(true);
	}
	
	/**
	 * Versteckt die View.
	 */
	public void hide() {
		getView().setVisible(false);
	}
}
