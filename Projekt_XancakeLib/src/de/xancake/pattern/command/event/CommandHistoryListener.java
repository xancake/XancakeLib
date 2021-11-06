package de.xancake.pattern.command.event;

public interface CommandHistoryListener {
	void onInvoke(InvokeEvent event);
	void onUndo(UndoEvent event);
	void onRedo(RedoEvent event);
	void onClearHistory(ClearHistoryEvent event);
}
