package de.xancake.io.persistence;

import de.xancake.io.persistence.bind.TypeBinding_I;

public interface PersistenceBroker_I {
	
	int ID_UNPERSISTED = -1;
	
	
	<T> Loader_I<T> loader(TypeBinding_I<T> binding);
	
	
	<T> Storer_I<T> storer(TypeBinding_I<T> binding);
	
	
	<T> Creator_I<T> creator(TypeBinding_I<T> binding);
}
