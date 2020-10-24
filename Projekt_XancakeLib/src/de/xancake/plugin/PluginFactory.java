package de.xancake.plugin;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class PluginFactory {
	private PluginFactory() {}
	
	public List<Plugin> loadPlugins(PluginManager pluginManager, String location) throws IOException,
		URISyntaxException
	{
		return PluginLoader.loadPlugins(pluginManager, new File(ClassLoader.getSystemResource(location).toURI()));
	}
	
	public static PluginFactory getInstance() {
		return Holder.INSTANCE;
	}
	
	private static class Holder {
		private static final PluginFactory INSTANCE = new PluginFactory();
	}
}
