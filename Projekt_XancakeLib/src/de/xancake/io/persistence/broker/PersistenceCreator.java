package de.xancake.io.persistence.broker;

import java.io.IOException;
import de.xancake.io.persistence.bind.TypeBinding;

public interface PersistenceCreator {
	
	void createPersistable(TypeBinding<?> binding) throws IOException;
	
	
	void dropPersistable(TypeBinding<?> binding) throws IOException;
}
