package de.xancake.localization;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import de.xancake.localization.source.LocalizationSource_I;

public class Localizer {
	private List<LocalizationSource_I> _sources;
	
	public Localizer() {
		this(new ArrayList<LocalizationSource_I>());
	}
	
	public Localizer(List<LocalizationSource_I> sources) {
		_sources = Objects.requireNonNull(sources);
	}
	
	public boolean hasLocalizationSources() {
		return !_sources.isEmpty();
	}
	
	public List<LocalizationSource_I> getLocalizationSources() {
		return Collections.unmodifiableList(_sources);
	}
	
	public void addLocalizationSource(LocalizationSource_I source) {
		_sources.add(source);
	}
	
	public void clearLocalizationSources() {
		_sources.clear();
	}
	
	public String localize(String key, Object... values) {
		return MessageFormat.format(fetchTranslation(key), values);
	}
	
	private String fetchTranslation(String key) {
		for(LocalizationSource_I source : _sources) {
			if(source.containsKey(key)) {
				return source.getValue(key);
			}
		}
		throw new LocalizationException(key);
	}
}
