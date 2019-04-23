package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.GroupBy;
import de.xancake.io.db.sql.query.Join;
import de.xancake.io.db.sql.query.JoinOn;
import de.xancake.io.db.sql.query.OrderBy;
import de.xancake.io.db.sql.query.OrderBy.Order;
import de.xancake.io.db.sql.query.Where;
import de.xancake.io.db.sql.query.intern.SqlCommand;

class JoinImpl extends StateableImpl implements Join {
	private JoinType _type;
	private String _table;
	private String _tableAlias;
	
	
	protected JoinImpl(SqlCommand predecessor, JoinType type, String table) {
		this(predecessor, type, table, null);
	}
	
	protected JoinImpl(SqlCommand predecessor, JoinType type, String table, String alias) {
		super(SqlConstants_I.JOIN, predecessor);
		_type = type;
		_table = table;
		_tableAlias = alias;
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
	public JoinOn on(String leftKey, String rightKey) {
		return new JoinOnImpl(this, leftKey, rightKey);
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
		sb.append(_type.getTypeString());
		sb.append(" ");
		sb.append(_sql);
		sb.append(" ");
		sb.append(_table);
		if(_tableAlias != null) {
			sb.append(" ");
			sb.append(SqlConstants_I.FROM_AS);
			sb.append(" ");
			sb.append(_tableAlias);
		}
		return sb;
	}
}
