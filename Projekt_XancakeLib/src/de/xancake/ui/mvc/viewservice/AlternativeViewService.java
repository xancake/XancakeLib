package de.xancake.ui.mvc.viewservice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import de.xancake.ui.mvc.Controller_A;
import de.xancake.ui.mvc.ViewListener_I;
import de.xancake.ui.mvc.View_I;

public class AlternativeViewService {
	@SuppressWarnings("rawtypes")
	private Map<Controller_A, View_I> myViewMapping;
	
	@SuppressWarnings("rawtypes")
	public AlternativeViewService() {
		myViewMapping = new HashMap<Controller_A, View_I>();
	}
	
	public <M, L extends ViewListener_I, V extends View_I<M, L>> void registerView(Controller_A<M, L, V, ?> controller, V view) {
		Objects.requireNonNull(controller, "Der Controller darf nicht 'null' sein!");
		myViewMapping.put(controller, view);
	}
	
	public boolean isControllerRegistered(Controller_A<?, ?, ?, ?> controller) {
		return myViewMapping.containsKey(controller);
	}
	
	@SuppressWarnings("unchecked")
	public <M, L extends ViewListener_I, V extends View_I<M, L>> V getView(Controller_A<M, L, V, ?> controller) {
		return (V)myViewMapping.get(controller);
	}
	
	public void reset() {
		myViewMapping.clear();
	}
}
