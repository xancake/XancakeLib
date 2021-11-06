package de.xancake.util.checks;

public final class DoubleChecks extends ObjectChecks<Double, DoubleChecks> {
	public static DoubleChecks check(Double number) {
		return check(number, "value");
	}
	
	public static DoubleChecks check(Double number, String name) {
		return new DoubleChecks(number, name);
	}
	
	private DoubleChecks(Double number, String name) {
		super(number, name);
	}
	
	public DoubleChecks equal(String message, double other) {
		return check(message, v -> v == other);
	}
	
	public DoubleChecks notEqual(String message, double other) {
		return check(message, v -> v != other);
	}
	
	public DoubleChecks less(String message, double other) {
		return check(message, v -> v < other);
	}
	
	public DoubleChecks lessOrEqual(String message, double other) {
		return check(message, v -> v <= other);
	}
	
	public DoubleChecks greater(String message, double other) {
		return check(message, v -> v > other);
	}
	
	public DoubleChecks greaterOrEqual(String message, double other) {
		return check(message, v -> v >= other);
	}
	
	public DoubleChecks between(String message, double a, double b) { // Exclusive
		return greater(message, a).less(message, b);
	}
	
	public DoubleChecks positive(String message) {
		return greaterOrEqual(message, 0);
	}
	
	public DoubleChecks negative(String message) {
		return less(message, 0);
	}
	
	public DoubleChecks equal(double other) {
		return equal("equal " + other, other);
	}
	
	public DoubleChecks notEqual(double other) {
		return notEqual("not equal " + other, other);
	}
	
	public DoubleChecks less(double other) {
		return less("less than " + other, other);
	}
	
	public DoubleChecks lessOrEqual(double other) {
		return lessOrEqual("less than or equal to " + other, other);
	}
	
	public DoubleChecks greater(double other) {
		return greater("greater than " + other, other);
	}
	
	public DoubleChecks greaterOrEqual(double other) {
		return greaterOrEqual("greater than or equal to " + other, other);
	}
	
	public DoubleChecks between(double a, double b) { // Exclusive
		return between("between " + a + " and " + b, a, b);
	}
	
	public DoubleChecks positive() {
		return positive("positive");
	}
	
	public DoubleChecks negative() {
		return negative("negative");
	}
}
