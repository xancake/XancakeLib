package de.xancake.io.persistence.broker.db.statement;

public class DBStatements {
	private DBStatements() {}
	
	public static String select(String table) {
		return new DBSelectStatement(table).get();
	}
	
	public static String acquireId(String table) {
		return new DBAqcuireIDStatement(table).get();
	}
	
	public static String insert(String table, String... fields) {
		return new DBInsertStatement(table, fields).get();
	}
	
	public static String update(String table, String... fields) {
		return new DBUpdateStatement(table, fields).get();
	}
	
	public static String delete(String table) {
		return new DBDeleteStatement(table).get();
	}
	
	public static String create(String table, String... attributeClauses) {
		return new DBCreateTableStatement(table, attributeClauses).get();
	}
	
	public static String drop(String table) {
		return new DBDropTableStatement(table).get();
	}
	
	/**
	 * Konkateniert alle Strings aus dem Array mit Kommas, sodass am Ende ein String dabei herauskommt,
	 * wobei jeder Eintrag am Ende mit einem Komma von einem anderen getrennt ist.
	 * 
	 * <p>Beispiel: <code>concatenateWithComma("Hallo", "Herr", "Sonne")</code> erzeugt <code>"Hallo, Herr, Sonne"</code>. 
	 * @param fields Die Elemente die konkateniert werden sollen
	 * @return Der konkatenierte String
	 */
	static String concatenateWithComma(String... fields) {
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<fields.length; i++) {
			builder.append(fields[i]);
			if(i != fields.length-1) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}
}
