package de.xancake.io.db.sql.config;

import java.io.IOException;

/**
 * Ein Singleton für den zentralen Zugriff auf die Datenbankverbindungskonfiguration.
 * 
 * @author Lars 'Xancake' Nielsen
 */
public class DBConfigurationSingleton {
	private static final String DEFAULT_CONFIG = "db.properties";
	
	private DBConfiguration_I myConfiguration;
	
	private DBConfigurationSingleton() {
		try {
			myConfiguration = new DBConfigurationProperties(getClass().getClassLoader().getResourceAsStream(DEFAULT_CONFIG));
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException("Fehler beim Laden der Datenbank-Konfigurations-Datei", e);
		}
	}
	
	/**
	 * Gibt die {@link DBConfiguration_I} des Singletons zurück.
	 * @return Die Datenbank-Konfiguration
	 */
	public DBConfiguration_I getConfiguration() {
		return myConfiguration;
	}
	
	/**
	 * Gibt das Singleton zurück.
	 * @return Das Singleton
	 */
	public static DBConfigurationSingleton getInstance() {
		return Holder.INSTANCE;
	}
	
	/**
	 * Der Holder für die Realisierung des Singleton-Patterns.
	 */
	private static class Holder {
		private static final DBConfigurationSingleton INSTANCE = new DBConfigurationSingleton();
	}
}
