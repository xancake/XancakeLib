package de.xancake.persistence;

import java.io.IOException;

public interface Storer_I<T> {
	
	void store(T object) throws IOException;
}
