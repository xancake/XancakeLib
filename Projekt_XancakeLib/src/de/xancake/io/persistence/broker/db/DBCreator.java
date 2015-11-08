package de.xancake.io.persistence.broker.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Objects;
import de.xancake.io.db.sql.ConnectionPool;
import de.xancake.io.db.sql.config.DBConfiguration_I;
import de.xancake.io.persistence.bind.SimpleAttributeBinding;
import de.xancake.io.persistence.bind.TypeBinding;
import de.xancake.io.persistence.broker.PersistenceCreator;
import de.xancake.io.persistence.broker.db.statement.DBStatements;

public class DBCreator implements PersistenceCreator {
	private ConnectionPool _connectionPool;
	
	public DBCreator(DBConfiguration_I configuration) {
		this(new ConnectionPool(configuration));
	}
	
	public DBCreator(DBConfiguration_I configuration, int maxConnections) {
		this(new ConnectionPool(configuration, maxConnections));
	}
	
	public DBCreator(ConnectionPool connectionPool) {
		_connectionPool = Objects.requireNonNull(connectionPool);
	}
	
	@Override
	public void createPersistable(TypeBinding<?> binding) throws IOException {
		String sql = DBStatements.create(binding.getEntityName(), getCreateAttributesClause(binding));
		
		try {
			_connectionPool.executeStatement(sql, null);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim Anlegen der Tabelle '" + binding.getEntityName() + "'", e);
		}
	}
	
	private String getCreateAttributesClause(TypeBinding<?> binding) {
		StringBuilder attributes = new StringBuilder();
		for(Iterator<SimpleAttributeBinding<?>> iter = binding.getAttributes().iterator(); iter.hasNext(); ) {
			SimpleAttributeBinding<?> attribute = iter.next();
			appendAttribute(attributes, attribute);
			if(iter.hasNext()) {
				attributes.append(", ");
			}
		}
		return attributes.toString();
	}
	
	private StringBuilder appendAttribute(StringBuilder builder, SimpleAttributeBinding<?> attribute) {
		builder.append(attribute.getName());
		builder.append(" ");
		builder.append(DBPersistenceUtils.javaToSQLDatatype(attribute.getJavaType()));
		builder.append(" ");
		builder.append(attribute.isOptional() ? "NULL" : "NOT NULL");
		return builder;
	}
	
	@Override
	public void dropPersistable(TypeBinding<?> binding) throws IOException {
		String sql = DBStatements.drop(binding.getEntityName());
		
		try {
			_connectionPool.executeStatement(sql, null);
		} catch(SQLException | InterruptedException e) {
			throw new IOException("Fehler beim Löschen der Tabelle '" + binding.getEntityName() + "'", e);
		}
	}
}
