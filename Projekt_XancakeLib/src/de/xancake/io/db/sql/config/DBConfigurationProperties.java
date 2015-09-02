package de.xancake.io.db.sql.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Repräsentiert die Konfiguration der Verbindungsdaten einer Datenbankverbindung.
 * Arbeitet mit {@link Properties}.
 * 
 * @author Lars 'Xancake' Nielsen
 */
public class DBConfigurationProperties implements DBConfiguration_I {
	private static final String DB_DRIVER = "db.driver";
	private static final String DB_HOST   = "db.host";
	private static final String DB_USER   = "db.user";
	private static final String DB_PASS   = "db.pass";
	
	private Properties myProperties;
	
	/**
	 * Initialisiert eine neue Datenbankkonfiguration aus der übergebenen Ressource.
	 * Die übergebene Ressource muss eine {@link Properties}-Datei sein und sich im Klassenpfad befinden.
	 * @param ressource Der Name der zu ladenden Ressource
	 * @throws ClassNotFoundException Wenn die JDBC-Treiberklasse nicht geladen werden konnte
	 * @throws IOException Wenn ein Fehler beim Lesen der Konfiguration aus dem InputStream auftritt
	 * @throws IllegalArgumentException Wenn die Ressource nicht gefunden werden konnte
	 */
	public DBConfigurationProperties(String ressource) throws ClassNotFoundException, IOException {
		this(DBConfigurationProperties.class.getClassLoader().getResourceAsStream(ressource));
	}
	
	/**
	 * Initialisiert eine neue Datenbankkonfiguration aus einem {@link InputStream}.
	 * Die Daten aus dem InputStream werden entsprechend von {@link Properties}
	 * verarbeitet und folgen somit den gleichen Formatierungsregeln.
	 * @param config Der {@link InputStream} zum Lesen der Konfiguration
	 * @throws ClassNotFoundException Wenn die JDBC-Treiberklasse nicht geladen werden konnte
	 * @throws IOException Wenn ein Fehler beim Lesen der Konfiguration aus dem InputStream auftritt
	 * @throws IllegalArgumentException Wenn der übergebene InputStream {@code null} ist
	 */
	public DBConfigurationProperties(InputStream config) throws ClassNotFoundException, IOException {
		if(config == null) {
			throw new IllegalArgumentException("Die Datenbank-Konfigurations-Datei konnte nicht gefunden werden");
		}
		myProperties = new Properties();
		myProperties.load(config);
		
		// JDBC-Treiberklasse laden
		Class.forName(getDriver());
	}
	
	/**
	 * Initialisiert eine neue Datenbankkonfiguration aus einer {@link Properties} und übernimmt die Konfiguration.
	 * @param properties Die Properties
	 * @throws ClassNotFoundException Wenn die JDBC-Treiberklasse nicht geladen werden konnte
	 */
	public DBConfigurationProperties(Properties properties) throws ClassNotFoundException {
		myProperties = properties;
		
		// JDBC-Treiberklasse laden
		Class.forName(getDriver());
	}
	
	@Override
	public String getDriver() {
		return myProperties.getProperty(DB_DRIVER);
	}
	
	@Override
	public String getHost() {
		return myProperties.getProperty(DB_HOST);
	}
	
	@Override
	public String getUser() {
		return myProperties.getProperty(DB_USER);
	}
	
	@Override
	public String getPassword() {
		return myProperties.getProperty(DB_PASS);
	}
}
