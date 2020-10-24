package de.xancake.plugin;

public interface Plugin {
	PluginManager getPluginManager();
	
	void setPluginManager(PluginManager pluginManager);
	
	void start();
}
