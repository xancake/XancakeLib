package de.xancake.domain.produkt;

import java.io.IOException;
import de.xancake.domain.kunde.DKunde;
import de.xancake.io.persistence.broker.PersistenceBroker;
import de.xancake.io.persistence.domain.AbstractDomainObject;

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
