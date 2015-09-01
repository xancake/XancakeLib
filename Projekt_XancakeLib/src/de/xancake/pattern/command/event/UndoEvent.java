package de.xancake.pattern.command.event;

import de.xancake.pattern.command.Command_I;

public class UndoEvent extends CommandHistoryEvent_A {
	public UndoEvent(Command_I command) {
		super(command);
	}
	
	@Override
	public void fireEvent(CommandHistoryListener_I l) {
		l.onUndo(this);
	}
}
