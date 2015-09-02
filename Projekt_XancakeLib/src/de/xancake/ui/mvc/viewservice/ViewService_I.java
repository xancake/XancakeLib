package de.xancake.ui.mvc.viewservice;

import java.lang.reflect.InvocationTargetException;
import de.xancake.ui.mvc.Controller_A;
import de.xancake.ui.mvc.ViewListener_I;
import de.xancake.ui.mvc.View_I;

public interface ViewService_I {
	/**
	 * Registriert die übergebene {@link View_I View}-Klasse für die übergebene
	 * {@link Controller_A Controller}-Klasse. Dabei wird auch  geprüft, ob die
	 * View-Klasse als Parameter erlaubt ist.
	 * @param controller Die Klasse des Controllers
	 * @param view Die Klasse der View
	 * @throws IllegalArgumentException Wenn die View-Klasse nicht gültig ist
	 */
	<M, L extends ViewListener_I, V extends View_I<M, L>>
	void registerView(Class<? extends Controller_A<M, L, V, ?>> controller, Class<? extends V> view);
	
	/**
	 * Prüft, ob für die übergebene {@link Controller_A Controller}-Klasse eine
	 * {@link View_I View} registriert wurde.
	 * @param controller Die Klasse des Controller
	 * @return {@code true}, wenn eine View zu dem Controller registriert wurde, ansonsten {@code false}
	 */
	@SuppressWarnings("rawtypes")
	boolean isControllerRegistered(Class<? extends Controller_A> controller);
	
	/**
	 * Erzeugt ein Exemplar der registrierten {@link View_I View} zu der übergebenen
	 * {@link Controller_A Controller}-Klasse und gibt dieses zurück.
	 * @param controller Die Klasse des Controllers
	 * @return Ein Exemplar der registrierten View, oder {@code null}, wenn keine zugeordnet wurde
	 * @throws InvocationTargetException Wenn der Konstruktor der View eine Exception
	 *         geworfen hat
	 */
	<M, L extends ViewListener_I, V extends View_I<M, L>>
	V createView(Class<? extends Controller_A<M, L, V, ?>> controller) throws InvocationTargetException;
	
	/**
	 * Erzeugt ein Exemplar der registrierten {@link View_I View} zu dem übergebenen
	 * {@link Controller_A Controller} und gibt dieses zurück.
	 * @param controller Der Controller
	 * @return Ein Exemplar der registrierten View, oder {@code null}, wenn keine zugeordnet wurde
	 * @throws InvocationTargetException Wenn der Konstruktor der View eine Exception
	 *         geworfen hat
	 */
	<M, L extends ViewListener_I, V extends View_I<M, L>>
	V createView(Controller_A<M, L, V, ?> controller) throws InvocationTargetException;
	
	/**
	 * Entfernt alle registrierten {@link Controller_A Controller}-{@link View_I View}-Mappings.
	 */
	void reset();
}
