package de.xancake.pattern.command.event;

import de.xancake.pattern.listener.Listener_I;

public interface CommandHistoryListener_I extends Listener_I {
	void onInvoke(InvokeEvent event);
	void onUndo(UndoEvent event);
	void onRedo(RedoEvent event);
	void onClearHistory(ClearHistoryEvent event);
}
