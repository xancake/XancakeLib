package de.xancake.ui.mvc.example;

import de.xancake.ui.mvc.window.WindowViewListener_I;

public interface PersonViewListener_I extends WindowViewListener_I {
	void onIdentChanged(int ident);
	void onVornameChanged(String vorname);
	void onNachnameChanged(String nachname);
	void onRandom();
}
