package de.xancake.io.db.sql.query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import de.xancake.io.db.sql.query.impl.JoinType;
import de.xancake.io.db.sql.query.impl.SQL;

@RunWith(JUnit4.class)
public class SQLTest {
	@SuppressWarnings("deprecation")
	public static void main(String... args) {
		System.out.println(SQL.select());
		System.out.println(SQL.select().from("test"));
		System.out.println(SQL.select().from("test", "t"));
		System.out.println(
				SQL.select()
				.from("test1", "t1")
				.join(JoinType.INNER, "test2", "t2").on("t1.aID", "t2.aID")
				.join(JoinType.INNER, "test3", "t3").on("t2.bID", "t3.bID")
//				.where("test1.name='test name'")
		);
		System.out.println(SQL.select("a1", "b2", "c3", "d4"));
		
		System.out.println(SQL.insertInto("test1").values(2, "test", 3.25));
		System.out.println(SQL.insertInto("test2", "a1", "a2", "a3").values(2, "test", 3.25));
		System.out.println(SQL.insertInto("test3", "a1", "a2", "a3").valuesWildcard());
		
		System.out.println(SQL.custom("SELECT * FROM test1"));
	}
	
	@Test
	public void testSQL_1() throws Exception {
		
		
	}
}
