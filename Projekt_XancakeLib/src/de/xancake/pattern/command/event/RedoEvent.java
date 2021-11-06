package de.xancake.pattern.command.event;

import de.xancake.pattern.command.Command;

public class RedoEvent extends AbstractCommandHistoryEvent {
	public RedoEvent(Command command) {
		super(command);
	}
}
