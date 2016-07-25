package de.xancake.pattern.listener;

@FunctionalInterface
public interface Event<L> {
	
	void fireEvent(L l);
}
