package de.xancake.pattern.command.event;

import de.xancake.pattern.command.Command;

public class UndoEvent extends AbstractCommandHistoryEvent {
	public UndoEvent(Command command) {
		super(command);
	}
}
