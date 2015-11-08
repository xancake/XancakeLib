package de.xancake.io.persistence.bind;

import java.util.List;

public interface TypeBinding_I<T> {
	/**
	 * Gibt den Namen der gebundenen Entit�t zur�ck.
	 * @return Der Name der Entit�t
	 */
	String getEntityName();
	
	/**
	 * Gibt den Namen des ID-Attributs zur�ck.
	 * @return Der Name des ID-Attributs
	 */
	AttributeBinding<Integer> getIDAttribute();
	
	/**
	 * Gibt die Namen der persistierten Attribute zur�ck.
	 * @return Die Namen der persistierten Attribute
	 */
	List<AttributeBinding<?>> getAttributes();
	
	/**
	 * Gibt den Wert des Attributs einer Entit�t zur�ck.
	 * @param object Die Entit�t, deren Attribut erfragt werden soll
	 * @param attribute Das Attribut das erfragt werden soll
	 * @return Der Wert des Attributs
	 */
	<V> V get(T object, AttributeBinding<V> attribute);
	
	/**
	 * Setzt den Wert des Attributes einer Entit�t.
	 * @param object Die Entit�t deren Attribut gesetzt werden soll
	 * @param attribute Das Attribut das gesetzt werden soll
	 * @param value Der Wert den das Attribut erhalten soll
	 */
	<V> void set(T object, AttributeBinding<V> attribute, V value);
	
	/**
	 * Erzeugt ein neues Objekt der gebundenen Entit�t.
	 * @return Das neue Objekt
	 */
	T create();
}
