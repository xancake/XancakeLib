package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.From_I;
import de.xancake.io.db.sql.query.Select_I;

class Select extends SqlCommand implements Select_I {
	private String[] myParameters;
	
	protected Select() {
		this(SqlConstants_I.SELECT_ALL);
	}
	
	protected Select(String... parameters) {
		super(SqlConstants_I.SELECT, null);
		myParameters = parameters;
	}
	
	@Override
	public From_I from(String table) {
		return new From(this, table);
	}
	
	@Override
	public From_I from(String table, String alias) {
		return new From(this, table, alias);
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(mySQL);
		sb.append(" ");
		for(int i=0; i<myParameters.length; i++) {
			sb.append(myParameters[i]);
			if(i<myParameters.length-1) {
				sb.append(", ");
			}
		}
		return sb;
	}
}
