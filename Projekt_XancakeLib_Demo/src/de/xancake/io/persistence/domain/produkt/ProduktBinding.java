package de.xancake.io.persistence.domain.produkt;

import java.math.BigDecimal;
import de.xancake.io.persistence.bind.SimpleAttributeBinding;
import de.xancake.io.persistence.bind.AbstractTypeBinding;

public class ProduktBinding extends AbstractTypeBinding<Produkt> {
	private static final String ENTITY = "Produkt";
	private static final SimpleAttributeBinding<String>     ATT_NUMMER      = new SimpleAttributeBinding<>("nummer", String.class, false);
	private static final SimpleAttributeBinding<String>     ATT_BEZEICHNUNG = new SimpleAttributeBinding<>("bezeichnung", String.class, false);
	private static final SimpleAttributeBinding<BigDecimal> ATT_PREIS       = new SimpleAttributeBinding<>("preis", BigDecimal.class, false);
	private static final SimpleAttributeBinding<BigDecimal> ATT_BESTAND     = new SimpleAttributeBinding<>("bestand", BigDecimal.class, false);
	
	public static final ProduktBinding INSTANCE = new ProduktBinding();
	
	private ProduktBinding() {
	    super(ENTITY, Produkt::new);
	    addAttribute(ATT_NUMMER);
	    addAttribute(ATT_BEZEICHNUNG);
	    addAttribute(ATT_PREIS);
	    addAttribute(ATT_BESTAND);
    }
}
