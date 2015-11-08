package de.xancake.domain.produkt;

import de.xancake.domain.kunde.DKunde;
import de.xancake.io.persistence.domain.DomainObject;

public interface DProdukt extends DomainObject {
	/**
	 * Der übergebene Kunde kauft dieses Produkt.
	 * @param kunde Der Kunde der dieses Produkt kauft
	 */
	void kaufen(DKunde kunde);
}
