package de.xancake.persistence.bind.produkt;

import java.math.BigDecimal;
import de.xancake.io.db.sql.config.DBConfigurationSingleton;
import de.xancake.io.db.sql.config.DBConfiguration_I;
import de.xancake.persistence.Creator_I;
import de.xancake.persistence.Loader_I;
import de.xancake.persistence.PersistenceBroker_I;
import de.xancake.persistence.Storer_I;
import de.xancake.persistence.bind.AttributeBinding;
import de.xancake.persistence.db.sql.DBPersistenceBroker;

public class ProduktDemo {
	public static void main(String[] args) throws Exception {
		Produkt produkt = new Produkt();
		produkt.setNummer("P-238811-TX");
		produkt.setBezeichnung("Computer-Maus");
		produkt.setPreis(new BigDecimal(2500));
		
		
		
		ProduktBinding produktBinding = new ProduktBinding();
		System.out.println(produktBinding.getIDAttribute().getName() + ": " + produktBinding.get(produkt, produktBinding.getIDAttribute()));
		for(AttributeBinding<?> attribute : produktBinding.getAttributes()) {
			System.out.println(attribute.getName() + ": " + produktBinding.get(produkt, attribute));
		}
		System.out.println();
		
		
		
		DBConfiguration_I configuration = DBConfigurationSingleton.getInstance().getConfiguration();
		PersistenceBroker_I broker = new DBPersistenceBroker(configuration);
		
		Creator_I<Produkt> produktCreator = broker.creator(produktBinding);
		produktCreator.createPersistable();
		
		Storer_I<Produkt> produktStorer = broker.storer(produktBinding);
		produktStorer.store(produkt);
		int id = produkt.getId();
		System.out.println("Produkt wurde unter ID '" + id + "' gespeichert!");
		
		Loader_I<Produkt> produktLoader = broker.loader(produktBinding);
		Produkt produkt2 = produktLoader.load(1002);
		System.out.println("Das Produkt mit der ID '1002' wurde geladen!");
		System.out.println(produkt2.toString());
		
		produktCreator.dropPersistable();
	}
}
