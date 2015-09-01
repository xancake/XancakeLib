package de.xancake.ui.mvc.example;

import de.xancake.ui.mvc.ViewListener_I;

public interface PersonViewListener_I extends ViewListener_I {
	void onIdentChanged(int ident);
	void onVornameChanged(String vorname);
	void onNachnameChanged(String nachname);
	void onRandom();
}
