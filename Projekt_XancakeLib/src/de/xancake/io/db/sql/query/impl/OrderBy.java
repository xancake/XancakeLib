package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.OrderBy_I;
import de.xancake.io.db.sql.query.intern.SqlCommand_I;

class OrderBy extends Stateable implements OrderBy_I {
	private String myOrderAttribute;
	private OrderType myOrderType;
	
	protected OrderBy(SqlCommand_I predecessor, String orderAttribute) {
		this(predecessor, orderAttribute, null);
	}
	
	protected OrderBy(SqlCommand_I predecessor, String orderAttribute, OrderType orderType) {
		super(SqlConstants_I.ORDER_BY, predecessor);
		myOrderAttribute = orderAttribute;
		myOrderType = orderType;
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(mySQL);
		sb.append(" ");
		sb.append(myOrderAttribute);
		if(myOrderType != null) {
			sb.append(" ");
			sb.append(myOrderType.getTypeString());
		}
		return sb;
	}
}
