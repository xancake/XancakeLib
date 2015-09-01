package de.xancake.io.db.sql.query.impl;

interface SqlConstants_I {
	String SELECT   = "SELECT";
	String FROM     = "FROM";
	String FROM_AS  = "AS";
	String JOIN     = "JOIN";
	String JOIN_ON  = "ON";
	String WHERE    = "WHERE";
	String ORDER_BY = "ORDER BY";
	String GROUP_BY = "GROUP BY";
	
	String INSERT_INTO   = "INSERT INTO";
	String INSERT_VALUES = "VALUES";
	
	String JOIN_TYPE_NONE    = "";
	String JOIN_TYPE_NATURAL = "NATURAL";
	String JOIN_TYPE_CROSS   = "CROSS";
	String JOIN_TYPE_LEFT    = "LEFT";
	String JOIN_TYPE_RIGHT   = "RIGHT";
	String JOIN_TYPE_INNER   = "INNER";
	
	String SELECT_ALL = "*";
	
	String WILDCARD = "?";
}
