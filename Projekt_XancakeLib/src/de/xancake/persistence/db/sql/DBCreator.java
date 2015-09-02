package de.xancake.persistence.db.sql;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import de.xancake.io.db.sql.ConnectionPool;
import de.xancake.persistence.Creator_I;
import de.xancake.persistence.bind.AttributeBinding;
import de.xancake.persistence.bind.TypeBinding_I;

public class DBCreator<T> implements Creator_I<T> {
	private static final String SQL_CREATE_TABLE    = "CREATE TABLE {0} ({1}, PRIMARY KEY ({2}))";
	private static final String SQL_DROP_TABLE      = "DROP TABLE {0}";
	private static final String SQL_CREATE_SEQUENCE = "CREATE SEQUENCE {0} START WITH 1000";
	private static final String SQL_DROP_SEQUENCE   = "DROP SEQUENCE {0}";
	
	private ConnectionPool myConnectionPool;
	private TypeBinding_I<T> myBinding;
	
	public DBCreator(ConnectionPool connectionPool, TypeBinding_I<T> binding) {
		myConnectionPool = Objects.requireNonNull(connectionPool, "Der ConnectionPool darf nicht null sein!");
		myBinding = Objects.requireNonNull(binding, "Das Binding darf nicht null sein!");
	}
	
	@Override
	public void createPersistable() throws IOException {
		String sql = SQL_CREATE_TABLE
				.replace("{0}", myBinding.getEntityName())
				.replace("{1}", getAttributes(myBinding))
				.replace("{2}", myBinding.getIDAttribute().getName());
		
		try {
			myConnectionPool.executeStatement(sql, null);
			createPrimaryKeySequence(myBinding);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim Anlegen der Tabelle '" + myBinding.getEntityName() + "'", e);
		}
	}
	
	private void createPrimaryKeySequence(TypeBinding_I<?> myBinding) throws IOException {
		String sequence = DBPersistenceUtils.getPrimaryKeySequenceName(myBinding);
		String sql = SQL_CREATE_SEQUENCE
				.replace("{0}", sequence);
		try {
			myConnectionPool.executeStatement(sql, null);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim Anlegen der Sequenz '" + sequence + "'", e);
		}
	}
	
	private String getAttributes(TypeBinding_I<?> myBinding) {
		StringBuilder attributes = new StringBuilder();
		appendAttribute(attributes, myBinding.getIDAttribute());
		for(AttributeBinding<?> attribute : myBinding.getAttributes()) {
			attributes.append(", ");
			appendAttribute(attributes, attribute);
		}
		return attributes.toString();
	}
	
	private StringBuilder appendAttribute(StringBuilder builder, AttributeBinding<?> attribute) {
		builder.append(attribute.getName());
		builder.append(" ");
		builder.append(DBPersistenceUtils.javaToSQLDatatype(attribute.getJavaType()));
		builder.append(" ");
		builder.append(attribute.isOptional() ? "NULL" : "NOT NULL");
		return builder;
	}
	
	@Override
	public void dropPersistable() throws IOException {
		String sql = SQL_DROP_TABLE
				.replace("{0}", myBinding.getEntityName());
		try {
			myConnectionPool.executeStatement(sql, null);
			dropPrimaryKeySequence(myBinding);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim L�schen der Tabelle '" + myBinding.getEntityName() + "'", e);
		}
	}
	
	private void dropPrimaryKeySequence(TypeBinding_I<?> myBinding) throws IOException {
		String sequence = DBPersistenceUtils.getPrimaryKeySequenceName(myBinding);
		String sql = SQL_DROP_SEQUENCE
				.replace("{0}", sequence);
		try {
			myConnectionPool.executeStatement(sql, null);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim L�schen der Sequenz '" + sequence + "'", e);
		}
	}
}
