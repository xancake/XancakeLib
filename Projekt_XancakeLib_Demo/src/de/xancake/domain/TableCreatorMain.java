package de.xancake.domain;

import java.util.Iterator;
import java.util.LinkedList;
import de.xancake.domain.adresse.AdresseBinding;
import de.xancake.domain.kunde.KundeBinding;
import de.xancake.domain.produkt.ProduktBinding;
import de.xancake.io.db.sql.config.DBConfigurationSingleton;
import de.xancake.io.db.sql.config.DBConfiguration_I;
import de.xancake.io.persistence.bind.TypeBinding;
import de.xancake.io.persistence.broker.PersistenceCreator;
import de.xancake.io.persistence.broker.db.DBCreator;

public class TableCreatorMain {
	private static final LinkedList<TypeBinding<?>> BINDINGS = new LinkedList<TypeBinding<?>>();
	
	static {
		BINDINGS.add(AdresseBinding.INSTANCE);
		BINDINGS.add(KundeBinding.INSTANCE);
		BINDINGS.add(ProduktBinding.INSTANCE);
	}
	
	private static void drop(PersistenceCreator creator) throws Exception {
		for(Iterator<TypeBinding<?>> iter = BINDINGS.descendingIterator(); iter.hasNext(); ) {
			creator.dropPersistable(iter.next());
		}
	}
	
	private static void create(PersistenceCreator creator) throws Exception {
		for(Iterator<TypeBinding<?>> iter = BINDINGS.iterator(); iter.hasNext(); ) {
			creator.createPersistable(iter.next());
		}
	}
	
	public static void main(String[] args) throws Exception {
		DBConfiguration_I configuration = DBConfigurationSingleton.getInstance().getConfiguration();
		PersistenceCreator creator = new DBCreator(configuration);
		drop(creator);
		create(creator);
	}
}
