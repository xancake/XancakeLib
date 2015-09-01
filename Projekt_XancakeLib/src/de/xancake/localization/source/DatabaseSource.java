package de.xancake.localization.source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.xancake.io.db.util.DatabaseUtils;
import de.xancake.localization.LocalizationException;

public class DatabaseSource implements LocalizationSource_I {
	private static final String DB_LOCALIZATION_TABLE = "localization";
	private static final String DB_LOCALIZATION_KEY   = "key";
	private static final String DB_LOCALIZATION_VALUE = "value";
	private static final String SELECT_LOCALIZATION_VALUE = "SELECT {2} FROM {0} WHERE {1}=?";
	
	private Connection myConnection;
	private Map<String, String> myCache;
	
	private String myDBTableName;
	private String myDBValueName;
	private String myDBKeyName;
	
	public DatabaseSource(Connection connection) {
		this(connection, DB_LOCALIZATION_TABLE, DB_LOCALIZATION_KEY, DB_LOCALIZATION_VALUE);
	}
	
	public DatabaseSource(Connection connection, String dbTableName, String dbKeyName, String dbValueName) {
		myConnection = connection;
		myCache = new HashMap<String, String>();
		setDBLocalizationTableName(dbTableName);
		setDBLocalizationKeyName(dbKeyName);
		setDBLocalizationValueName(dbValueName);
		resetCache();
	}
	
	@Override
	public boolean containsKey(String key) {
		boolean contains = myCache.containsKey(key);
		if(!contains) {
			try {
				String value = queryValue(key);
				contains = value != null;
				if(contains) {
					myCache.put(key, value);
				}
			} catch(SQLException e) {
				throw new LocalizationException(key, e);
			}
		}
		return contains;
	}
	
	@Override
	public String getValue(String key) {
		String value = myCache.get(key);
		if(value == null) {
			try {
				value = queryValue(key);
				if(value == null) {
					throw new LocalizationException(key);
				}
				myCache.put(key, value);
			} catch(SQLException e) {
				throw new LocalizationException(key, e);
			}
		}
		return value;
	}
	
	private String queryValue(String key) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String select = MessageFormat.format(SELECT_LOCALIZATION_VALUE, myDBTableName, myDBKeyName, myDBValueName);
			stmt = myConnection.prepareStatement(select);
			stmt.setString(1, key);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString(myDBValueName);
			} else {
				return null;
			}
		} finally {
			try {
				DatabaseUtils.close(stmt, rs);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Collection<String> getKeys() {
		return myCache.keySet();
	}
	
	public void setDBLocalizationTableName(String dbTableName) {
		myDBTableName = dbTableName;
	}
	
	public void setDBLocalizationKeyName(String dbKeyName) {
		myDBKeyName = dbKeyName;
	}
	
	public void setDBLocalizationValueName(String dbValueName) {
		myDBValueName = dbValueName;
	}
	
	public void resetCache() {
		myCache.clear();
	}
}
