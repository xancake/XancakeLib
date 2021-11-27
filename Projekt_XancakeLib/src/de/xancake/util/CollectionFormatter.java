package de.xancake.util;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionFormatter {
	public static final String DEFAULT_BRACKET_OPEN = "[";
	public static final String DEFAULT_BRACKET_CLOSE = "]";
	public static final String DEFAULT_INDENTATION = "\t";
	public static final String DEFAULT_SEPARATOR = ", ";
	
	public static CollectionFormatter createLineByLineFormatter() {
		return new CollectionFormatter().linebreak().indent();
	}
	
	private boolean _brackets = true;
	private String _bracketOpen = DEFAULT_BRACKET_OPEN;
	private String _bracketClose = DEFAULT_BRACKET_CLOSE;
	
	private String _beforeEach = "";
	private String _afterEach = "";
	
	private String _separator = DEFAULT_SEPARATOR;
	
	private boolean _breakLines;
	private final String _linebreakSymbol = System.lineSeparator();
	
	private boolean _indent;
	private String _indentationSymbol = DEFAULT_INDENTATION;
	
	public CollectionFormatter noBrackets() {
		_brackets = false;
		return this;
	}
	
	public CollectionFormatter brackets() {
		_brackets = true;
		return this;
	}
	
	public CollectionFormatter brackets(String open, String close) {
		_bracketOpen = Objects.requireNonNull(open);
		_bracketClose = Objects.requireNonNull(close);
		return brackets();
	}
	
	public CollectionFormatter noBeforeEach() {
		return beforeEach("");
	}
	
	public CollectionFormatter beforeEach(String symbol) {
		_beforeEach = Objects.requireNonNull(symbol);
		return this;
	}
	
	public CollectionFormatter noAfterEach() {
		return afterEach("");
	}
	
	public CollectionFormatter afterEach(String symbol) {
		_afterEach = Objects.requireNonNull(symbol);
		return this;
	}
	
	public CollectionFormatter noSeparator() {
		return separator("");
	}
	
	public CollectionFormatter separator(String symbol) {
		_separator = Objects.requireNonNull(symbol);
		return this;
	}
	
	public CollectionFormatter noLinebreak() {
		_breakLines = false;
		return this;
	}
	
	public CollectionFormatter linebreak() {
		_breakLines = true;
		return this;
	}
	
	public CollectionFormatter noIndent() {
		_indent = false;
		return this;
	}
	
	public CollectionFormatter indent() {
		_indent = true;
		return this;
	}
	
	public CollectionFormatter indent(String symbol) {
		_indentationSymbol = Objects.requireNonNull(symbol);
		return indent();
	}
	
	public <T> String format(Collection<T> c) {
		return format(c, "", Objects::toString);
	}
	
	public <T> String format(Collection<T> c, String label) {
		return format(c, label, Objects::toString);
	}
	
	public <T> String format(Collection<T> c, Function<? super T, String> formatter) {
		return format(c, "", formatter);
	}
	
	public <T> String format(Collection<T> c, String label, Function<? super T, String> formatter) {
		String beforeElement = (_breakLines ? _linebreakSymbol + (_indent ? _indentationSymbol : "") : "")
			+ _beforeEach;
		String afterElement = _afterEach;
		String delimiter = afterElement + _separator + beforeElement;
		String prefix = label + (_brackets ? _bracketOpen : "") + beforeElement;
		String suffix = afterElement + (_breakLines ? _linebreakSymbol : "") + (_brackets ? _bracketClose : "");
		return c.stream().map(formatter).collect(Collectors.joining(delimiter, prefix, suffix));
	}
}
