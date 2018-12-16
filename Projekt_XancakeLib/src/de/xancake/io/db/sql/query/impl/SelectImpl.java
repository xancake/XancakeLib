package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.From;
import de.xancake.io.db.sql.query.Select;

class SelectImpl extends SqlCommandImpl implements Select {
	private String[] myParameters;
	
	protected SelectImpl() {
		this(SqlConstants_I.SELECT_ALL);
	}
	
	protected SelectImpl(String... parameters) {
		super(SqlConstants_I.SELECT, null);
		myParameters = parameters;
	}
	
	@Override
	public From from(String table) {
		return new FromImpl(this, table);
	}
	
	@Override
	public From from(String table, String alias) {
		return new FromImpl(this, table, alias);
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(_sql);
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
