package de.xancake.ui.mvc.window;

import de.xancake.ui.mvc.ViewListener;

public interface WindowViewListener extends ViewListener {
	/**
	 * Wird von der View aufgerufen, wenn sie angezeigt wird.
	 */
	void onViewOpened();
	
	/**
	 * Wird von der View aufgerufen, wenn sie geschlossen wird.
	 */
	void onViewClosed();
}
