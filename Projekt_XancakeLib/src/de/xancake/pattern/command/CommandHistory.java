package de.xancake.pattern.command;

import java.util.ArrayList;
import java.util.List;

import de.xancake.pattern.command.event.CommandHistoryEventExecutor;
import de.xancake.pattern.command.event.CommandHistoryListener_I;

public class CommandHistory {
	private CommandHistoryEventExecutor myEventExecutor;
	private List<UndoableCommand_I> myCommands;
	private int myCurrentCommandIndex;
	
	public CommandHistory() {
		myEventExecutor = new CommandHistoryEventExecutor();
		myCommands = new ArrayList<UndoableCommand_I>();
		myCurrentCommandIndex = -1;
	}
	
	public void addListener(CommandHistoryListener_I l) {
		myEventExecutor.addListener(l);
	}
	
	public void removeListener(CommandHistoryListener_I l) {
		myEventExecutor.addListener(l);
	}
	
	public boolean canUndo() {
		return !myCommands.isEmpty() && myCurrentCommandIndex >= 0;
	}
	
	public boolean canRedo() {
		return !myCommands.isEmpty() && myCurrentCommandIndex < myCommands.size()-1;
	}
	
	public void undo() {
		if(canUndo()) {
			UndoableCommand_I command = myCommands.get(myCurrentCommandIndex--); 
			command.undo();
			myEventExecutor.fireUndoEvent(command);
		}
	}
	
	public void redo() {
		if(canRedo()) {
			Command_I command = myCommands.get(++myCurrentCommandIndex);
			command.execute();
			myEventExecutor.fireRedoEvent(command);
		}
	}
	
	public void invoke(Command_I command) {
		if(command instanceof UndoableCommand_I) {
			if(canRedo()) {
				// Wenn wir redo'n können, sind wir nicht am Ende der Command-Liste.
				// Folglich werden die folgenden Commands (ursprüngliche Redos) überschrieben.
				// Damit das nicht zu Problemen führt werden die Redos von vornherein gelöscht.
				clearRedos();
			}
			myCommands.add((UndoableCommand_I)command);
			myCurrentCommandIndex++;
		} else {
			clearHistory();
		}
		command.execute();
		myEventExecutor.fireInvokeEvent(command);
	}
	
	public boolean isEmpty() {
		return myCommands.isEmpty();
	}
	
	public void clearHistory() {
		myCurrentCommandIndex = -1;
		myCommands.clear();
		myEventExecutor.fireClearHistoryEvent();
	}
	
	private void clearRedos() {
		for(int i=myCommands.size()-1; i>myCurrentCommandIndex; i--) {
			myCommands.remove(i);
		}
	}
}
