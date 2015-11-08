package de.xancake.io.persistence.domain;

import de.xancake.io.db.sql.config.DBConfigurationProperties;
import de.xancake.io.db.sql.config.DBConfiguration_I;
import de.xancake.io.persistence.broker.PersistenceBroker;
import de.xancake.io.persistence.broker.db.DBPersistenceBroker;
import de.xancake.io.persistence.domain.kunde.DKunde;
import de.xancake.io.persistence.domain.kunde.DKundeImpl;

public class DomainObjectDemo {
	public static void main(String[] args) {
		DBConfiguration_I configuration = new DBConfigurationProperties("db.properties");
		PersistenceBroker broker = new DBPersistenceBroker(configuration);
		
		DKunde kunde = new DKundeImpl(broker, id);
		
		
		
	}
}
