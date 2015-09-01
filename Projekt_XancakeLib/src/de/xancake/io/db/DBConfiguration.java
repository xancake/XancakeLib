package de.xancake.io.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Repräsentiert die Konfiguration der Verbindungsdaten einer Datenbankverbindung.
 * 
 * @author Lars 'Xancake' Nielsen
 */
public class DBConfiguration {
	private static final String DB_DRIVER = "db.driver";
	private static final String DB_HOST   = "db.host";
	private static final String DB_USER   = "db.user";
	private static final String DB_PASS   = "db.pass";
	
	private Properties myProperties;
	
	/**
	 * Initialisiert eine Datenbankkonfiguration aus einem {@link InputStream}.
	 * Die Daten aus dem InputStream werden entsprechend von {@link Properties}
	 * verarbeitet und folgen somit den gleichen Formatierungsregeln.
	 * @param config Der {@link InputStream} zum Lesen der Konfiguration
	 * @throws IOException Wenn ein Fehler beim Lesen der Konfiguration aus dem InputStream auftritt
	 * @throws ClassNotFoundException Wenn die JDBC-Treiberklasse nicht geladen werden konnte
	 * @throws IllegalArgumentException Wenn der übergebene InputStream {@code null} ist
	 */
	public DBConfiguration(InputStream config) throws IOException, ClassNotFoundException {
		if(config == null) {
			throw new IllegalArgumentException("Die Datenbank-Konfigurations-Datei konnte nicht gefunden werden");
		}
		myProperties = new Properties();
		myProperties.load(config);
		
		// JDBC-Treiberklasse laden
		Class.forName(getDriver());
	}
	
	/**
	 * Gibt den Treibernamen für die Datenbank zurück.
	 * @return Der Treibername für die Datenbank
	 */
	public String getDriver() {
		return myProperties.getProperty(DB_DRIVER);
	}
	
	/**
	 * Gibt den Host / Connection-String für die Datenbank zurück.
	 * @return Der Host / Connection-String für die Datenbank
	 */
	public String getHost() {
		return myProperties.getProperty(DB_HOST);
	}
	
	/**
	 * Gibt den Benutzernamen für die Datenbank zurück.
	 * @return Der Benutzername für die Datenbank
	 */
	public String getUser() {
		return myProperties.getProperty(DB_USER);
	}
	
	/**
	 * Gibt das Passwort für die Datenbank zurück.
	 * @return Das Passwort für die Datenbank
	 */
	public String getPassword() {
		return myProperties.getProperty(DB_PASS);
	}
}
