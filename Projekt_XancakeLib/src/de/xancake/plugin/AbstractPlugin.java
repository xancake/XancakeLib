package de.xancake.plugin;

public abstract class AbstractPlugin implements Plugin {
	protected PluginManager myPluginManager;
	
	@Override
	public PluginManager getPluginManager() {
		return myPluginManager;
	}
	
	@Override
	public void setPluginManager(PluginManager pluginManager) {
		myPluginManager = pluginManager;
	}
}
