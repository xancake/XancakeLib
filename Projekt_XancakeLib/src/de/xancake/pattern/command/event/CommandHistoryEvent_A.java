package de.xancake.pattern.command.event;

import de.xancake.pattern.command.Command_I;
import de.xancake.pattern.listener.Event_A;

public abstract class CommandHistoryEvent_A extends Event_A<CommandHistoryListener_I> {
	private Command_I myCommand;
	
	public CommandHistoryEvent_A(Command_I command) {
		myCommand = command;
	}
	
	public Command_I getCommand() {
		return myCommand;
	}
}
