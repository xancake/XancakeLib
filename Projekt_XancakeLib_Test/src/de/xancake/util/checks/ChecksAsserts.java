package de.xancake.util.checks;

import static org.junit.Assert.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

public final class ChecksAsserts {
	private ChecksAsserts() {}
	
	public static <T, C extends Checks<T, C>> T assertOk(C check) {
		assertTrue(check.toString(), check.isOk());
		return check.get();
	}
	
	public static <T, C extends Checks<T, C>> void assertNotOk(C check, String expectedMessage) {
		assertNotOk(check, CoreMatchers.is(expectedMessage));
	}
	
	public static <T, C extends Checks<T, C>> void assertNotOk(C check, Matcher<String> expectedMessage) {
		assertFalse(check.toString(), check.isOk());
		
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, check::get);
		MatcherAssert.assertThat(e.getMessage(), expectedMessage);
	}
}
