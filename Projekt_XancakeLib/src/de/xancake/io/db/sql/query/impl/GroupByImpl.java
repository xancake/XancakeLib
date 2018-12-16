package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.GroupBy;
import de.xancake.io.db.sql.query.intern.SqlCommand;

public class GroupByImpl extends StateableImpl implements GroupBy {
	private String[] _criteria;
	
	protected GroupByImpl(SqlCommand predecessor, String... criteria) {
		super(SqlConstants_I.GROUP_BY, predecessor);
		_criteria = criteria;
	}
	
	
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(_sql);
		sb.append(" ");
		for(int i=0; i<_criteria.length; i++) {
			sb.append(_criteria[i]);
			if(i != _criteria.length) {
				sb.append(", ");
			}
		}
		return sb;
	}
}
