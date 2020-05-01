package de.xancake.io.persistence.domain.adresse;

import de.xancake.io.persistence.bind.SimpleAttributeBinding;
import de.xancake.io.persistence.bind.AbstractTypeBinding;

public class AdresseBinding extends AbstractTypeBinding<Adresse> {
	private static final String ENTITY = "Adresse";
	private static final SimpleAttributeBinding<String>  ATT_ORT     = new SimpleAttributeBinding<>("ort", String.class, false);
	private static final SimpleAttributeBinding<String>  ATT_PLZ     = new SimpleAttributeBinding<>("postleitzahl", String.class, false);
	private static final SimpleAttributeBinding<String>  ATT_STRASSE = new SimpleAttributeBinding<>("strasse", String.class, false);
	
	public static final AdresseBinding INSTANCE = new AdresseBinding();
	
	private AdresseBinding() {
	    super(ENTITY, Adresse::new);
	    addAttribute(ATT_ORT);
	    addAttribute(ATT_PLZ);
	    addAttribute(ATT_STRASSE);
    }
}
