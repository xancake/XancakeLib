package de.xancake.util;

import de.xancake.util.checks.DoubleChecks;
import de.xancake.util.checks.IntegerChecks;

public final class Numbers {
	private Numbers() {}
	
	public static IntegerChecks check(int number) {
		return IntegerChecks.check(number);
	}
	
	public static IntegerChecks check(int number, String name) {
		return IntegerChecks.check(number, name);
	}
	
	public static DoubleChecks check(double number) {
		return DoubleChecks.check(number);
	}
	
	public static DoubleChecks check(double number, String name) {
		return DoubleChecks.check(number, name);
	}
}
