package de.xancake.pattern.command;

public interface UndoableCommand extends Command {
	void undo();
}
