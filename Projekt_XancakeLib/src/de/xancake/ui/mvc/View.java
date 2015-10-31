package de.xancake.ui.mvc;

public interface View<M, L extends ViewListener> {
	/**
	 * Gibt den {@link ViewListener} zurück, der aktuell auf diese View lauscht.
	 * @return Der aktuell auf diese View lauschende {@link ViewListener}
	 */
	L getViewListener();
	
	/**
	 * Registriert den übergebenen {@link ViewListener}, auf dieser View.
	 * @param l Der zu registrierende {@link ViewListener}
	 */
	void setViewListener(L l);
	
	/**
	 * Fällt diese View mit den Daten aus dem Model.
	 * @param model Das Model
	 */
	void fillViewWithModel(M model);
}
