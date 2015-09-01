package de.xancake.io.file;

import java.io.File;

public class FileUtils {
	private static final String DOT = ".";
	
	public static String getExtension(String path) {
		return getExtension(new File(path));
	}
	
	public static String getExtension(File file) {
		if(file==null) return null;
		return getExtension0(file.getName());
	}
	
	private static String getExtension0(String name) {
		String extension = null;
		if(name!=null) {
			int i = name.lastIndexOf(DOT);
			if(i>0 && i<name.length()) {
				extension = name.substring(i+1).toLowerCase();
			} else if(i==-1) {
				extension = name;
			}
		}
		return extension;
	}
}
