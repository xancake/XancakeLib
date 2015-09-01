package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.InsertInto_I;
import de.xancake.io.db.sql.query.Values_I;

public class InsertInto extends SqlCommand implements InsertInto_I {
	private String myTable;
	private String[] myAttributeNames;
	
	protected InsertInto(String table) {
		this(table, new String[] {});
	}
	
	protected InsertInto(String table, String... attributeNames) {
		super(SqlConstants_I.INSERT_INTO, null);
		myTable = table;
		myAttributeNames = attributeNames;
	}
	
	@Override
	public Values_I values(Object... attributeValues) {
		return new Values(this, attributeValues);
	}
	
	@Override
	public Values_I valuesWildcard() {
		if(myAttributeNames == null) {
			throw new SqlSyntaxException(ExceptionMessages_I.EXC_INSERT_VALUES_WILDCARD_NO_ATTRIBUTES);
		}
		return new Values(this, myAttributeNames.length);
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(mySQL);
		sb.append(" ");
		sb.append(myTable);
		if(myAttributeNames != null && myAttributeNames.length > 0) {
			sb.append(" (");
			for(int i=0; i<myAttributeNames.length; i++) {
				sb.append(myAttributeNames[i]);
				if(i<myAttributeNames.length-1) {
					sb.append(", ");
				}
			}
			sb.append(")");
		}
		return sb;
	}
}
