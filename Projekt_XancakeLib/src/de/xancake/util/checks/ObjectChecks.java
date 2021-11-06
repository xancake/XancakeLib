package de.xancake.util.checks;

public class ObjectChecks<T extends Object, C extends ObjectChecks<T, C>> extends Checks<T, C> {
	public static <C extends ObjectChecks<Object, C>> ObjectChecks<Object, C> check(Object o) {
		return check(o, "object");
	}
	
	public static <C extends ObjectChecks<Object, C>> ObjectChecks<Object, C> check(Object o, String name) {
		return new ObjectChecks<>(o, name);
	}
	
	protected ObjectChecks(T o, String name) {
		super(o, name);
	}
	
	public C isNull(String message) {
		return check(message, v -> v == null);
	}
	
	public C isNotNull(String message) {
		return checkNot(message, v -> v == null);
	}
	
	public C isSame(String message, Object o) {
		return check(message, v -> v == o);
	}
	
	public C isNotSame(String message, Object o) {
		return checkNot(message, v -> v == o);
	}
	
	public C isEqual(String message, Object o) {
		return check(message, o::equals);
	}
	
	public C isNotEqual(String message, Object o) {
		return checkNot(message, o::equals);
	}
}
