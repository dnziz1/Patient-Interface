
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DBManagerTest {
	DBManager dbmanager;
	@Test
	void testTestConnection() {
		dbmanager = new DBManager();
		Connection result = dbmanager.testConnection();
		assertEquals(result != null, true);
	}

	@Test
	void testCheckLogin() {
		String checkUn = "John";
		String checkPw = "Password";
		assertEquals(true, dbmanager.checkLogin(checkUn, checkPw));
	}

	@Test
	void testGetPID() {
		String pid = "DI123";
		assertEquals(pid, dbmanager.getPID(pid));
	}

	@Test
	void testGetMessages() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBookings() {
		fail("Not yet implemented");
	}

}
