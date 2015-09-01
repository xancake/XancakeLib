package de.xancake.ui.mvc;

public abstract class View_A<M extends Model_I, L extends ViewListener_I> implements View_I<M, L> {
	protected L myListener;
	
	@Override
	public L getViewListener() {
		return myListener;
	}
	
	@Override
	public void setViewListener(L l) {
		myListener = l;
	}
}
