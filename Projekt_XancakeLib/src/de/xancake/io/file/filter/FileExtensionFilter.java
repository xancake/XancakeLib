package de.xancake.io.file.filter;

import java.io.File;
import java.io.FileFilter;
import de.xancake.io.file.FileUtils;

/**
 * This class represents a file filter that accepts files based on their file extension.
 * 
 * @author Lars 'Xancake' Nielsen
 */
public class FileExtensionFilter implements FileFilter {
	private static final String DOT = ".";
	
	private String myExtension;
	
	/**
	 * The invocation of this constructor with any string starting without a dot will
	 * result in the dot being added. For example {@code new FileExtensionFilter("jpg")}
	 * results in the same filter as {@code new FileExtensionFilter(".jpg")}.
	 * @param extension The extension to check for
	 * @throws IllegalArgumentException if the extension is {@code null}
	 */
	public FileExtensionFilter(String extension) {
		if(extension == null) {
			throw new IllegalArgumentException("The extension may not be 'null'");
		}
		myExtension = extension.startsWith(DOT) ? extension : DOT + extension;
	}
	
	@Override
	public boolean accept(File pathname) {
		return FileUtils.getExtension(pathname).equals(myExtension);
	}
}
