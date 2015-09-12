package de.xancake.pattern.service.locator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Ein ServiceLocator der es ermöglicht, Services zu Registrieren und zu ermitteln.
 * Ein ServiceLocator verfügt über seine eigenen Service-Bindings für einen zentralen
 * ServiceLocator kann {@link ServiceLocatorSingleton} verwendet werden.
 * 
 * @author Lars 'Xancake' Nielsen
 */
public class ServiceLocator implements ServiceLocator_I {
	@SuppressWarnings("rawtypes")
	private Map<Class, Object> myServiceMapping;
	
	/**
	 * Initialisiert einen neuen ServiceLocator.
	 */
	public ServiceLocator() {
		myServiceMapping = new HashMap<>();
	}
	
	@Override
	public boolean isServiceRegistered(Class<?> serviceClass) {
		return myServiceMapping.containsKey(serviceClass);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T locateService(Class<T> serviceClass) {
		if(!isServiceRegistered(serviceClass)) {
			throw new IllegalArgumentException("Für die Klasse " + serviceClass.getName() + " wurde kein Objekt registriert!");
		}
		return (T)myServiceMapping.get(Objects.requireNonNull(serviceClass));
	}
	
	/**
	 * Ermöglicht das Registrieren von Services an diesem ServiceLocator.
	 * @param serviceClass Der abstrakte Service zu dem ein konkreter Service registriert werden soll
	 * @param service Die konkrete Implementation des zu registrierenden Services 
	 */
	public <T> void registerService(Class<T> serviceClass, T service) {
		myServiceMapping.put(Objects.requireNonNull(serviceClass), service);
	}
	
	/**
	 * Entfernt alle registrierten Services.
	 */
	public void reset() {
		myServiceMapping.clear();
	}
}
