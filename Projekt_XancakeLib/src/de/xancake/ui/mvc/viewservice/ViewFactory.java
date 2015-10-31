package de.xancake.ui.mvc.viewservice;

public class ViewFactory {
	private ViewService myViewService;
	
	private ViewFactory() {
		setViewService(new DefaultViewService());
	}
	
	public void setViewService(ViewService viewService) {
		myViewService = viewService;
	}
	
	public ViewService getViewService() {
		return myViewService;
	}
	
	public static ViewFactory getInstance() {
		return Holder.INSTANCE;
	}
	
	private static class Holder {
		private static final ViewFactory INSTANCE = new ViewFactory();
	}
}
