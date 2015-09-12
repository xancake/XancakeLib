package de.xancake.pattern.service.locator;

/**
 * Ein Singleton für den zentralen Zugriff auf einen {@link ServiceLocator_I}.
 * 
 * @author Lars 'Xancake' Nielsen
 */
public class ServiceLocatorSingleton extends ServiceLocator {
	/**
	 * Ein ServiceLocator der registriert werden kann, um ein Overriden zu
	 * ermöglichen. Dies wird nur vom {@link TestServiceLocatorSingleton}
	 * benutzt und sollte sonst keine Verwendung finden.
	 */
	ServiceLocator _overrideServiceLocator;
	
	private ServiceLocatorSingleton() {
		// Hier können Services registriert werden
	}
	
	/**
	 * Gibt den als Singleton gehaltenen ServiceLocator zurück.
	 * @return Der ServiceLocator
	 */
	public static ServiceLocator_I getInstance() {
		return Holder.INSTANCE;
	}
	
	@Override
	public <T> T locateService(Class<T> serviceClass) {
		// Implementation der Methode realisiert die Override-Mechanik.
		// Wenn kein Override-Locator registriert ist, wird der Normale verwendet.
		// Ansonsten wird zuerst ermittelt, ob der Override-Locator den Service
		// registriert hat und erst wenn nicht, wird der Normale verwendet.
		if(_overrideServiceLocator == null) {
			return super.locateService(serviceClass);
		} else {
			T object = _overrideServiceLocator.locateService(serviceClass);
			if(object == null) {
				object = super.locateService(serviceClass);
			}
			return object;
		}
	}
	
	/**
	 * Der Holder für die Realisierung des Singleton-Patterns.
	 */
	private static class Holder {
		private static final ServiceLocatorSingleton INSTANCE = new ServiceLocatorSingleton();
	}
}
