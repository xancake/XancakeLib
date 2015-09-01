package de.xancake.localization;

import java.util.Arrays;
import java.util.List;

import de.xancake.localization.source.LocalizationSource_I;

public class LocalizerFactory {
	private LocalizerFactory() {}
	
	public Localizer createLocalizer(String... sourceParameters) {
		return createLocalizer(Arrays.asList(sourceParameters));
	}
	
	public Localizer createLocalizer(List<String> sourceParameters) {
		Localizer localizer = new Localizer();
		for(String param : sourceParameters) {
			localizer.addLocalizationSource(createLocalizationSource(param));
		}
		return localizer;
	}
	
	public LocalizationSource_I createLocalizationSource(String param) {
		LocalizationSource_I source = null;
		
		
		
		return source;
	}
	
	public static LocalizerFactory getInstance() {
		return Holder.INSTANCE;
	}
	
	private static class Holder {
		private static final LocalizerFactory INSTANCE = new LocalizerFactory();
	}
}
