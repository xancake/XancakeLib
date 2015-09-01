package de.xancake.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import de.xancake.io.file.filter.FileExtensionFilter;

public class PluginLoader {
	@SuppressWarnings("unchecked")
  public static <M extends PluginManager_I> List<Plugin_I<M>> loadPlugins(M pluginManager, File pluginDir) throws IOException {
		File[] pluginJars = pluginDir.listFiles(new FileExtensionFilter("jar"));
		ClassLoader classLoader = new URLClassLoader(fileArrayToURLArray(pluginJars));
		List<Class<Plugin_I<M>>> pluginClasses = extractClassesFromJARs(pluginJars, classLoader, (Class<M>)pluginManager.getClass());
		List<Plugin_I<M>> plugins = createPluggableObjects(pluginClasses);
		registerPluginManager(plugins, pluginManager);
		return plugins;
	}
	
	private static URL[] fileArrayToURLArray(File[] files) throws MalformedURLException {
		URL[] urls = new URL[files.length];
		for (int i = 0; i < files.length; i++) {
			urls[i] = files[i].toURI().toURL();
		}
		return urls;
	}
	
	private static <M extends PluginManager_I> List<Class<Plugin_I<M>>> extractClassesFromJARs(File[] jars, ClassLoader classLoader, Class<M> managerClass) throws IOException {
		List<Class<Plugin_I<M>>> classes = new ArrayList<Class<Plugin_I<M>>>();
		for (File jar : jars) {
			classes.addAll(extractClassesFromJAR(jar, classLoader, managerClass));
		}
		return classes;
	}
	
	@SuppressWarnings("unchecked")
	private static <M extends PluginManager_I> List<Class<Plugin_I<M>>> extractClassesFromJAR(File jar, ClassLoader classLoader, Class<M> managerClass) throws IOException {
		List<Class<Plugin_I<M>>> classes = new ArrayList<Class<Plugin_I<M>>>();
		JarInputStream jaris = new JarInputStream(new FileInputStream(jar));
		JarEntry entry = null;
		while((entry = jaris.getNextJarEntry()) != null) {
			if(entry.getName().toLowerCase().endsWith(".class")) {
				try {
					Class<?> cls = classLoader.loadClass(entry.getName().substring(0, entry.getName().length() - 6).replace('/', '.'));
					if(isPluginClass(cls)) {
						classes.add((Class<Plugin_I<M>>)cls);
					}
				} catch(ClassNotFoundException e) {
					System.err.println("Can't load Class " + entry.getName());
					e.printStackTrace();
				}
			}
		}
		jaris.close();
		return classes;
	}
	
	private static boolean isPluginClass(Class<?> cls) {
		for(Class<?> iface : cls.getInterfaces()) {
			if(iface.equals(Plugin_I.class)) {
				return true;
			}
		}
		return false;
	}
	
	private static <M extends PluginManager_I> List<Plugin_I<M>> createPluggableObjects(List<Class<Plugin_I<M>>> plugables) {
		List<Plugin_I<M>> plugins = new ArrayList<Plugin_I<M>>(plugables.size());
		for (Class<Plugin_I<M>> pluginClass : plugables) {
			try {
				plugins.add(pluginClass.newInstance());
			} catch (InstantiationException e) {
				System.err.println("Can't instantiate plugin: " + pluginClass.getName());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccess for plugin: " + pluginClass.getName());
				e.printStackTrace();
			}
		}
		return plugins;
	}
	
	private static <M extends PluginManager_I> void registerPluginManager(List<Plugin_I<M>> plugins, M pluginManager) {
		for(Plugin_I<M> plugin : plugins) {
			plugin.setPluginManager(pluginManager);
		}
	}
}
