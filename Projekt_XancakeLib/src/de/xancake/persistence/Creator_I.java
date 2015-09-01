package de.xancake.persistence;

import java.io.IOException;

public interface Creator_I<T> {

	void createPersistable() throws IOException;
	
	
	void dropPersistable() throws IOException;
}
