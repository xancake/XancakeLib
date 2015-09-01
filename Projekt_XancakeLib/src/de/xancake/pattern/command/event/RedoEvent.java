package de.xancake.pattern.command.event;

import de.xancake.pattern.command.Command_I;

public class RedoEvent extends CommandHistoryEvent_A {
	public RedoEvent(Command_I command) {
		super(command);
	}
	
	@Override
	public void fireEvent(CommandHistoryListener_I l) {
		l.onRedo(this);
	}
}
