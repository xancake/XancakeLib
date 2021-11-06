package de.xancake.util.checks;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class Checks<T, C extends Checks<T, C>> {
	private final T _o;
	private String _name;
	private List<String> _messages;
	
	protected Checks(T o, String name) {
		_o = o;
		_name = name;
		_messages = new LinkedList<>();
	}
	
	@SuppressWarnings("unchecked")
	public final C check(String message, Predicate<T> predicate) {
		if(!predicate.test(_o)) {
			String prefix = _messages.isEmpty() ? " must be " : " and ";
			_messages.add(prefix + message);
		}
		return (C)this;
	}
	
	public final C checkNot(String message, Predicate<T> predicate) {
		return check(message, predicate.negate());
	}
	
	@SuppressWarnings("unchecked")
	public final <X, SUB extends Checks<X, SUB>> C check(SUB subcheck, Function<SUB, SUB>... checks) {
		for(Function<SUB, SUB> check : checks) {
			subcheck = check.apply(subcheck);
		}
		if(!subcheck.isOk()) {
			_messages.add(" " + subcheck.toString());
		}
		return (C)this;
	}
	
	public final boolean isOk() {
		return _messages.isEmpty();
	}
	
	public final T get() {
		return get(this::toString);
	}
	
	public final T get(Supplier<String> message) {
		if(!isOk()) {
			throw new IllegalArgumentException(message.get());
		}
		return _o;
	}
	
	protected final T getIntern() {
		return _o;
	}
	
	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(_name);
		for(int i = 0; i < _messages.size(); i++) {
			sb.append(_messages.get(i));
		}
		return sb.toString();
	}
}
