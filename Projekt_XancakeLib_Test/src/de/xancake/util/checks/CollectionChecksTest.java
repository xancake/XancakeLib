package de.xancake.util.checks;

import static de.xancake.util.checks.ChecksAsserts.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class CollectionChecksTest {
	private static final Collection<String> BASE = Arrays.asList("a", "b", "c");
	
	private static final Collection<String> EMPTY = Arrays.asList();
	private static final Collection<String> NONE = Arrays.asList("d", "e", "f");
	private static final Collection<String> ANY = Arrays.asList("b");
	private static final Collection<String> ALL = BASE;
	private static final Collection<String> NOT_ALL = Arrays.asList("a", "b", "c", "d");
	
	@Test
	public void testEmpty_Empty() {
		assertOk(CollectionChecks.check(EMPTY).empty());
	}
	
	@Test
	public void testEmpty_NotEmpty() {
		assertNotOk(CollectionChecks.check(BASE).empty(), "collection ([a, b, c]) must be empty");
	}
	
	@Test
	public void testNotEmpty_Empty() {
		assertNotOk(CollectionChecks.check(EMPTY).notEmpty(), "collection ([]) must be not empty");
	}
	
	@Test
	public void testNotEmpty_NotEmpty() {
		assertOk(CollectionChecks.check(BASE).notEmpty());
	}
	
	@Test
	public void testSize_Empty_Ok() {
		assertOk(CollectionChecks.check(EMPTY).size(s -> s.equal(0)));
	}
	
	@Test
	public void testSize_Empty_NotOk() {
		assertNotOk(CollectionChecks.check(EMPTY).size(s -> s.equal(1)), "collection ([]) size must be equal to 1");
	}
	
	@Test
	public void testSize_NotEmpty_Ok() {
		assertOk(CollectionChecks.check(BASE).size(s -> s.equal(3)));
	}
	
	@Test
	public void testSize_NotEmpty_NotOk() {
		assertNotOk(
			CollectionChecks.check(BASE).size(s -> s.equal(1)),
			"collection ([a, b, c]) size must be equal to 1"
		);
	}
	
	@Test
	public void testContainsAll_Empty() {
		assertOk(CollectionChecks.check(BASE).containsAll(EMPTY));
	}
	
	@Test
	public void testContainsAll_None() {
		assertNotOk(
			CollectionChecks.check(BASE).containsAll(NONE),
			"collection ([a, b, c]) must be containing [d, e, f] but was missing [d, e, f]"
		);
	}
	
	@Test
	public void testContainsAll_Any() {
		assertOk(CollectionChecks.check(BASE).containsAll(ANY));
	}
	
	@Test
	public void testContainsAll_All() {
		assertOk(CollectionChecks.check(BASE).containsAll(ALL));
	}
	
	@Test
	public void testContainsAll_NotAll() {
		assertNotOk(
			CollectionChecks.check(BASE).containsAll(NOT_ALL),
			"collection ([a, b, c]) must be containing [a, b, c, d] but was missing [d]"
		);
	}
	
	@Test
	public void testContainsAny_Empty() {
		assertOk(CollectionChecks.check(BASE).containsAny(EMPTY));
	}
	
	@Test
	public void testContainsAny_None() {
		assertNotOk(
			CollectionChecks.check(BASE).containsAny(NONE),
			"collection ([a, b, c]) must be containing one of [d, e, f] but didn't"
		);
	}
	
	@Test
	public void testContainsAny_Any() {
		assertOk(CollectionChecks.check(BASE).containsAny(ANY));
	}
	
	@Test
	public void testContainsAny_All() {
		assertOk(CollectionChecks.check(BASE).containsAny(ALL));
	}
	
	@Test
	public void testContainsAny_NotAll() {
		assertOk(CollectionChecks.check(BASE).containsAny(NOT_ALL));
	}
	
	@Test
	public void testContainsNone_Empty() {
		assertOk(CollectionChecks.check(BASE).containsNone(EMPTY));
	}
	
	@Test
	public void testContainsNone_None() {
		assertOk(CollectionChecks.check(BASE).containsNone(NONE));
	}
	
	@Test
	public void testContainsNone_Any() {
		assertNotOk(
			CollectionChecks.check(BASE).containsNone(ANY),
			"collection ([a, b, c]) must be containing none of [b] but contained [b]"
		);
	}
	
	@Test
	public void testContainsNone_All() {
		assertNotOk(
			CollectionChecks.check(BASE).containsNone(ALL),
			"collection ([a, b, c]) must be containing none of [a, b, c] but contained [a, b, c]"
		);
	}
	
	@Test
	public void testContainsNone_NotAll() {
		assertNotOk(
			CollectionChecks.check(BASE).containsNone(NOT_ALL),
			"collection ([a, b, c]) must be containing none of [a, b, c, d] but contained [a, b, c]"
		);
	}
}
