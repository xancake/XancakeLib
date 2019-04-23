package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.GroupBy;
import de.xancake.io.db.sql.query.Join;
import de.xancake.io.db.sql.query.JoinOn;
import de.xancake.io.db.sql.query.OrderBy;
import de.xancake.io.db.sql.query.OrderBy.Order;
import de.xancake.io.db.sql.query.Where;
import de.xancake.io.db.sql.query.intern.SqlCommand;

class JoinOnImpl extends StateableImpl implements JoinOn {
	private String _left;
	private String _right;
	
	protected JoinOnImpl(SqlCommand predecessor, String leftSide, String rightSide) {
		super(SqlConstants_I.JOIN_ON, predecessor);
		_left = leftSide;
		_right = rightSide;
	}
	
	@Override
	public Join join(JoinType type, String table) {
		return new JoinImpl(this, type, table);
	}
	
	@Override
	public Join join(JoinType type, String table, String alias) {
		return new JoinImpl(this, type, table, alias);
	}
	
	@Override
	public Where where(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public OrderBy orderBy(String field) {
		return new OrderByImpl(this, field);
	}
	
	@Override
	public OrderBy orderBy(String field, Order order) {
		return new OrderByImpl(this, field, order);
	}
	
	@Override
	public GroupBy groupBy(String... criteria) {
		return new GroupByImpl(this, criteria);
	}
	
	@Override
	StringBuilder buildOwnSql(StringBuilder sb) {
		sb.append(_sql);
		sb.append(" ");
		sb.append(_left);
		sb.append("=");
		sb.append(_right);
		return sb;
	}
}
