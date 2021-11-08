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
	
	private String label = null;
	
	private boolean brackets = true;
	private String bracketOpen = DEFAULT_BRACKET_OPEN;
	private String bracketClose = DEFAULT_BRACKET_CLOSE;
	
	private String beforeEach = null;
	private String afterEach = null;
	
	private String separator = DEFAULT_SEPARATOR;
	
	private boolean breakLines;
	private final String linebreakSymbol = System.lineSeparator();
	
	private boolean indent;
	private String indentationSymbol = DEFAULT_INDENTATION;
	
	@SuppressWarnings("unchecked")
	private Function<T, String> _formatter = (Function<T, String>)DEFAULT_ELEMENT_FORMATTER;
	
	public static <T> CollectionFormatter<T> format(Collection<T> c) {
		return new CollectionFormatter<>(c);
	}
	
	private CollectionFormatter(Collection<T> c) {
		_c = Objects.requireNonNull(c);
	}
	
	public CollectionFormatter<T> noLabel() {
		label = null;
		return this;
	}
	
	public CollectionFormatter<T> label(String text) {
		label = Objects.requireNonNull(text);
		return this;
	}
	
	public CollectionFormatter<T> noBrackets() {
		brackets = false;
		return this;
	}
	
	public CollectionFormatter<T> brackets() {
		brackets = true;
		return this;
	}
	
	public CollectionFormatter<T> brackets(String open, String close) {
		bracketOpen = Objects.requireNonNull(open);
		bracketClose = Objects.requireNonNull(close);
		return brackets();
	}
	
	public CollectionFormatter<T> noBeforeEach() {
		return beforeEach(null);
	}
	
	public CollectionFormatter<T> beforeEach(String symbol) {
		beforeEach = symbol;
		return this;
	}
	
	public CollectionFormatter<T> noAfterEach() {
		return afterEach(null);
	}
	
	public CollectionFormatter<T> afterEach(String symbol) {
		afterEach = symbol;
		return this;
	}
	
	public CollectionFormatter<T> noSeparator() {
		separator = null;
		return this;
	}
	
	public CollectionFormatter<T> separator(String symbol) {
		separator = Objects.requireNonNull(symbol);
		return this;
	}
	
	public CollectionFormatter<T> noLinebreak() {
		breakLines = false;
		return this;
	}
	
	public CollectionFormatter<T> linebreak() {
		breakLines = true;
		return this;
	}
	
	public CollectionFormatter<T> noIndent() {
		indent = false;
		return this;
	}
	
	public CollectionFormatter<T> indent() {
		indent = true;
		return this;
	}
	
	public CollectionFormatter<T> indent(String symbol) {
		indentationSymbol = Objects.requireNonNull(symbol);
		return indent();
	}
	
	public CollectionFormatter<T> map(Function<T, String> formatter) {
		_formatter = Objects.requireNonNull(formatter);
		return this;
	}
	
	@Override
	public String toString() {
		String beforeElement = (breakLines ? linebreakSymbol + (indent ? indentationSymbol : "") : "")
			+ (beforeEach == null ? "" : beforeEach);
		String afterElement = (afterEach == null ? "" : afterEach);
		String delimiter = afterElement + (separator == null ? "" : separator) + beforeElement;
		String prefix = (label == null ? "" : label + " ") + (brackets ? bracketOpen : "") + beforeElement;
		String suffix = afterElement + (breakLines ? linebreakSymbol : "") + (brackets ? bracketClose : "");
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
