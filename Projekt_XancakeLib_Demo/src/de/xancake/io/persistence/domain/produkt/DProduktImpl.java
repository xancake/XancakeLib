package de.xancake.io.persistence.domain.produkt;

import java.io.IOException;
import de.xancake.io.persistence.broker.PersistenceBroker;
import de.xancake.io.persistence.domain.AbstractDomainObject;
import de.xancake.io.persistence.domain.kunde.DKunde;

public class DProduktImpl extends AbstractDomainObject<Produkt> implements DProdukt {
	
	public DProduktImpl(PersistenceBroker broker, int id) throws IOException {
		super(broker, ProduktBinding.INSTANCE, id);
	}
	
	public DProduktImpl(PersistenceBroker broker, Produkt object) {
		super(broker, ProduktBinding.INSTANCE, object);
	}
	
	@Override
	public void kaufen(DKunde kunde) {
		// TODO Auto-generated method stub
		
	}
}
