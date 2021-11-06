package de.xancake.util.checks;

import static de.xancake.util.checks.ChecksAsserts.*;
import org.junit.Test;

public class IntegerChecksTest {
	@Test
	public void testEqual_Equal() {
		assertOk(IntegerChecks.check(10).equal(10));
	}
	
	@Test
	public void testEqual_NotEqual() {
		assertNotOk(IntegerChecks.check(10).equal(11), "value (10) must be equal to 11");
	}
	
	@Test
	public void testChaining_Successful() {
		assertOk(IntegerChecks.check(10).positive().less(100));
	}
	
	@Test
	public void testChaining_Unsuccessful_1() {
		assertNotOk(IntegerChecks.check(-1).positive().less(100), "value (-1) must be positive");
	}
	
	@Test
	public void testChaining_Unsuccessful_2() {
		assertNotOk(IntegerChecks.check(101).positive().less(100), "value (101) must be less than 100");
	}
	
	@Test
	public void testChaining_Unsuccessful_3() {
		assertNotOk(
			IntegerChecks.check(101).positive().less(100).equal(42),
			"value (101) must be less than 100 and equal to 42"
		);
	}
}
