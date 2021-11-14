package de.xancake.util;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionFormatter<T> {
	public static final String DEFAULT_BRACKET_OPEN = "[";
	public static final String DEFAULT_BRACKET_CLOSE = "]";
	public static final String DEFAULT_INDENTATION = "\t";
	public static final String DEFAULT_SEPARATOR = ", ";
	public static final Function<?, String> DEFAULT_ELEMENT_FORMATTER = Objects::toString;
	
	private final Collection<T> _c;
	
	private String _label = "";
	
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
	
	@SuppressWarnings("unchecked")
	private Function<T, String> _formatter = (Function<T, String>)DEFAULT_ELEMENT_FORMATTER;
	
	public static <T> CollectionFormatter<T> format(Collection<T> c) {
		return new CollectionFormatter<>(c);
	}
	
	private CollectionFormatter(Collection<T> c) {
		_c = Objects.requireNonNull(c);
	}
	
	public CollectionFormatter<T> noLabel() {
		return label("");
	}
	
	public CollectionFormatter<T> label(String text) {
		_label = Objects.requireNonNull(text);
		return this;
	}
	
	public CollectionFormatter<T> noBrackets() {
		_brackets = false;
		return this;
	}
	
	public CollectionFormatter<T> brackets() {
		_brackets = true;
		return this;
	}
	
	public CollectionFormatter<T> brackets(String open, String close) {
		_bracketOpen = Objects.requireNonNull(open);
		_bracketClose = Objects.requireNonNull(close);
		return brackets();
	}
	
	public CollectionFormatter<T> noBeforeEach() {
		return beforeEach("");
	}
	
	public CollectionFormatter<T> beforeEach(String symbol) {
		_beforeEach = Objects.requireNonNull(symbol);
		return this;
	}
	
	public CollectionFormatter<T> noAfterEach() {
		return afterEach("");
	}
	
	public CollectionFormatter<T> afterEach(String symbol) {
		_afterEach = Objects.requireNonNull(symbol);
		return this;
	}
	
	public CollectionFormatter<T> noSeparator() {
		return separator("");
	}
	
	public CollectionFormatter<T> separator(String symbol) {
		_separator = Objects.requireNonNull(symbol);
		return this;
	}
	
	public CollectionFormatter<T> noLinebreak() {
		_breakLines = false;
		return this;
	}
	
	public CollectionFormatter<T> linebreak() {
		_breakLines = true;
		return this;
	}
	
	public CollectionFormatter<T> noIndent() {
		_indent = false;
		return this;
	}
	
	public CollectionFormatter<T> indent() {
		_indent = true;
		return this;
	}
	
	public CollectionFormatter<T> indent(String symbol) {
		_indentationSymbol = Objects.requireNonNull(symbol);
		return indent();
	}
	
	public CollectionFormatter<T> map(Function<T, String> formatter) {
		_formatter = Objects.requireNonNull(formatter);
		return this;
	}
	
	@Override
	public String toString() {
		String beforeElement = (_breakLines ? _linebreakSymbol + (_indent ? _indentationSymbol : "") : "") + _beforeEach;
		String afterElement = _afterEach;
		String delimiter = afterElement + _separator + beforeElement;
		String prefix = _label + (_brackets ? _bracketOpen : "") + beforeElement;
		String suffix = afterElement + (_breakLines ? _linebreakSymbol : "") + (_brackets ? _bracketClose : "");
		return _c.stream().map(_formatter).collect(Collectors.joining(delimiter, prefix, suffix));
	}
	
	public void print() {
		print(System.out);
	}
	
	public void print(OutputStream s) {
		new PrintStream(s).print(toString());
	}
	
	public void println() {
		println(System.out);
	}
	
	public void println(OutputStream s) {
		new PrintStream(s).println(toString());
	}
	
	public static void main(String... args) {
		Collection<String> c = Arrays.asList("Lars", "Laura", "Sven", "Martina");
		System.out.println(c);
		CollectionFormatter.format(c).println();
		CollectionFormatter
			.format(c)
			.label("Personen")
			.brackets("{", "}")
			.linebreak()
			.indent()
			.noSeparator()
			.beforeEach("<")
			.afterEach(">")
			.map(s -> s + " (" + s.length() + ")")
			.println();
	}
}
