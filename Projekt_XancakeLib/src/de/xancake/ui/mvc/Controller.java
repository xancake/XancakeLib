package de.xancake.ui.mvc;

import java.util.List;
import java.util.function.BiConsumer;
import de.xancake.pattern.listener.EventDispatcher;

public abstract class Controller<M, L extends ViewListener, V extends View<M, L>, T extends ControllerListener> {
	private EventDispatcher<T> myEventDispatcher;
	private M myModel;
	private V myView;
	
	public Controller(M model, V view) {
		myEventDispatcher = new EventDispatcher<>();
		setModel(model);
		setView(view);
	}
	
	/**
	 * Gibt das von diesem Controller verwaltete Model zurück.
	 * 
	 * @return Das Model
	 */
	public final M getModel() {
		return myModel;
	}
	
	/**
	 * Registriert dasübergebene Model an diesem Controller. Sollte zu diesem Controller schon eine {@link View}
	 * registriert sein, wird die {@link View} mit dem Model befüllt.
	 * 
	 * @param model Das zu verwaltende Model
	 * @see View#fillViewWithModel(Object)
	 */
	public final void setModel(M model) {
		myModel = model;
		update();
	}
	
	/**
	 * Gibt die von diesem Controller verwaltete {@link View} zurück.
	 * 
	 * @return Die {@link View}
	 */
	public final V getView() {
		return myView;
	}
	
	/**
	 * Registriert die übergebene {@link View} an diesem Controller. Dabei registriert dieser Controller wiederum einen
	 * {@link ViewListener} auf der {@link View} und unregestriert sich an einer gebenenfalls vorher registrierten
	 * {@link View}. Sollte zu diesem Controller schon ein Model registriert sein, wird die {@link View} mit dem Model
	 * befüllt.
	 * 
	 * @param view Die zu verwaltende {@link View}
	 * @see #getViewListener()
	 * @see View#fillViewWithModel(Object)
	 */
	public final void setView(V view) {
		unregisterOnView(myView);
		myView = view;
		registerOnView(myView);
		update();
	}
	
	private void unregisterOnView(V view) {
		if(view != null && view.getViewListener() == this) {
			view.setViewListener(null);
		}
	}
	
	private void registerOnView(V view) {
		if(view != null && view.getViewListener() != this) {
			view.setViewListener(getViewListener());
		}
	}
	
	/**
	 * Aktualisiert die Oberfläche mit den aktuellen Daten aus dem Model.
	 */
	protected void update() {
		if(myView != null && myModel != null) {
			myView.fillViewWithModel(myModel);
		}
	}
	
	/**
	 * Fügt dem Controller einen Listener hinzu.
	 */
	public void addControllerListener(T listener) {
		myEventDispatcher.addListener(listener);
	}
	
	/**
	 * Entfernt einen Listener vom Controller.
	 */
	public void removeControllerListener(T listener) {
		myEventDispatcher.removeListener(listener);
	}
	
	/**
	 * Gibt eine Liste aller Listener dieses Controllers zurück.
	 * 
	 * @return Eine Liste der Listener dieses Controllers
	 */
	protected List<T> getListeners() {
		return myEventDispatcher.getListeners();
	}
	
	/**
	 * Benachrichtigt alle Listener über das Auftreten des übergebenen Events.
	 * 
	 * @param event    Das Event, über das benachrichtigt werden soll
	 * @param delegate Die Methode am Listener, die zur Benachrichtigung aufgerufen werden soll
	 */
	protected <E> void fireEvent(E event, BiConsumer<T, E> delegate) {
		myEventDispatcher.fireEvent(event, delegate);
	}
	
	/**
	 * Gibt den {@link ViewListener} zurück, der auf der {@link View} die von diesem Controller verwaltet werden soll
	 * registriert wird. Es muss gewährleistet werden, dass diese Methode immer den selben {@link ViewListener}
	 * zurückgibt.
	 * 
	 * @return Der {@link ViewListener}
	 */
	protected abstract L getViewListener();
}
