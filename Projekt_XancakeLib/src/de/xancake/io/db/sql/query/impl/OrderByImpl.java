package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.GroupBy;
import de.xancake.io.db.sql.query.OrderBy;
import de.xancake.io.db.sql.query.intern.SqlCommand;

class OrderByImpl extends StateableImpl implements OrderBy {
	private String _field;
	private Order _order;
	
	protected OrderByImpl(SqlCommand predecessor, String field) {
		this(predecessor, field, null);
	}
	
	protected OrderByImpl(SqlCommand predecessor, String field, Order order) {
		super(SqlConstants_I.ORDER_BY, predecessor);
		_field = field;
		_order = order;
	}
	
	@Override
	public GroupBy groupBy(String... criteria) {
		return new GroupByImpl(this, criteria);
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(_sql);
		sb.append(" ");
		sb.append(_field);
		if(_order != null) {
			sb.append(" ");
			sb.append(_order.getTypeString());
		}
		return sb;
	}
}
