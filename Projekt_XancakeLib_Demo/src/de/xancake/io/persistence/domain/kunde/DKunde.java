package de.xancake.io.persistence.domain.kunde;

import java.io.IOException;
import de.xancake.io.persistence.domain.DomainObject;
import de.xancake.io.persistence.domain.adresse.DAdresse;

public interface DKunde extends DomainObject {
	
	DAdresse getAdresse() throws IOException;
	
	
	void setAdresse(DAdresse adresse);
}
