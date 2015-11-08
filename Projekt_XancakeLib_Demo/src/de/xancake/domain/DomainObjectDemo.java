package de.xancake.domain;

import de.xancake.domain.kunde.DKunde;
import de.xancake.domain.kunde.DKundeImpl;
import de.xancake.io.db.sql.config.DBConfigurationProperties;
import de.xancake.io.db.sql.config.DBConfiguration_I;
import de.xancake.io.persistence.broker.PersistenceBroker;
import de.xancake.io.persistence.broker.db.DBPersistenceBroker;

public class DomainObjectDemo {
	public static void main(String[] args) {
		DBConfiguration_I configuration = new DBConfigurationProperties("db.properties");
		PersistenceBroker broker = new DBPersistenceBroker(configuration);
		
		DKunde kunde = new DKundeImpl(broker, id);
		
		
		
	}
}
