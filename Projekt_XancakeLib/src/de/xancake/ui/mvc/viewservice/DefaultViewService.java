package de.xancake.ui.mvc.viewservice;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import de.xancake.ui.mvc.Controller;
import de.xancake.ui.mvc.View;
import de.xancake.ui.mvc.ViewListener;

public class DefaultViewService implements ViewService {
	@SuppressWarnings("rawtypes")
	private Map<Class, Class> myViewMapping;
	
	public DefaultViewService() {
		myViewMapping = new HashMap<>();
	}
	
	@Override
	public <M, L extends ViewListener, V extends View<M, L>> void registerView(
		Class<? extends Controller<M, L, V, ?>> controller,
		Class<? extends V> view
	) {
		if (view == null) {
			throw new IllegalArgumentException("Die View-Klasse darf nicht 'null' sein!");
		}
		if (view.isInterface() || Modifier.isAbstract(view.getModifiers())) {
			throw new IllegalArgumentException("Die View-Klasse darf weder ein Interface noch abstract sein!");
		}
		Constructor<?> constructor = getEmptyConstructor(view);
		if (constructor == null) {
			throw new IllegalArgumentException("Die View-Klasse enthält keinen parameterlosen Konstruktor!");
		}
		if (!Modifier.isPublic(constructor.getModifiers())) {
			throw new IllegalArgumentException("Der parameterlose Konstruktor der View-Klasse muss public sein!");
		}
		
		myViewMapping.put(controller, view);
	}
	
	/**
	 * Hilfsmethode, die den parameterlosen Konstruktor einer Klasse findet. Für innere Klassen muss der erste Parameter
	 * aller Konstruktoren der Typ der äußeren Klasse sein (wird durch die Sprache gewährleistet), siehe
	 * {@link Class#getConstructor(Class...)}.
	 * 
	 * @param clazz Die zu prüfende Klasse
	 * @return Der gefundene Konstruktor oder {@code null}, wenn keiner gefunden wurden konnte
	 * @see Class#getConstructor(Class...)
	 */
	private Constructor<?> getEmptyConstructor(Class<?> clazz) {
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			Class<?>[] constructorParameterTypes = constructor.getParameterTypes();
			if (constructorParameterTypes.length == 0) {
				// Parameterloser Konstruktor gefunden
				return constructor;
			} else if (
				clazz.getEnclosingClass() != null
					&& constructorParameterTypes.length == 1
					&& constructorParameterTypes[0].equals(clazz.getEnclosingClass())
			) {
				// Parameterloser Konstruktor für innere Klassen gefunden (@see Class#getConstructor(Class...))
				return constructor;
			}
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public boolean isControllerRegistered(Class<? extends Controller> controller) {
		return myViewMapping.containsKey(controller);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <M, L extends ViewListener, V extends View<M, L>> V createView(
		Class<? extends Controller<M, L, V, ?>> controller
	) throws InvocationTargetException {
		try {
			Class<V> viewClass = myViewMapping.get(controller);
			if (viewClass == null) {
				// Es wurde keine View zu dem Controller registriert.
				return null;
			}
			
			Class<?> enclosingClass = viewClass.getEnclosingClass();
			if (enclosingClass == null) {
				return viewClass.getConstructor().newInstance();
			} else {
				return viewClass.getConstructor(enclosingClass).newInstance(
					enclosingClass.getConstructor().newInstance()
				);
			}
		} catch (
			SecurityException
			| InstantiationException
			| IllegalAccessException
			| NoSuchMethodException
			| IllegalArgumentException e
		) {
			// SecurityException, wenn es Probleme mit dem SecurityManager gibt
			// InstantiationException, wenn die View-Klasse abstract ist
			// IllegalAccessException, wenn der parameterlose Konstruktor der View-Klasse nicht public ist
			// NoSuchMethodException, wenn die View-Klasse keinen parameterlosen Konstruktor hat
			// IllegalArgumentException, wenn etwas mit den Parametern nicht stimmt
			// Diese Exceptions sollten allesamt nicht auftreten, wenn die Checks in registerView korrekt sind
			throw new RuntimeException(
				"Diese Exception sollte nicht auftreten. Wenn sie dennoch auftrat handelt es sich hierbei um einen Programmierfehler. Bitte benachrichtigen Sie den/die Entwickler mitsamt des Stacktraces, um diesen Fehler in Zukunft beheben zu können.",
				e
			);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <M, L extends ViewListener, V extends View<M, L>> V createView(Controller<M, L, V, ?> controller)
		throws InvocationTargetException
	{
		return createView((Class<? extends Controller<M, L, V, ?>>)controller.getClass());
	}
	
	@Override
	public void reset() {
		myViewMapping.clear();
	}
}
