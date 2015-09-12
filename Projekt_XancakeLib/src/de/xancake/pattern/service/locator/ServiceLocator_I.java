package de.xancake.pattern.service.locator;

/**
 * Schnittstelle für einen ServiceLocator, über den sich Services ermitteln lassen.
 * Ein ServiceLocator verfügt über seine eigenen Service-Bindings für einen zentralen
 * ServiceLocator kann {@link ServiceLocatorSingleton} verwendet werden.
 * 
 * @author Lars 'Xancake' Nielsen
 */
public interface ServiceLocator_I {
	/**
	 * Gibt zurück, ob für die abstrakte Service-Klasse eine konkrete Implementation
	 * an diesem ServiceLocator registriert wurde.
	 * @param serviceClass Die abstrakte Service-Klasse
	 * @return {@code true}, wenn der Service registriert wurde, ansonsten {@code false}
	 */
	boolean isServiceRegistered(Class<?> serviceClass);
	
	/**
	 * Gibt den konkreten Service zu der abstrakten Service-Klasse zurück.
	 * @param serviceClass Der gewünschte abstrakte Service
	 * @return Die registrierte, konkrete Implementation des gewünschten Services 
	 * @throws IllegalArgumentException Wenn für den geforderten abstrakten Service kein konkreter Service registriert wurde
	 * @see #isServiceRegistered(Class)
	 */
	abstract <T> T locateService(Class<T> serviceClass);
}