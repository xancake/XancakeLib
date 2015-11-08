package de.xancake.domain.kunde;

import java.io.IOException;
import de.xancake.domain.adresse.DAdresse;
import de.xancake.io.persistence.domain.DomainObject;

public interface DKunde extends DomainObject {
	
	DAdresse getAdresse() throws IOException;
	
	
	void setAdresse(DAdresse adresse);
}
