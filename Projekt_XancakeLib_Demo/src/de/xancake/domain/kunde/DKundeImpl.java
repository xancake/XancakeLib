package de.xancake.domain.kunde;

import java.io.IOException;
import de.xancake.domain.adresse.DAdresseImpl;
import de.xancake.domain.adresse.DAdresse;
import de.xancake.io.persistence.broker.PersistenceBroker;
import de.xancake.io.persistence.domain.AbstractDomainObject;

public class DKundeImpl extends AbstractDomainObject<Kunde> implements DKunde {
	private DAdresse _dadresse;
	
	public DKundeImpl(PersistenceBroker broker, int id) throws IOException {
	    super(broker, KundeBinding.INSTANCE, id);
    }

	public DKundeImpl(PersistenceBroker broker, Kunde object) {
	    super(broker, KundeBinding.INSTANCE, object);
    }
	
	@Override
    public DAdresse getAdresse() throws IOException {
		if(_dadresse == null) {
            _dadresse = new DAdresseImpl(getBroker(), getObject().getAdresse());
		}
	    return _dadresse;
    }
	
	@Override
	public void setAdresse(DAdresse adresse) {
		_dadresse = adresse;
		getObject().setAdresse(_dadresse.getId());
	}
}
