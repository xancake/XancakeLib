package de.xancake.io.persistence.broker.db.statement;

public class AbstractDBStatement implements DBStatement {
	private String _statement;
	
	public AbstractDBStatement(String statement) {
		_statement = statement;
	}
	
	@Override
    public String get() {
	    return _statement;
    }
}
