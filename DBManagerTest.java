import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.mysql.cj.xdevapi.Statement;

class DBManagerTest {
	private DBManagerTest dbmanager;
	private Connection conn;
	private Statement stat;
	@Test
	void testTestConnection() {
		System.out.println("Databse should be connected.");
	}

	@Test
	void testCheckLogin(String un, String pw) {
		System.out.println("checkLogin");
		boolean expect = false;
		DBManager instance = new DBManager();
		instance.checkLogin(un, pw);
		boolean result = instance.checkLogin(un, pw);
		assertEquals(expect, result);
		
	}

	@Test
	void testGetPID(String un) {
		System.out.println("getPID");
		String pid = "joe234";
		DBManager instance = new DBManager();
		instance.getPID(pid);
		String result = instance.getPID(un);
		assertEquals(pid, result);
	}

	@Test
	void testGetMessages() {
		System.out.println("getMessages");
		String msg = "You have scheduled an appointment...";
		DBManager instance = new DBManager();
		instance.getPID(msg);
		String result = instance.getPID(msg);
		assertEquals(msg, result);
	}

	@Test
	void testGetBookings() {
		System.out.println("getBookings");
		DBManager instance = new DBManager();
		String expected = "Booking at (location) on (day) (month) (year)";
		instance.getBookings(expected);
		String result = instance.getBookings();
		assertEquals(expected, result);
	}

}
