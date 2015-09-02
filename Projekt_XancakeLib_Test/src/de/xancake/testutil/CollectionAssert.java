package de.xancake.testutil;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Set;

public class CollectionAssert {
	private CollectionAssert() {}
	
	public static <T> void assertContainsOnly(Set<T> expected, Set<T> actual) {
		assertEquals("Die Menge der tatsächlichen Elemente weicht von den erwarteten Elementen ab.", expected.size(), actual.size());
		for(T element : expected) {
			assertTrue("Ein erwartetes Element ist nicht vorhanden.", actual.contains(element));
		}
	}
	
	public static <T> void assertListEquals(List<T> expected, List<T> actual) {
		assertEquals("Die Menge der tatsächlichen Elemente weicht von den erwarteten Elementen ab.", expected.size(), actual.size());
		for(int i=0; i<expected.size(); i++) {
			assertEquals("Das tatsächliche Element am Index " + i + " entspricht nicht dem erwarteten Element.", expected.get(i), actual.get(i));
		}
	}
}
