package de.xancake.pattern.service.locator;

public class ServiceLocatorSingleton extends ServiceLocator {
	private ServiceLocatorSingleton() {}
	
	public static ServiceLocator getInstance() {
		return Holder.INSTANCE;
	}
	
	private static class Holder {
		private static final ServiceLocatorSingleton INSTANCE = new ServiceLocatorSingleton();
	}
}
