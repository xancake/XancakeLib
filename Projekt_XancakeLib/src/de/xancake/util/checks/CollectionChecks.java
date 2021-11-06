package de.xancake.util.checks;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionChecks<E, T extends Collection<E>, C extends ObjectChecks<T, C>> extends ObjectChecks<T, C> {
	
	private static <T> String format(Collection<T> collection, Function<Collection<T>, String> formatter) {
		return formatter.apply(collection);
	}
	
	public static <E, C extends CollectionChecks<E, Collection<E>, C>> CollectionChecks<E, Collection<E>, C> check(
		Collection<E> collection
	)
	{
		return check(collection, "collection (" + format(collection, CollectionFormatters.formatPlain()) + ")");
	}
	
	public static <E, C extends CollectionChecks<E, Collection<E>, C>> CollectionChecks<E, Collection<E>, C> check(
		Collection<E> collection,
		String name
	)
	{
		return new CollectionChecks<>(collection, name);
	}
	
	protected CollectionChecks(T o, String name) {
		super(o, name);
	}
	
	public C empty() {
		return check("empty", Collection::isEmpty);
	}
	
	public C notEmpty() {
		return check("not empty", c -> !c.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	public C size(Function<IntegerChecks, IntegerChecks> sizeCheck) {
		return check(IntegerChecks.check(getIntern().size(), "size"), sizeCheck);
	}
	
	@SuppressWarnings("unchecked")
	public C containsAll(E... elements) {
		return containsAll(Arrays.asList(elements));
	}
	
	public C containsAll(Collection<E> elements) {
		Collection<E> missing = new LinkedList<>(elements);
		missing.removeAll(getIntern());
		return check("containing " + elements + " but was missing " + missing, c -> missing.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	public C containsNotAll(E... elements) { // TODO: Enthält nicht alle der übergebenen Elemente gleichzeitig
		return containsAll(Arrays.asList(elements));
	}
	
	public C containsNotAll(Collection<E> elements) {
		return check("", c -> !c.containsAll(elements)); // TODO: Text
	}
	
	@SuppressWarnings("unchecked")
	public C containsAny(E... elements) {
		return containsAny(Arrays.asList(elements));
	}
	
	public C containsAny(Collection<E> elements) {
		Collection<E> contained = new LinkedList<>(elements);
		contained.retainAll(getIntern());
		return check("containing one of " + elements + " but didn't", c -> !contained.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	public C containsNone(E... elements) {
		return containsAny(Arrays.asList(elements));
	}
	
	public C containsNone(Collection<E> elements) {
		Collection<E> contained = new LinkedList<>(elements);
		contained.retainAll(getIntern());
		return check("containing none of " + elements + " but contained " + contained, c -> contained.isEmpty());
	}
	
	public static final class CollectionFormatters {
		private CollectionFormatters() {}
		
		public static <T> Function<Collection<T>, String> formatPlain() {
			return Collection::toString;
		}
		
		public static <T> Function<Collection<T>, String> formatLineByLine() {
			return formatLineByLine(Object::toString);
		}
		
		public static <T> Function<Collection<T>, String> formatLineByLine(
			Function<? super T, String> elementFormatter
		)
		{
			return c -> c.stream().map(elementFormatter).collect(Collectors.joining("[\t\n", "\t\n", "\n]"));
		}
	}
}
