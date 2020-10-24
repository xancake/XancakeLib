package de.xancake.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import de.xancake.io.file.filter.FileExtensionFilter;

class PluginLoader {
	public static List<Plugin> loadPlugins(PluginManager pluginManager, File pluginDir) throws IOException {
		File[] pluginJars = pluginDir.listFiles(new FileExtensionFilter("jar"));
		ClassLoader classLoader = new URLClassLoader(fileArrayToURLArray(pluginJars));
		List<Class<Plugin>> pluginClasses = extractClassesFromJARs(pluginJars, classLoader, pluginManager.getClass());
		List<Plugin> plugins = createPluggableObjects(pluginClasses);
		registerPluginManager(plugins, pluginManager);
		return plugins;
	}
	
	private static URL[] fileArrayToURLArray(File... files) throws MalformedURLException {
		URL[] urls = new URL[files.length];
		for (int i = 0; i < files.length; i++) {
			urls[i] = files[i].toURI().toURL();
		}
		return urls;
	}
	
	private static List<Class<Plugin>> extractClassesFromJARs(
		File[] jars,
		ClassLoader classLoader,
		Class<? extends PluginManager> managerClass
	) throws IOException {
		List<Class<Plugin>> classes = new ArrayList<>();
		for (File jar : jars) {
			classes.addAll(extractClassesFromJAR(jar, classLoader, managerClass));
		}
		return classes;
	}
	
	@SuppressWarnings("unchecked")
	private static List<Class<Plugin>> extractClassesFromJAR(
		File jar,
		ClassLoader classLoader,
		Class<? extends PluginManager> managerClass
	) throws IOException {
		List<Class<Plugin>> classes = new ArrayList<>();
		JarInputStream jaris = new JarInputStream(new FileInputStream(jar));
		JarEntry entry = null;
		while ((entry = jaris.getNextJarEntry()) != null) {
			if (entry.getName().toLowerCase().endsWith(".class")) {
				try {
					Class<?> cls = classLoader.loadClass(
						entry.getName().substring(0, entry.getName().length() - 6).replace('/', '.')
					);
					if (cls.isAssignableFrom(Plugin.class)) {
						classes.add((Class<Plugin>)cls);
					}
				} catch (ClassNotFoundException e) {
					System.err.println("Can't load Class " + entry.getName());
					e.printStackTrace();
				}
			}
		}
		jaris.close();
		return classes;
	}
	
	private static List<Plugin> createPluggableObjects(List<Class<Plugin>> plugables) {
		List<Plugin> plugins = new ArrayList<>(plugables.size());
		for (Class<Plugin> pluginClass : plugables) {
			try {
				plugins.add(pluginClass.getConstructor().newInstance());
			} catch (InstantiationException e) {
				System.err.println("Can't instantiate plugin: " + pluginClass.getName());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccess for plugin: " + pluginClass.getName());
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.err.println("IllegalArgument for plugin: " + pluginClass.getName());
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException for plugin: " + pluginClass.getName());
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				System.err.println("NoSuchMethodException for plugin: " + pluginClass.getName());
				e.printStackTrace();
			} catch (SecurityException e) {
				System.err.println("SecurityException for plugin: " + pluginClass.getName());
				e.printStackTrace();
			}
		}
		return plugins;
	}
	
	private static void registerPluginManager(List<Plugin> plugins, PluginManager pluginManager) {
		for (Plugin plugin : plugins) {
			plugin.setPluginManager(pluginManager);
		}
	}
}
