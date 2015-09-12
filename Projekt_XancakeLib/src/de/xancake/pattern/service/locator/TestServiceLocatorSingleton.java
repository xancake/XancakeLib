package de.xancake.pattern.service.locator;

/**
 * Diese Klasse stellt einen zentralen ServiceLocator für den Test bereit.
 * Das Singleton verfügt über zwei ServiceLocator. Der eine kommt von
 * {@link ServiceLocatorSingleton} und enthält die Standardkonfiguration.
 * Der Andere wird direkt durch dieses Singleton repräsentiert und steht
 * zur Verfügung um Standardservices mit Mocks zu overriden.
 * Es geschieht also ein zweistufiges Ermitteln des Services, zuerst in
 * diesem ServiceLocator und wenn hier keiner registriert ist im
 * {@link ServiceLocatorSingleton} um die Standardimplementation zu erhalten.
 * 
 * @author Lars 'Xancake' Nielsen
 */
public class TestServiceLocatorSingleton extends ServiceLocator {
	@Override
	public void reset() {
		super.reset();
		((ServiceLocatorSingleton)ServiceLocatorSingleton.getInstance())._overrideServiceLocator = null;
	}
	
	/**
	 * Gibt den als Singleton gehaltenen ServiceLocator zurück.
	 * Dabei wird der Locator am echten {@link ServiceLocatorSingleton} als
	 * Override registiert.
	 * @return Der ServiceLocator
	 */
	public static ServiceLocator getInstance() {
		ServiceLocator locator = Holder.INSTANCE;
		((ServiceLocatorSingleton)ServiceLocatorSingleton.getInstance())._overrideServiceLocator = locator;
		return locator;
	}
	
	/**
	 * Der Holder für die Realisierung des Singleton-Patterns.
	 */
	private static class Holder {
		private static final TestServiceLocatorSingleton INSTANCE = new TestServiceLocatorSingleton();
	}
}
