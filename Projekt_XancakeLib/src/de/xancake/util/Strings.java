package de.xancake.util;

import de.xancake.util.checks.StringChecks;

public final class Strings {
	private Strings() {}
	
	public static StringChecks check(String s) {
		return StringChecks.check(s);
	}
	
	public static StringChecks check(String s, String name) {
		return StringChecks.check(s, name);
	}
}
