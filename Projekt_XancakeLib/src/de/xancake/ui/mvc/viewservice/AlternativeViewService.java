package de.xancake.ui.mvc.viewservice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import de.xancake.ui.mvc.Controller;
import de.xancake.ui.mvc.ViewListener;
import de.xancake.ui.mvc.View;

public class AlternativeViewService {
	@SuppressWarnings("rawtypes")
	private Map<Controller, View> myViewMapping;
	
	@SuppressWarnings("rawtypes")
	public AlternativeViewService() {
		myViewMapping = new HashMap<Controller, View>();
	}
	
	public <M, L extends ViewListener, V extends View<M, L>> void registerView(Controller<M, L, V, ?> controller, V view) {
		Objects.requireNonNull(controller, "Der Controller darf nicht 'null' sein!");
		myViewMapping.put(controller, view);
	}
	
	public boolean isControllerRegistered(Controller<?, ?, ?, ?> controller) {
		return myViewMapping.containsKey(controller);
	}
	
	@SuppressWarnings("unchecked")
	public <M, L extends ViewListener, V extends View<M, L>> V getView(Controller<M, L, V, ?> controller) {
		return (V)myViewMapping.get(controller);
	}
	
	public void reset() {
		myViewMapping.clear();
	}
}
