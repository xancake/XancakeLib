package de.xancake.persistence.db.sql;

import de.xancake.io.db.sql.ConnectionPool;
import de.xancake.io.db.sql.DBConfiguration;
import de.xancake.persistence.Creator_I;
import de.xancake.persistence.Loader_I;
import de.xancake.persistence.PersistenceBroker_I;
import de.xancake.persistence.Storer_I;
import de.xancake.persistence.bind.TypeBinding_I;

public class DBPersistenceBroker implements PersistenceBroker_I {
	private ConnectionPool myConnectionPool;
	
	public DBPersistenceBroker(DBConfiguration configuration) {
		myConnectionPool = new ConnectionPool(configuration);
	}
	
	public DBPersistenceBroker(DBConfiguration configuration, int maxConnections) {
		myConnectionPool = new ConnectionPool(configuration, maxConnections);
	}
	
	@Override
	public <T> Loader_I<T> loader(TypeBinding_I<T> binding) {
		return new DBLoader<>(myConnectionPool, binding);
	}
	
	@Override
	public <T> Storer_I<T> storer(TypeBinding_I<T> binding) {
		return new DBStorer<T>(myConnectionPool, binding);
	}
	
	@Override
	public <T> Creator_I<T> creator(TypeBinding_I<T> binding) {
		return new DBCreator<T>(myConnectionPool, binding);
	}
}
