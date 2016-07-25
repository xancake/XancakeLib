package de.xancake.pattern.command.event;

import de.xancake.pattern.command.Command_I;
import de.xancake.pattern.listener.EventDispatcher;

public class CommandHistoryEventExecutor extends EventDispatcher<CommandHistoryListener_I> {
	public void fireInvokeEvent(Command_I command) {
		fireEvent(new InvokeEvent(command));
	}
	
	public void fireUndoEvent(Command_I command) {
		fireEvent(new UndoEvent(command));
	}
	
	public void fireRedoEvent(Command_I command) {
		fireEvent(new RedoEvent(command));
	}
	
	public void fireClearHistoryEvent() {
		fireEvent(new ClearHistoryEvent());
	}
}
