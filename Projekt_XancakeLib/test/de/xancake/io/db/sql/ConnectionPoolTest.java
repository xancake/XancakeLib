package de.xancake.io.db.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import java.sql.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnectionPoolTest {
	private static final String host = "jdbc:oracle:thin:@ham47:1521:divaora";
	private static final String user = "PROVISION_LN";
	private static final String pass = "PROVISION_LN_P1S";
	
	private ConnectionPool myPool;
	
	@Before
	public void setUp() throws Exception {
		// TODO: Configuration korrekt laden
		DBConfiguration configuration = null;
		myPool = new ConnectionPool(configuration);
	}
	
	@After
	public void tearDown() throws Exception {
		myPool.close();
	}
	
	@Test
	public void testConnectionPool_AquireRelease() throws Exception {
		assertEquals(0, myPool.getConnectionsCount());
		Connection con = myPool.acquire();
		assertEquals(1, myPool.getConnectionsCount());
		assertEquals(1, myPool.getConnectionsInUseCount());
		assertEquals(0, myPool.getConnectionsAvailableCount());
		myPool.release(con);
		assertEquals(1, myPool.getConnectionsCount());
		assertEquals(0, myPool.getConnectionsInUseCount());
		assertEquals(1, myPool.getConnectionsAvailableCount());
	}
	
	
	
	@Test
	public void testConnectionPool_AquireReleaseAquireSame() throws Exception {
		Connection con = myPool.acquire();
		myPool.release(con);
		Connection same = myPool.acquire();
		assertSame(con, same);
	}
}
