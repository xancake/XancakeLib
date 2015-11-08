package de.xancake.io.persistence.domain.produkt;

import de.xancake.io.persistence.domain.DomainObject;
import de.xancake.io.persistence.domain.kunde.DKunde;

public interface DProdukt extends DomainObject {
	/**
	 * Der übergebene Kunde kauft dieses Produkt.
	 * @param kunde Der Kunde der dieses Produkt kauft
	 */
	void kaufen(DKunde kunde);
}
