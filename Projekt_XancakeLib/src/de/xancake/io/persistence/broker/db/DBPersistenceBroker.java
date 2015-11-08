package de.xancake.io.persistence.broker.db;

import static de.xancake.io.persistence.broker.db.DBPersistenceUtils.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Objects;
import de.xancake.io.db.sql.ConnectionPool;
import de.xancake.io.db.sql.ConnectionPool.PreparedStatementCallback;
import de.xancake.io.db.sql.ConnectionPool.ResultSetCallback;
import de.xancake.io.db.sql.config.DBConfiguration_I;
import de.xancake.io.persistence.bind.SimpleAttributeBinding;
import de.xancake.io.persistence.bind.TypeBinding;
import de.xancake.io.persistence.broker.PersistenceBroker;
import de.xancake.io.persistence.broker.db.statement.DBStatements;

public class DBPersistenceBroker implements PersistenceBroker {
	private ConnectionPool _connectionPool;
	
	public DBPersistenceBroker(DBConfiguration_I configuration) {
		this(new ConnectionPool(configuration));
	}
	
	public DBPersistenceBroker(DBConfiguration_I configuration, int maxConnections) {
		this(new ConnectionPool(configuration, maxConnections));
	}
	
	public DBPersistenceBroker(ConnectionPool connectionPool) {
		_connectionPool = Objects.requireNonNull(connectionPool);
	}
	
	@Override
	public <T> T load(final TypeBinding<T> binding, final int id) throws IOException {
		String sql = DBStatements.select(binding.getEntityName());
		
		PreparedStatementCallback stmtCallback = new PreparedStatementCallback() {
			@Override
			public void callback(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, id);
			}
		};
		
		ResultSetCallback<T> rsCallback = new ResultSetCallback<T>() {
			@Override
			@SuppressWarnings({"rawtypes", "unchecked"})
			public T callback(ResultSet rs) throws SQLException {
				if(rs.next()) {
					T object = binding.create();
					for(SimpleAttributeBinding attribute : binding.getAttributes()) {
						binding.set(object, attribute, rs.getObject(attribute.getName()));
					}
					return object;
				}
				return null;
			}
		};
		
		try {
			return _connectionPool.executePreparedStatement(sql, stmtCallback, rsCallback);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim Laden eines Datensatzes aus '" + binding.getEntityName() + "'", e);
		}
	}
	
	@Override
    public <T> int acquireId(TypeBinding<T> binding) throws IOException {
		String sql = DBStatements.acquireId(binding.getEntityName());
		
		ResultSetCallback<Integer> rsCallback = new ResultSetCallback<Integer>() {
			@Override
			public Integer callback(ResultSet rs) throws SQLException {
				rs.next();
				return rs.getInt(1);
			}
		};
		
		try {
			return _connectionPool.executeStatement(sql, rsCallback);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim Ermitteln eines Identen für '" + binding.getEntityName() + "'", e);
		}
    }
	
	@Override
	public <T> void insert(final TypeBinding<T> binding, final int id, final T object) throws IOException {
		String sql = DBStatements.insert(binding.getEntityName(), getAttributes(binding));
		
		PreparedStatementCallback stmtCallback = new PreparedStatementCallback() {
			@Override
			public void callback(PreparedStatement stmt) throws SQLException {
				int i=1;
				stmt.setInt(i++, id);
				for(Iterator<SimpleAttributeBinding<?>> iter = binding.getAttributes().iterator(); iter.hasNext(); ) {
					stmt.setObject(i++, binding.get(object, iter.next()));
				}
			}
		};
		
		try {
			_connectionPool.executePreparedStatement(sql, stmtCallback, null);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim Anlegen eines Datensatzes für '" + binding.getEntityName() + "'", e);
		}
	}
	
	@Override
	public <T> void update(final TypeBinding<T> binding, final int id, final T object) throws IOException {
		String sql = DBStatements.update(binding.getEntityName(), getAttributes(binding));
		
		PreparedStatementCallback stmtCallback = new PreparedStatementCallback() {
			@Override
			public void callback(PreparedStatement stmt) throws SQLException {
				int i=1;
				for(Iterator<SimpleAttributeBinding<?>> iter = binding.getAttributes().iterator(); iter.hasNext(); ) {
					stmt.setObject(i++, binding.get(object, iter.next()));
				}
				stmt.setInt(i, id);
			}
		};
		
		try {
			_connectionPool.executePreparedStatement(sql, stmtCallback, null);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim Aktualisieren eines Datensatzes für '" + binding.getEntityName() + "'", e);
		}
	}
	
	@Override
	public <T> void delete(final TypeBinding<T> binding, final int id) throws IOException {
		String sql = DBStatements.delete(binding.getEntityName());
		
		PreparedStatementCallback stmtCallback = new PreparedStatementCallback() {
			@Override
			public void callback(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, id);
			}
		};
		
		try {
			_connectionPool.executePreparedStatement(sql, stmtCallback, null);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim Aktualisieren eines Datensatzes für '" + binding.getEntityName() + "'", e);
		}
	}
}
