package de.xancake.util.checks;

public final class IntegerChecks extends ObjectChecks<Integer, IntegerChecks> {
	public static IntegerChecks check(Integer number) {
		return check(number, "value (" + number + ")");
	}
	
	public static IntegerChecks check(Integer number, String name) {
		return new IntegerChecks(number, name);
	}
	
	private IntegerChecks(Integer number, String name) {
		super(number, name);
	}
	
	public IntegerChecks equal(String message, int other) {
		return check(message, v -> v == other);
	}
	
	public IntegerChecks notEqual(String message, int other) {
		return check(message, v -> v != other);
	}
	
	public IntegerChecks less(String message, int other) {
		return check(message, v -> v < other);
	}
	
	public IntegerChecks lessOrEqual(String message, int other) {
		return check(message, v -> v <= other);
	}
	
	public IntegerChecks greater(String message, int other) {
		return check(message, v -> v > other);
	}
	
	public IntegerChecks greaterOrEqual(String message, int other) {
		return check(message, v -> v >= other);
	}
	
	public IntegerChecks between(String message, int a, int b) { // Exclusive
		return greater(message, a).less(message, b);
	}
	
	public IntegerChecks positive(String message) {
		return greaterOrEqual(message, 0);
	}
	
	public IntegerChecks negative(String message) {
		return less(message, 0);
	}
	
	public IntegerChecks equal(int other) {
		return equal("equal to " + other, other);
	}
	
	public IntegerChecks notEqual(int other) {
		return notEqual("not equal to " + other, other);
	}
	
	public IntegerChecks less(int other) {
		return less("less than " + other, other);
	}
	
	public IntegerChecks lessOrEqual(int other) {
		return lessOrEqual("less than or equal to " + other, other);
	}
	
	public IntegerChecks greater(int other) {
		return greater("greater than " + other, other);
	}
	
	public IntegerChecks greaterOrEqual(int other) {
		return greaterOrEqual("greater than or equal to " + other, other);
	}
	
	public IntegerChecks between(int a, int b) { // Exclusive
		return between("between " + a + " and " + b, a, b);
	}
	
	public IntegerChecks positive() {
		return positive("positive");
	}
	
	public IntegerChecks negative() {
		return negative("negative");
	}
}
