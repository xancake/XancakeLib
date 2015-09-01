package de.xancake.ui.mvc;

public abstract class Controller_A<M extends Model_I, L extends ViewListener_I, V extends View_I<M, L>> implements Controller_I<M, L, V> {
	protected M myModel;
	protected V myView;
	
	@Override
	public M getModel() {
		return myModel;
	}
	
	@Override
	public void setModel(M model) {
		myModel = model;
		fillViewWithModel(myView, myModel);
	}
	
	@Override
	public V getView() {
		return myView;
	}
	
	@Override
	public void setView(V view) {
		unregisterOnView(myView);
		myView = view;
		registerOnView(myView);
		fillViewWithModel(myView, myModel);
	}
	
	private void unregisterOnView(V view) {
		if(view != null) {
			view.setViewListener(null);
		}
	}
	
	private void registerOnView(V view) {
		if(view != null) {
			view.setViewListener(getViewListener());
		}
	}
	
	private void fillViewWithModel(V view, M model) {
		if(view != null && model != null) {
			view.fillViewWithModel(model);
		}
	}
}
