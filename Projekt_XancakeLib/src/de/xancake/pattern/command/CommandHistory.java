package de.xancake.pattern.command;

import java.util.ArrayList;
import java.util.List;

import de.xancake.pattern.command.event.CommandHistoryEventExecutor;
import de.xancake.pattern.command.event.CommandHistoryListener;

public class CommandHistory {
	private CommandHistoryEventExecutor _eventDispatcher;
	private List<UndoableCommand> _commands;
	private int _currentCommandIndex;
	
	public CommandHistory() {
		_eventDispatcher = new CommandHistoryEventExecutor();
		_commands = new ArrayList<UndoableCommand>();
		_currentCommandIndex = -1;
	}
	
	public void addListener(CommandHistoryListener l) {
		_eventDispatcher.addListener(l);
	}
	
	public void removeListener(CommandHistoryListener l) {
		_eventDispatcher.addListener(l);
	}
	
	public boolean canUndo() {
		return !_commands.isEmpty() && _currentCommandIndex >= 0;
	}
	
	public boolean canRedo() {
		return !_commands.isEmpty() && _currentCommandIndex < _commands.size()-1;
	}
	
	public void undo() {
		if(canUndo()) {
			UndoableCommand command = _commands.get(_currentCommandIndex--); 
			command.undo();
			_eventDispatcher.fireUndoEvent(command);
		}
	}
	
	public void redo() {
		if(canRedo()) {
			Command command = _commands.get(++_currentCommandIndex);
			command.execute();
			_eventDispatcher.fireRedoEvent(command);
		}
	}
	
	public void invoke(Command command) {
		if(command instanceof UndoableCommand) {
			if(canRedo()) {
				// Wenn wir redo'n können, sind wir nicht am Ende der Command-Liste.
				// Folglich werden die folgenden Commands (ursprüngliche Redos) überschrieben.
				// Damit das nicht zu Problemen führt werden die Redos von vornherein gelöscht.
				clearRedos();
			}
			_commands.add((UndoableCommand)command);
			_currentCommandIndex++;
		} else {
			clearHistory();
		}
		command.execute();
		_eventDispatcher.fireInvokeEvent(command);
	}
	
	public boolean isEmpty() {
		return _commands.isEmpty();
	}
	
	public void clearHistory() {
		_currentCommandIndex = -1;
		_commands.clear();
		_eventDispatcher.fireClearHistoryEvent();
	}
	
	private void clearRedos() {
		for(int i=_commands.size()-1; i>_currentCommandIndex; i--) {
			_commands.remove(i);
		}
	}
}
