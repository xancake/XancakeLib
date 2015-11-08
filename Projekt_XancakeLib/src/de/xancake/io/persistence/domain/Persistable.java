package de.xancake.io.persistence.domain;

import java.io.IOException;
import de.xancake.io.persistence.broker.PersistenceBroker;

public interface Persistable<T> {
	
	int ID_UNPERSISTED = -1;
	
	
	int getId();
	
	
	boolean isUnpersisted();
	
	
	T getObject();
	
	
	void load(PersistenceBroker _broker) throws IOException;
	
	
	void store(PersistenceBroker _broker) throws IOException;
	
	
	void delete(PersistenceBroker _broker) throws IOException;
}
