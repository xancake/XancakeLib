package de.xancake.localization.source;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesSource implements LocalizationSource_I {
	private ResourceBundle myResourceBundle;
	
	public PropertiesSource(String baseName) {
		this(ResourceBundle.getBundle(baseName));
	}
	
	public PropertiesSource(String baseName, Locale locale) {
		this(ResourceBundle.getBundle(baseName, locale));
	}
	
	public PropertiesSource(ResourceBundle bundle) {
		myResourceBundle = bundle;
	}
	
	@Override
	public boolean containsKey(String key) {
		return myResourceBundle.containsKey(key);
	}
	
	@Override
	public String getValue(String key) {
		return myResourceBundle.getString(key);
	}
	
	@Override
	public Collection<String> getKeys() {
		return myResourceBundle.keySet();
	}
}
