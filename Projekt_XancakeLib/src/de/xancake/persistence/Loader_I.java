package de.xancake.persistence;

import java.io.IOException;

public interface Loader_I<T> {
	
	T load(int id) throws IOException;
}
