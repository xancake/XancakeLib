package de.xancake.io.db.sql.query.impl;

import de.xancake.io.db.sql.query.GroupBy;
import de.xancake.io.db.sql.query.OrderBy;
import de.xancake.io.db.sql.query.OrderBy.Order;
import de.xancake.io.db.sql.query.Where;
import de.xancake.io.db.sql.query.WhereAnd;
import de.xancake.io.db.sql.query.WhereOr;
import de.xancake.io.db.sql.query.intern.SqlCommand;

class WhereImpl extends StateableImpl implements Where {
	
	
	protected WhereImpl(SqlCommand predecessor) {
		super(SqlConstants_I.WHERE, predecessor);
	}
	
	@Override
	public WhereAnd and(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public WhereOr or(String condition) {
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
		return null;
	}
}
