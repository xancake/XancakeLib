package de.xancake.pattern.listener;

import java.util.ArrayList;
import java.util.Collection;

public abstract class EventExecutor_A<L extends Listener_I> {
	protected Collection<L> myListeners;
	
	public EventExecutor_A() {
		this(new ArrayList<L>());
	}
	
	protected EventExecutor_A(Collection<L> listenerList) {
		myListeners = listenerList;
	}
	
	public void addListener(L l) {
		myListeners.add(l);
	}
	
	public void removeListener(L l) {
		myListeners.remove(l);
	}
	
	public Collection<L> getListeners() {
		return myListeners;
	}
	
	protected void fireEvent(Event_A<? super L> event) {
		for(L l : myListeners) {
			event.fireEvent(l);
		}
	}
}
