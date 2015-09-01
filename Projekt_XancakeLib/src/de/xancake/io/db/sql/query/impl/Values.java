package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.Values_I;
import de.xancake.io.db.sql.query.intern.SqlCommand_I;

public class Values extends Stateable implements Values_I {
	private Object[] myAttributeValues;  // TODO: Aufbereitung der Attributwerte
	
	protected Values(SqlCommand_I predecessor, int wildcardCount) {
		super(SqlConstants_I.INSERT_VALUES, predecessor);
		myAttributeValues = new Object[wildcardCount];
		for(int i=0; i<wildcardCount; i++) {
			myAttributeValues[i] = SqlConstants_I.WILDCARD;
		}
	}
	
	protected Values(SqlCommand_I predecessor, Object... attributeValues) {
		super(SqlConstants_I.INSERT_VALUES, predecessor);
		myAttributeValues = attributeValues;
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(mySQL);
		sb.append(" (");
		for(int i=0; i<myAttributeValues.length; i++) {
			sb.append(myAttributeValues[i]);
			if(i<myAttributeValues.length-1) {
				sb.append(", ");
			}
		}
		sb.append(")");
		return sb;
	}
}
