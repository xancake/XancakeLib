package de.xancake.ui.mvc.example;

import de.xancake.ui.mvc.window.WindowViewListener;

public interface PersonViewListener_I extends WindowViewListener {
	void onIdentChanged(int ident);
	void onVornameChanged(String vorname);
	void onNachnameChanged(String nachname);
	void onRandom();
}
