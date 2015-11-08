package de.xancake.io.persistence.bind;

import java.util.List;

public interface TypeBinding<T> {
	/**
	 * Gibt den Namen der gebundenen Entität zurück.
	 * @return Der Name der Entität
	 */
	String getEntityName();
	
	/**
	 * Gibt die Namen der persistierten Attribute zurück.
	 * @return Die Namen der persistierten Attribute
	 */
	List<SimpleAttributeBinding<?>> getAttributes();
	
	/**
	 * Gibt den Wert des Attributs einer Entität zurück.
	 * @param object Die Entität, deren Attribut erfragt werden soll
	 * @param attribute Das Attribut das erfragt werden soll
	 * @return Der Wert des Attributs
	 */
	<V> V get(T object, SimpleAttributeBinding<V> attribute);
	
	/**
	 * Setzt den Wert des Attributes einer Entität.
	 * @param object Die Entität deren Attribut gesetzt werden soll
	 * @param attribute Das Attribut das gesetzt werden soll
	 * @param value Der Wert den das Attribut erhalten soll
	 */
	<V> void set(T object, SimpleAttributeBinding<V> attribute, V value);
	
	/**
	 * Erzeugt ein neues Objekt der gebundenen Entität.
	 * @return Das neue Objekt
	 */
	T create();
}
