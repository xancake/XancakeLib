package de.xancake.pattern.service.locator;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocatorLocator {
	private Map<String, ServiceLocator> myLocatorMapping;
	
	private ServiceLocatorLocator() {
		myLocatorMapping = new HashMap<>();
	}
	
	public void registerServiceLocator(String name, ServiceLocator locator) {
		myLocatorMapping.put(name, locator);
	}
	
	public ServiceLocator_I getServiceLocator(String name) {
		return myLocatorMapping.get(name);
	}
	
	public static ServiceLocatorLocator getInstance() {
		return Holder.INSTANCE;
	}
	
	private static class Holder {
		private static final ServiceLocatorLocator INSTANCE = new ServiceLocatorLocator();
	}
}
