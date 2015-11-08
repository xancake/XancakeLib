package de.xancake.io.persistence.broker;

import java.io.IOException;
import de.xancake.io.persistence.bind.TypeBinding;

public interface PersistenceBroker {
	
	<T> T load(TypeBinding<T> binding, int id) throws IOException;
	
	
	<T> int acquireId(TypeBinding<T> binding) throws IOException;
	
	
	<T> void insert(TypeBinding<T> binding, int id, T object) throws IOException;
	
	
	<T> void update(TypeBinding<T> binding, int id, T object) throws IOException;
	
	
	<T> void delete(TypeBinding<T> binding, int id) throws IOException;
}
