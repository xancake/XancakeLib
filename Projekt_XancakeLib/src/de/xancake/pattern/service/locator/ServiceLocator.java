package de.xancake.pattern.service.locator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ServiceLocator {
	@SuppressWarnings("rawtypes")
	private Map<Class, Object> myServiceMapping;
	
	public ServiceLocator() {
		myServiceMapping = new HashMap<>();
	}
	
	public <T> void registerService(Class<T> serviceClass, T service) {
		myServiceMapping.put(Objects.requireNonNull(serviceClass), service);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T locateService(Class<T> serviceClass) {
		if(!myServiceMapping.containsKey(serviceClass)) {
			throw new IllegalArgumentException("Für die Klasse " + serviceClass.getName() + " wurde kein Objekt registriert!");
		}
		return (T)myServiceMapping.get(Objects.requireNonNull(serviceClass));
	}
}
