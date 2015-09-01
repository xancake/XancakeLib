package de.xancake.ui.mvc;

public interface Controller_I<M extends Model_I, L extends ViewListener_I, V extends View_I<M, L>> {
	/**
	 * Gibt das von diesem Controller verwaltete {@link Model_I} zurück.
	 * @return Das {@link Model_I}
	 */
	M getModel();
	
	/**
	 * Registriert das übergebene {@link Model_I} an diesem Controller.
	 * Sollte zu diesem Controller schon eine {@link View_I} registriert sein, wird die {@link View_I} mit dem {@link Model_I} befüllt.
	 * @param model Das zu verwaltende {@link Model_I}
	 * @see View_I#fillViewWithModel(Model_I)
	 */
	void setModel(M model);
	
	/**
	 * Gibt die von diesem Controller verwaltete {@link View_I} zurück.
	 * @return Die {@link View_I}
	 */
	V getView();
	
	/**
	 * Registriert die übergebene {@link View_I} an diesem Controller.
	 * Dabei registriert dieser Controller wiederum einen {@link ViewListener_I} auf der {@link View_I}
	 * und unregestriert sich an einer gebenenfalls vorher registrierten {@link View_I}.
	 * Sollte zu diesem Controller schon ein {@link Model_I} registriert sein, wird die {@link View_I} mit dem {@link Model_I} befüllt.
	 * @param view Die zu verwaltende {@link View_I}
	 * @see #getViewListener()
	 * @see View_I#fillViewWithModel(Model_I)
	 */
	void setView(V view);
	
	/**
	 * Gibt den {@link ViewListener_I} zurück, der auf der {@link View_I} die von diesem Controller verwaltet werden soll registriert wird.
	 * Es muss gewährleistet werden, dass diese Methode immer den selben {@link ViewListener_I} zurückgibt.
	 * @return Der {@link ViewListener_I}
	 */
	L getViewListener();
}
