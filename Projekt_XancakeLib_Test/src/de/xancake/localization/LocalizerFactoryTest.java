package de.xancake.localization;

import static org.junit.Assert.*;

import org.junit.Test;

import de.xancake.localization.source.DatabaseSource;
import de.xancake.localization.source.LocalizationSource_I;
import de.xancake.localization.source.PropertiesSource;
import de.xancake.localization.source.SimpleSource;

public class LocalizerFactoryTest {
	@Test
	public void testCreateLocalizationSource_Simple() {
		assertLocalizationSource(SimpleSource.class, "");
	}
	
	@Test
	public void testCreateLocalizationSource_Properties() {
		assertLocalizationSource(PropertiesSource.class, "");
	}
	
	@Test
	public void testCreateLocalizationSource_Database() {
		assertLocalizationSource(DatabaseSource.class, "");
	}
	
	private void assertLocalizationSource(Class<? extends LocalizationSource_I> expectedSource, String paramString) {
		LocalizationSource_I source = LocalizerFactory.getInstance().createLocalizationSource(paramString);
		assertTrue("The source is expected to be an instance of '" + expectedSource.getSimpleName() + "' but was '" + source.getClass().getSimpleName() + "'", expectedSource.isInstance(source));
	}
}
