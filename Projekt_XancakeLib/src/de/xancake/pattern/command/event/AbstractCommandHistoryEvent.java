package de.xancake.pattern.command.event;

import de.xancake.pattern.command.Command;

public abstract class AbstractCommandHistoryEvent {
	private Command _command;
	
	public AbstractCommandHistoryEvent(Command command) {
		_command = command;
	}
	
	public Command getCommand() {
		return _command;
	}
}
