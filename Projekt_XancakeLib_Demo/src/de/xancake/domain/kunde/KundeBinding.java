package de.xancake.domain.kunde;

import de.xancake.domain.adresse.Adresse;
import de.xancake.io.persistence.bind.SimpleAttributeBinding;
import de.xancake.io.persistence.bind.AbstractTypeBinding;

public class KundeBinding extends AbstractTypeBinding<Kunde> {
	private static final String ENTITY = "Kunde";
	private static final SimpleAttributeBinding<String>  ATT_VORNAME  = new SimpleAttributeBinding<>("vorname", String.class, false);
	private static final SimpleAttributeBinding<String>  ATT_NACHNAME = new SimpleAttributeBinding<>("nachname", String.class, false);
	private static final SimpleAttributeBinding<Adresse> ATT_ADRESSE  = new SimpleAttributeBinding<>("adresse", Adresse.class, true);
	
	public static final KundeBinding INSTANCE = new KundeBinding();
	
	private KundeBinding() {
	    super(ENTITY, new Factory<Kunde>() {
			@Override
            public Kunde create() {
	            return new Kunde();
            }
	    });
	    addAttribute(ATT_VORNAME);
	    addAttribute(ATT_NACHNAME);
	    addAttribute(ATT_ADRESSE);
    }
}
