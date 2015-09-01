package de.xancake.plugin;

public interface Plugin_I<M extends PluginManager_I> {
	M getPluginManager();
	void setPluginManager(M pluginManager);
	void start();
}
