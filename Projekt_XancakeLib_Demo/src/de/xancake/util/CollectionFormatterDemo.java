package de.xancake.util;

import java.util.Arrays;
import java.util.Collection;

public class CollectionFormatterDemo {
	public static void main(String... args) {
		Collection<String> c = Arrays.asList("Lars", "Laura", "Sven", "Martina");
		System.out.println(c);
		System.out.println(new CollectionFormatter().format(c));
		System.out
			.println(
				new CollectionFormatter() //
					.brackets("{", "}") //
					.linebreak() //
					.indent() //
					.noSeparator() //
					.beforeEach("<") //
					.afterEach(">") //
					.format(c, "Personen", s -> s + " (" + s.length() + ")")
			);
	}
}
