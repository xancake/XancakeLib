package de.xancake.io.persistence.domain.adresse;

import java.io.IOException;
import de.xancake.io.persistence.broker.PersistenceBroker;
import de.xancake.io.persistence.domain.AbstractDomainObject;

public class DAdresseImpl extends AbstractDomainObject<Adresse> implements DAdresse {
	
	public DAdresseImpl(PersistenceBroker broker, int id) throws IOException {
		super(broker, AdresseBinding.INSTANCE, id);
	}
	
	public DAdresseImpl(PersistenceBroker broker, Adresse object) {
		super(broker, AdresseBinding.INSTANCE, object);
	}
}
