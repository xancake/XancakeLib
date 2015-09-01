package de.xancake.plugin;

public abstract class Plugin_A<M extends PluginManager_I> implements Plugin_I<M> {
	protected M myPluginManager;
	
	@Override
	public M getPluginManager() {
		return myPluginManager;
	}
	
	@Override
	public void setPluginManager(M pluginManager) {
		myPluginManager = pluginManager;
	}
}
