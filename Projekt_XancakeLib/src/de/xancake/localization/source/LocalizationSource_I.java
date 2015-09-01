package de.xancake.localization.source;

import java.util.Collection;

public interface LocalizationSource_I {
	/**
	 * Checks if this source contains the given key.
	 * @param key The key to check for
	 * @return {@code true}, if this source contains the given key, {@code false} otherwise
	 */
	boolean containsKey(String key);
	
	/**
	 * Returns the value for the given key.
	 * @param key The key to resolve
	 * @return The value for the given key or {@code null} if there is no value
	 */
	String getValue(String key);
	
	/**
	 * Returns a collection of all keys contained in this source.
	 * @return A collection of all keys contained in this source
	 */
	Collection<String> getKeys();
}
