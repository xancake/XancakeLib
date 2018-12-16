package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.InsertInto;
import de.xancake.io.db.sql.query.Values;

public class InsertIntoImpl extends SqlCommandImpl implements InsertInto {
	private String _table;
	private String[] _fieldNames;
	
	protected InsertIntoImpl(String table) {
		this(table, new String[] {});
	}
	
	protected InsertIntoImpl(String table, String... fieldNames) {
		super(SqlConstants_I.INSERT_INTO, null);
		_table = table;
		_fieldNames = fieldNames;
	}
	
	@Override
	public Values values(Object... attributeValues) {
		return new ValuesImpl(this, attributeValues);
	}
	
	@Override
	public Values valuesWildcard() {
		if(_fieldNames == null) {
			throw new SqlSyntaxException(ExceptionMessages_I.EXC_INSERT_VALUES_WILDCARD_NO_ATTRIBUTES);
		}
		return new ValuesImpl(this, _fieldNames.length);
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(_sql);
		sb.append(" ");
		sb.append(_table);
		if(_fieldNames != null && _fieldNames.length > 0) {
			sb.append(" (");
			for(int i=0; i<_fieldNames.length; i++) {
				sb.append(_fieldNames[i]);
				if(i<_fieldNames.length-1) {
					sb.append(", ");
				}
			}
			sb.append(")");
		}
		return sb;
	}
}
