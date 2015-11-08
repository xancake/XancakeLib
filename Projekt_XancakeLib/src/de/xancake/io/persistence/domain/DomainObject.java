package de.xancake.io.persistence.domain;

import java.io.IOException;

public interface DomainObject {
	
	Integer getId();
	
	
	void store() throws IOException;
	
	
	void delete() throws IOException;
}
