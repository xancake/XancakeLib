package de.xancake.io.db.sql.query.impl;

public enum OrderType {
	ASCENDING("ASC"),
	DESCENDING("DESC");
	
	private String myTypeString;
	
	private OrderType(String typeString) {
		myTypeString = typeString;
	}
	
	public String getTypeString() {
		return myTypeString;
	}
}
