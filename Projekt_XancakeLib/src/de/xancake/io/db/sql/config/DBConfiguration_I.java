package de.xancake.io.db.sql.config;

/**
 * Repräsentiert die Konfiguration der Verbindungsdaten einer Datenbankverbindung.
 * 
 * @author Lars 'Xancake' Nielsen
 */
public interface DBConfiguration_I {
	/**
	 * Gibt den Treibernamen für die Datenbank zurück.
	 * @return Der Treibername für die Datenbank
	 */
	String getDriver();
	
	/**
	 * Gibt den Host / Connection-String für die Datenbank zurück.
	 * @return Der Host / Connection-String für die Datenbank
	 */
	String getHost();
	
	/**
	 * Gibt den Benutzernamen für die Datenbank zurück.
	 * @return Der Benutzername für die Datenbank
	 */
	String getUser();
	
	/**
	 * Gibt das Passwort für die Datenbank zurück.
	 * @return Das Passwort für die Datenbank
	 */
	String getPassword();
}
