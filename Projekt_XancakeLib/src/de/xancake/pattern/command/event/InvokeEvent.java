package de.xancake.pattern.command.event;

import de.xancake.pattern.command.Command;

public class InvokeEvent extends AbstractCommandHistoryEvent {
	public InvokeEvent(Command command) {
		super(command);
	}
}
