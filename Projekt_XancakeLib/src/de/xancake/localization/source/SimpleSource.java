package de.xancake.localization.source;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SimpleSource implements LocalizationSource_I {
	private Map<String, String> myKeyValueMap;
	
	public SimpleSource() {
		this(new HashMap<String, String>());
	}
	
	public SimpleSource(Map<String, String> localizationMap) {
		myKeyValueMap = localizationMap;
	}
	
	@Override
	public boolean containsKey(String key) {
		return myKeyValueMap.containsKey(key);
	}
	
	@Override
	public String getValue(String key) {
		return myKeyValueMap.get(key);
	}
	
	@Override
	public Collection<String> getKeys() {
		return myKeyValueMap.keySet();
	}
	
	public void put(String key, String value) {
		myKeyValueMap.put(key, value);
	}
}
