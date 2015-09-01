package de.xancake.pattern.command.event;

import de.xancake.pattern.command.Command_I;

public class InvokeEvent extends CommandHistoryEvent_A {
	public InvokeEvent(Command_I command) {
		super(command);
	}
	
	@Override
	public void fireEvent(CommandHistoryListener_I l) {
		l.onInvoke(this);
	}
}
