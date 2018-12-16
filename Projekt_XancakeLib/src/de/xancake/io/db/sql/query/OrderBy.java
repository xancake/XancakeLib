package de.xancake.io.db.sql.query;

import de.xancake.io.db.sql.query.intern.Groupable;
import de.xancake.io.db.sql.query.intern.SqlCommand;
import de.xancake.io.db.sql.query.intern.Stateable;

public interface OrderBy extends SqlCommand, Stateable, Groupable {
	
	public static enum Order {
		ASCENDING("ASC"),
		DESCENDING("DESC");
		
		private String myTypeString;
		
		private Order(String typeString) {
			myTypeString = typeString;
		}
		
		public String getTypeString() {
			return myTypeString;
		}
	}
}
