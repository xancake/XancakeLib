package de.xancake.ui.mvc.viewservice;

import java.lang.reflect.InvocationTargetException;
import de.xancake.ui.mvc.Controller;
import de.xancake.ui.mvc.ViewListener;
import de.xancake.ui.mvc.View;

public interface ViewService {
	/**
	 * Registriert die übergebene {@link View View}-Klasse für die übergebene
	 * {@link Controller Controller}-Klasse. Dabei wird auch geprüft, ob die
	 * View-Klasse als Parameter erlaubt ist.
	 * @param controller Die Klasse des Controllers
	 * @param view Die Klasse der View
	 * @throws IllegalArgumentException Wenn die View-Klasse nicht gültig ist
	 */
	<M, L extends ViewListener, V extends View<M, L>>
	void registerView(Class<? extends Controller<M, L, V, ?>> controller, Class<? extends V> view);
	
	/**
	 * Prüft, ob für die übergebene {@link Controller Controller}-Klasse eine
	 * {@link View View} registriert wurde.
	 * @param controller Die Klasse des Controller
	 * @return {@code true}, wenn eine View zu dem Controller registriert wurde, ansonsten {@code false}
	 */
	@SuppressWarnings("rawtypes")
	boolean isControllerRegistered(Class<? extends Controller> controller);
	
	/**
	 * Erzeugt ein Exemplar der registrierten {@link View View} zu der übergebenen
	 * {@link Controller Controller}-Klasse und gibt dieses zurück.
	 * @param controller Die Klasse des Controllers
	 * @return Ein Exemplar der registrierten View, oder {@code null}, wenn keine zugeordnet wurde
	 * @throws InvocationTargetException Wenn der Konstruktor der View eine Exception
	 *         geworfen hat
	 */
	<M, L extends ViewListener, V extends View<M, L>>
	V createView(Class<? extends Controller<M, L, V, ?>> controller) throws InvocationTargetException;
	
	/**
	 * Erzeugt ein Exemplar der registrierten {@link View View} zu dem übergebenen
	 * {@link Controller Controller} und gibt dieses zurück.
	 * @param controller Der Controller
	 * @return Ein Exemplar der registrierten View, oder {@code null}, wenn keine zugeordnet wurde
	 * @throws InvocationTargetException Wenn der Konstruktor der View eine Exception
	 *         geworfen hat
	 */
	<M, L extends ViewListener, V extends View<M, L>>
	V createView(Controller<M, L, V, ?> controller) throws InvocationTargetException;
	
	/**
	 * Entfernt alle registrierten {@link Controller Controller}-{@link View View}-Mappings.
	 */
	void reset();
}
