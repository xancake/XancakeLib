package de.xancake.persistence.db.sql;

import java.math.BigDecimal;
import de.xancake.persistence.bind.TypeBinding_I;

public final class DBPersistenceUtils {
	private DBPersistenceUtils() {}
	
	public static String getPrimaryKeySequenceName(TypeBinding_I<?> binding) {
		return binding.getEntityName() + "_" + binding.getIDAttribute().getName();
	}
	
	public static String javaToSQLDatatype(Class<?> javatype) {
		if(Integer.class.equals(javatype)) {
			return "INTEGER";
		} else if(String.class.equals(javatype)) {
			return "VARCHAR(100)";
		} else if(Double.class.equals(javatype) || BigDecimal.class.equals(javatype)) {
			return "NUMBER(8,2)";
		} else if(Number.class.isAssignableFrom(javatype)) {
			return "NUMBER(10,4)";
		}
		return null;
	}
}