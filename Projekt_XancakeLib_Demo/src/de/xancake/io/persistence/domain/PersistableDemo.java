package de.xancake.io.persistence.domain;

import java.math.BigDecimal;
import de.xancake.io.db.sql.config.DBConfigurationProperties;
import de.xancake.io.db.sql.config.DBConfiguration_I;
import de.xancake.io.persistence.broker.PersistenceBroker;
import de.xancake.io.persistence.broker.db.DBPersistenceBroker;
import de.xancake.io.persistence.domain.adresse.Adresse;
import de.xancake.io.persistence.domain.adresse.AdresseBinding;
import de.xancake.io.persistence.domain.kunde.Kunde;
import de.xancake.io.persistence.domain.kunde.KundeBinding;
import de.xancake.io.persistence.domain.produkt.Produkt;
import de.xancake.io.persistence.domain.produkt.ProduktBinding;

public class PersistableDemo {
	public static void main(String[] args) throws Exception {
		DBConfiguration_I config = new DBConfigurationProperties("db.properties");
		PersistenceBroker broker = new DBPersistenceBroker(config);
		
		
		Produkt produkt = new Produkt();
		produkt.setNummer("P-238811-TX");
		produkt.setBezeichnung("Computer-Maus");
		produkt.setPreis(new BigDecimal("23.99"));
		produkt.setBestand(new BigDecimal(2500));
		
		Persistable<Produkt> pProdukt = new PersistableImpl<>(ProduktBinding.INSTANCE, produkt);
		pProdukt.store(broker);
		System.out.println("Produkt '" + pProdukt.getId() + "' gespeichert!");
		
		Persistable<Produkt> pProdukt2 = new PersistableImpl<>(ProduktBinding.INSTANCE, pProdukt.getId());
		pProdukt2.load(broker);
		Produkt produkt2 = pProdukt2.getObject();
		System.out.println("Produkt '" + pProdukt2.getId() + "' geladen: " + produkt2.toString());
		
		
		Adresse adresse = new Adresse();
		adresse.setOrt("Hamburg");
		adresse.setPostleitzahl("22159");
		adresse.setStrasse("Kleine Straße 123");
		
		Persistable<Adresse> pAdresse = new PersistableImpl<>(AdresseBinding.INSTANCE, adresse);
		pAdresse.store(broker);
		System.out.println("Adresse '" + pAdresse.getId() + "' gespeichert!");
		
		Persistable<Adresse> pAdresse2 = new PersistableImpl<>(AdresseBinding.INSTANCE, pAdresse.getId());
		pAdresse2.load(broker);
		Adresse adresse2 = pAdresse2.getObject();
		System.out.println("Adresse '" + pAdresse2.getId() + "' geladen: " + adresse2.toString());
		
		
		Kunde kunde = new Kunde();
		kunde.setVorname("Lars");
		kunde.setNachname("Nielsen");
		kunde.setAdresse(pAdresse.getId());
		
		Persistable<Kunde> pKunde = new PersistableImpl<>(KundeBinding.INSTANCE, kunde);
		pKunde.store(broker);
		System.out.println("Kunde '" + pKunde.getId() + "' gespeichert!");
		
		Persistable<Kunde> pKunde2 = new PersistableImpl<>(KundeBinding.INSTANCE, pKunde.getId());
		pKunde2.load(broker);
		Kunde kunde2 = pKunde2.getObject();
		System.out.println("Kunde '" + pKunde2.getId() + "' geladen: " + kunde2.toString());
		
	}
}
