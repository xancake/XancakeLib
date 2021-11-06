package de.xancake.pattern.command.event;

import de.xancake.pattern.command.Command;
import de.xancake.pattern.listener.EventDispatcher;

public class CommandHistoryEventExecutor extends EventDispatcher<CommandHistoryListener> {
	public void fireInvokeEvent(Command command) {
		fireEvent(new InvokeEvent(command), CommandHistoryListener::onInvoke);
	}
	
	public void fireUndoEvent(Command command) {
		fireEvent(new UndoEvent(command), CommandHistoryListener::onUndo);
	}
	
	public void fireRedoEvent(Command command) {
		fireEvent(new RedoEvent(command), CommandHistoryListener::onRedo);
	}
	
	public void fireClearHistoryEvent() {
		fireEvent(new ClearHistoryEvent(), CommandHistoryListener::onClearHistory);
	}
}
