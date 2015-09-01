package de.xancake.io.file;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FileUtilsTest {
	@Test
	public void testGetExtension_Filename() {
		assertGetExtension("jpg", "Forest.jpg");
	}
	
	@Test
	public void testGetExtension_Pathname() {
		assertGetExtension("jpg", "C:/Users/currentuser/test.jpg");
	}
	
	@Test
	public void testGetExtension_DottedFilename() {
		assertGetExtension("jpg", "Flower v1.2.jpg");
	}
	
	@Test
	public void testGetExtension_DottedPathname() {
		assertGetExtension("jpg", "C:/Users/current.user/Flower v1.2.jpg");
	}
	
	@Test
	public void testGetExtension_CapitalExtension() {
		assertGetExtension("jpg", "FLOWER.JPG");
	}
	
	@Test
	public void testGetExtension_NoExtension() {
		assertGetExtension("fileWithoutAnExtension", "C:/Users/currentuser/fileWithoutAnExtension");
	}
	
	@Test
	public void testGetExtension_DottedPathnameNoExtension() {
		assertGetExtension("fileWithoutAnExtension", "C:/Users/current.user/fileWithoutAnExtension");
	}
	
	@Test
	public void testGetExtension_FileEndsWithDot() {
		assertGetExtension("", "C:/Users/currentuser/fileStoppingWithA.");
	}
	
	@Test
	public void testGetExtension_FileStartsWithDot() {
		assertGetExtension("properties", "C:/Users/currentuser/.properties");
	}
	
	private void assertGetExtension(String expected, String dir) {
		assertEquals(expected, FileUtils.getExtension(dir));
	}
}
