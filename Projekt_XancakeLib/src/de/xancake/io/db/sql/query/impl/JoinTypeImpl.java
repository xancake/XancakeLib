package de.xancake.io.db.sql.query.impl;

public enum JoinTypeImpl {
	NONE(SqlConstants_I.JOIN_TYPE_NONE),
	NATURAL(SqlConstants_I.JOIN_TYPE_NATURAL),
	CROSS(SqlConstants_I.JOIN_TYPE_CROSS),
	LEFT(SqlConstants_I.JOIN_TYPE_LEFT),
	RIGHT(SqlConstants_I.JOIN_TYPE_RIGHT),
	INNER(SqlConstants_I.JOIN_TYPE_INNER);
	
	private String myTypeString;
	
	private JoinTypeImpl(String typeString) {
		myTypeString = typeString;
	}
	
	public String getTypeString() {
		return myTypeString;
	}
}
