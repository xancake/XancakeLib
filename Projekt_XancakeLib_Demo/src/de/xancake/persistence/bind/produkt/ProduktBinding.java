package de.xancake.persistence.bind.produkt;

import java.math.BigDecimal;
import de.xancake.io.persistence.bind.AttributeBinding;
import de.xancake.io.persistence.bind.Binding;

public class ProduktBinding extends Binding<Produkt> {
	private static final String ENTITY = "Produkt";
	
	private static final AttributeBinding<Integer> ATT_ID         = new AttributeBinding<>("id", Integer.class, false);
	private static final AttributeBinding<String> ATT_NUMMER      = new AttributeBinding<>("nummer", String.class, false);
	private static final AttributeBinding<String> ATT_BEZEICHNUNG = new AttributeBinding<>("bezeichnung", String.class, false);
	private static final AttributeBinding<BigDecimal> ATT_PREIS   = new AttributeBinding<>("preis", BigDecimal.class, false);
	
	public ProduktBinding() {
	    super(ENTITY, ATT_ID, new Factory<Produkt>() {
			@Override
            public Produkt create() {
	            return new Produkt();
            }
	    });
	    addAttribute(ATT_NUMMER);
	    addAttribute(ATT_BEZEICHNUNG);
	    addAttribute(ATT_PREIS);
    }
}
