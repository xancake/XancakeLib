package de.xancake.io.db.sql;

import static org.junit.Assert.*;
import java.sql.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import de.xancake.io.db.sql.config.DBConfigurationSingleton;
import de.xancake.io.db.sql.config.DBConfiguration_I;

public class ConnectionPoolTest {
	private ConnectionPool myPool;
	
	@Before
	public void setUp() throws Exception {
		DBConfiguration_I configuration = DBConfigurationSingleton.getInstance().getConfiguration();
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
