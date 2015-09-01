package de.xancake.persistence.db.sql;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import de.xancake.io.db.sql.ConnectionPool;
import de.xancake.io.db.sql.ConnectionPool.PreparedStatementCallback;
import de.xancake.io.db.sql.ConnectionPool.ResultSetCallback;
import de.xancake.persistence.Loader_I;
import de.xancake.persistence.bind.AttributeBinding;
import de.xancake.persistence.bind.TypeBinding_I;

public class DBLoader<T> implements Loader_I<T> {
	private static final String SQL_SELECT = "SELECT * FROM {0} WHERE {1}=?";
	
	private ConnectionPool myConnectionPool;
	private TypeBinding_I<T> myBinding;
	
	public DBLoader(ConnectionPool connectionPool, TypeBinding_I<T> binding) {
		myConnectionPool = Objects.requireNonNull(connectionPool, "Der ConnectionPool darf nicht null sein!");
		myBinding = Objects.requireNonNull(binding, "Das Binding darf nicht null sein!");
	}
	
	@Override
	public T load(final int id) throws IOException {
		String sql = SQL_SELECT
				.replaceFirst("{0}", myBinding.getEntityName())
				.replaceFirst("{1}", myBinding.getIDAttribute().getName());
		
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
					T object = myBinding.create();
					for(AttributeBinding attribute : myBinding.getAttributes()) {
						myBinding.set(object, attribute, rs.getObject(attribute.getName()));
					}
					return object;
				}
				return null;
			}
		};
		
		try {
			return myConnectionPool.executePreparedStatement(sql, stmtCallback, rsCallback);
		} catch(InterruptedException e) {
			throw new IOException("Fehler beim Laden eines Datensatzes aus '" + myBinding.getEntityName() + "'", e);
		}
	}
}
