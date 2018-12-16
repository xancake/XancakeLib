package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.Values;
import de.xancake.io.db.sql.query.intern.SqlCommand;

public class ValuesImpl extends StateableImpl implements Values {
	private Object[] _fieldValues;  // TODO: Aufbereitung der Attributwerte
	
	protected ValuesImpl(SqlCommand predecessor, int wildcardCount) {
		super(SqlConstants_I.INSERT_VALUES, predecessor);
		_fieldValues = new Object[wildcardCount];
		for(int i=0; i<wildcardCount; i++) {
			_fieldValues[i] = SqlConstants_I.WILDCARD;
		}
	}
	
	protected ValuesImpl(SqlCommand predecessor, Object... attributeValues) {
		super(SqlConstants_I.INSERT_VALUES, predecessor);
		_fieldValues = attributeValues;
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(_sql);
		sb.append(" (");
		for(int i=0; i<_fieldValues.length; i++) {
			sb.append(_fieldValues[i]);
			if(i<_fieldValues.length-1) {
				sb.append(", ");
			}
		}
		sb.append(")");
		return sb;
	}
}
