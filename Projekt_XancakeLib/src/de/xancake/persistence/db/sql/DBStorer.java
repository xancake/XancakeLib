package de.xancake.persistence.db.sql;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Objects;
import de.xancake.persistence.PersistenceBroker_I;
import de.xancake.persistence.Storer_I;
import de.xancake.persistence.bind.AttributeBinding;
import de.xancake.persistence.bind.TypeBinding_I;
import de.xancake.io.db.sql.ConnectionPool;
import de.xancake.io.db.sql.ConnectionPool.PreparedStatementCallback;
import de.xancake.io.db.sql.ConnectionPool.ResultSetCallback;

public class DBStorer<T> implements Storer_I<T> {
	private static final String SQL_NEW_ID = "SELECT {0}.nextval FROM DUAL";
	private static final String SQL_INSERT = "INSERT INTO {0} ({1}) VALUES ({2})";
	private static final String SQL_UPDATE = "UPDATE {0} SET {1} WHERE {3}=?";
	
	private ConnectionPool myConnectionPool;
	private TypeBinding_I<T> myBinding;
	
	public DBStorer(ConnectionPool connectionPool, TypeBinding_I<T> binding) {
		myConnectionPool = Objects.requireNonNull(connectionPool, "Der ConnectionPool darf nicht null sein!");
		myBinding = Objects.requireNonNull(binding, "Das Binding darf nicht null sein!");
	}
	
	@Override
	public void store(T object) throws IOException {
		if(myBinding.get(object, myBinding.getIDAttribute()) == PersistenceBroker_I.ID_UNPERSISTED) {
			myBinding.set(object, myBinding.getIDAttribute(), acquireID());
			insert(object);
		} else {
			update(object);
		}
	}
	
	private int acquireID() throws IOException {
		String sequence = DBPersistenceUtils.getPrimaryKeySequenceName(myBinding);
		String sql = SQL_NEW_ID
				.replaceFirst("{0}", sequence);
		
		ResultSetCallback<Integer> rsCallback = new ResultSetCallback<Integer>() {
			@Override
			public Integer callback(ResultSet rs) throws SQLException {
				rs.next();
				return rs.getInt(1);
			}
		};
		
		try {
			return myConnectionPool.executeStatement(sql, rsCallback);
		} catch(InterruptedException e) {
			throw new IOException("Fehler beim Ermitteln eines Identen aus der Sequenz '" + sequence + "'", e);
		}
	}
	
	private void insert(final T object) throws IOException {
		String sql = SQL_INSERT
				.replaceFirst("{0}", myBinding.getEntityName())
				.replaceFirst("{1}", getInsertAttributes())
				.replaceFirst("{2}", getInsertPlaceholders());
		
		PreparedStatementCallback stmtCallback = new PreparedStatementCallback() {
			@Override
			public void callback(PreparedStatement stmt) throws SQLException {
				int i=1;
				stmt.setInt(i++, myBinding.get(object, myBinding.getIDAttribute()));
				for(Iterator<AttributeBinding<?>> iter = myBinding.getAttributes().iterator(); iter.hasNext(); ) {
					stmt.setObject(i++, myBinding.get(object, iter.next()));
				}
			}
		};
		
		try {
			myConnectionPool.executePreparedStatement(sql, stmtCallback, null);
		} catch(InterruptedException e) {
			throw new IOException("Fehler beim Anlegen eines Datensatzes f�r '" + myBinding.getEntityName() + "'", e);
		}
	}
	
	private String getInsertAttributes() {
		StringBuilder attributes = new StringBuilder(myBinding.getIDAttribute().getName());
		for(AttributeBinding<?> attribute : myBinding.getAttributes()) {
			attributes.append(", ");
			attributes.append(attribute.getName());
		}
		return attributes.toString();
	}
	
	private String getInsertPlaceholders() {
		StringBuilder placeholders = new StringBuilder("?");
		for(Iterator<AttributeBinding<?>> iter = myBinding.getAttributes().iterator(); iter.hasNext(); ) {
			placeholders.append(", ?");
		}
		return placeholders.toString();
	}
	
	private void update(final T object) throws IOException {
		String sql = SQL_UPDATE
				.replaceFirst("{0}", myBinding.getEntityName())
				.replaceFirst("{1}", getUpdateSetClause())
				.replaceFirst("{2}", getUpdateWhereClause());
		
		PreparedStatementCallback stmtCallback = new PreparedStatementCallback() {
			@Override
			public void callback(PreparedStatement stmt) throws SQLException {
				int i=1;
				for(Iterator<AttributeBinding<?>> iter = myBinding.getAttributes().iterator(); iter.hasNext(); ) {
					stmt.setObject(i++, myBinding.get(object, iter.next()));
				}
				stmt.setInt(i, myBinding.get(object, myBinding.getIDAttribute()));
			}
		};
		
		try {
			myConnectionPool.executePreparedStatement(sql, stmtCallback, null);
		} catch(InterruptedException e) {
			throw new IOException("Fehler beim Aktualisieren eines Datensatzes f�r '" + myBinding.getEntityName() + "'", e);
		}
	}
	
	private String getUpdateSetClause() {
		StringBuilder setClause = new StringBuilder();
		for(Iterator<AttributeBinding<?>> iter = myBinding.getAttributes().iterator(); iter.hasNext(); ) {
			setClause.append(iter.next().getName());
			setClause.append("=?");
			if(iter.hasNext())
				setClause.append(", ");
		}
		return setClause.toString();
	}
	
	private String getUpdateWhereClause() {
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(myBinding.getIDAttribute().getName());
		whereClause.append("=?");
		return whereClause.toString();
	}
}
