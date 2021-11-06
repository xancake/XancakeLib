package de.xancake.util.checks;

import java.util.function.Function;
import java.util.regex.Pattern;

public final class StringChecks extends ObjectChecks<String, StringChecks> {
	public static StringChecks check(String s) {
		return new StringChecks(s, "string (" + s + ")");
	}
	
	public static StringChecks check(String s, String name) {
		return new StringChecks(s, name);
	}
	
	private StringChecks(String s, String name) {
		super(s, name);
	}
	
	public StringChecks isEmpty(String message) {
		return check(message, String::isEmpty);
	}
	
	public StringChecks isNotEmpty(String message) {
		return checkNot(message, String::isEmpty);
	}
	
	public StringChecks isBlank(String message) {
		return check(message, String::isBlank);
	}
	
	public StringChecks isNotBlank(String message) {
		return checkNot(message, String::isBlank);
	}
	
	@SuppressWarnings("unchecked")
	public StringChecks length(Function<IntegerChecks, IntegerChecks> lengthCheck) {
		return check(IntegerChecks.check(getIntern().length(), "length"), lengthCheck);
	}
	
	public StringChecks startsWith(String message, String prefix) {
		return check(message, s -> s.startsWith(prefix));
	}
	
	public StringChecks endsWith(String message, String suffix) {
		return check(message, s -> s.endsWith(suffix));
	}
	
	public StringChecks contains(String message, String sequence) {
		return check(message, s -> s.contains(sequence));
	}
	
	public StringChecks matches(String message, String regex) {
		return matches(message, Pattern.compile(regex));
	}
	
	public StringChecks matches(String message, Pattern pattern) {
		return check(message, s -> pattern.matcher(s).matches());
	}
	
	public StringChecks isEmpty() {
		return isEmpty("empty");
	}
	
	public StringChecks isNotEmpty() {
		return isNotEmpty("not empty");
	}
	
	public StringChecks isBlank() {
		return isBlank("blank");
	}
	
	public StringChecks isNotBlank() {
		return isNotBlank("not blank");
	}
	
	public StringChecks startsWith(String prefix) {
		return startsWith("starting with '" + prefix + "'", prefix);
	}
	
	public StringChecks endsWith(String suffix) {
		return endsWith("ending with '" + suffix + "'", suffix);
	}
	
	public StringChecks contains(String sequence) {
		return contains("containing '" + sequence + "'", sequence);
	}
	
	public StringChecks matches(String regex) {
		return matches("matching '" + regex + "'", regex);
	}
	
	public StringChecks matches(Pattern pattern) {
		return matches("matching '" + pattern + "'", pattern);
	}
}
