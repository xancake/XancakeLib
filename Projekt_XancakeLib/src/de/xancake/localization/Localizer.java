package de.xancake.localization;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.xancake.localization.source.LocalizationSource_I;

public class Localizer {
	private List<LocalizationSource_I> myLocalizationSources;
	
	public Localizer() {
		this(new ArrayList<LocalizationSource_I>());
	}
	
	public Localizer(List<LocalizationSource_I> sources) {
		setLocalizationSources(sources);
	}
	
	public boolean hasLocalizationSources() {
		return !myLocalizationSources.isEmpty();
	}
	
	public List<LocalizationSource_I> getLocalizationSources() {
		return Collections.unmodifiableList(myLocalizationSources);
	}
	
	public void setLocalizationSources(List<LocalizationSource_I> sources) {
		if(sources == null) {
			throw new IllegalArgumentException("The Sources-List may not be null!");
		}
		myLocalizationSources = sources;
	}
	
	public void addLocalizationSource(LocalizationSource_I source) {
		myLocalizationSources.add(source);
	}
	
	public void clearLocalizationSources() {
		myLocalizationSources.clear();
	}
	
	public String localize(String key, Object... values) {
		return MessageFormat.format(fetchTranslation(key), values);
	}
	
	private String fetchTranslation(String key) {
		for(LocalizationSource_I source : myLocalizationSources) {
			if(source.containsKey(key)) {
				return source.getValue(key);
			}
		}
		throw new LocalizationException(key);
	}
}
