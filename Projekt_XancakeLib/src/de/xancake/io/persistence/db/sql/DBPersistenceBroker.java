package de.xancake.io.persistence.db.sql;

import de.xancake.io.db.sql.ConnectionPool;
import de.xancake.io.db.sql.config.DBConfiguration_I;
import de.xancake.io.persistence.Creator_I;
import de.xancake.io.persistence.Loader_I;
import de.xancake.io.persistence.PersistenceBroker_I;
import de.xancake.io.persistence.Storer_I;
import de.xancake.io.persistence.bind.TypeBinding_I;

public class DBPersistenceBroker implements PersistenceBroker_I {
	private ConnectionPool myConnectionPool;
	
	public DBPersistenceBroker(DBConfiguration_I configuration) {
		myConnectionPool = new ConnectionPool(configuration);
	}
	
	public DBPersistenceBroker(DBConfiguration_I configuration, int maxConnections) {
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
