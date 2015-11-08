package de.xancake.io.persistence.broker.db;

import java.math.BigDecimal;
import de.xancake.io.persistence.bind.SimpleAttributeBinding;
import de.xancake.io.persistence.bind.TypeBinding;

public final class DBPersistenceUtils {
	private DBPersistenceUtils() {}
	
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
	
	public static String[] getAttributes(TypeBinding<?> binding) {
		String[] attributes = new String[binding.getAttributes().size()];
		int i = 0;
		for(SimpleAttributeBinding<?> attribute : binding.getAttributes()) {
			attributes[i++] = attribute.getName();
		}
		return attributes;
	}
}
