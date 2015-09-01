package de.xancake.pattern.command.event;

public class ClearHistoryEvent extends CommandHistoryEvent_A {
	public ClearHistoryEvent() {
		super(null);
	}
	
	@Override
	public void fireEvent(CommandHistoryListener_I l) {
		l.onClearHistory(this);
	}
}
