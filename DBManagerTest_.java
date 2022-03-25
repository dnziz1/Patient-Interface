import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;

import org.junit.jupiter.api.Test;

class DBManagerTest {
	private Connection conn;
	private Statement stat;
	@Test
	void testTestConnection() {
		System.out.println("testConnection");
		System.out.println("js048 " + "j0eSm1th");
	}

	@Test
	void testCheckLogin(String un, String pw) {
		System.out.println("checkLogin");
		boolean expect = false;
		DBManager instance = new DBManager();
		instance.checkLogin(un, pw);
		boolean result = true;
		assertEquals(expect, result);
	}

	@Test
	void testGetPID(String un) {
		System.out.println("getPID");
		String pid = "joe234";
		DBManager instance = new DBManager();
		instance.getPID(un);
		String result = "patientid";
		assertEquals(pid, result);
	}

	@Test
	void testGetMessages(String pid) {
		System.out.println("getMessages");
		String msg = "select * from messages where pid = " + "'" + pid + "'" + "";
		DBManager instance = new DBManager();
		instance.getMessages(pid);
		String result = "messageID / messageBody";
		assertEquals(msg, result);
	}

	@Test
	void testGetBookings(String pid, String month, String year) {
		System.out.println("getBookings");
		DBManager instance = new DBManager();
		String expected = "Booking at (location) on (day) (month) (year)";
		instance.getBookings(pid, month, year);
		String result = "jo123";
		assertEquals(expected, result);
	}
	
	@Test
	void testGetAllBookings(String pid) {
		System.out.println("getAllBookings");
		DBManager instance = new DBManager();
		String expected = "select * from Bookings where patientID = " + "'" + pid + "'" + "";
		instance.getAllBookings(pid);
		String result = "jo123";
		assertEquals(expected, result);
	}
	
	@Test
	void testArrangeBooking(String[] input, String pid) {
		System.out.println("arrangeBooking");
		DBManager instance = new DBManager();
		String expected = " insert into Bookings (BookingID, PatientID, DoctorID, Room, Day, Month, Year)"
		        + " values (?, ?, ?, ?, ?, ?, ?)";
		instance.arrangeBooking(input, pid);
		String result = "jo123";
		assertEquals(expected, result);
	}
	
	@Test
	void testGenerateRandomIDNumber(int code) {
		Random rm = new Random();
		System.out.println("generateRandomIDNumber");
		DBManager instance = new DBManager();
		int result = 1 + rm.nextInt((100 - 1) + 1);
		String expected = "select * from Bookings where BookingID = " + "'" + result + "'" + "";		
		instance.generateRandomIDNumber(code);
		assertEquals(expected, result);
	}
	
	@Test
	void testGetDoctorID() {
		System.out.println("getDoctorID");
		DBManager instance = new DBManager();
		String result = "001";
		String expected = "select doctorID from patient where patientID = " + "'" + result + "'" + "";
		instance.getDoctorID(expected);
		instance.getDoctorID(result);
		assertEquals(expected, result);
	}
	
	@Test
	void testAddMessage(int msgCode, String pid, String additionalInput) {
		System.out.println("addMessage");
		DBManager instance = new DBManager();
		String result = "" + additionalInput;
		String expected = null;
		instance.addMessage(1, pid, additionalInput);
		instance.addMessage(2, pid, additionalInput);
		instance.addMessage(3, pid, additionalInput);
		instance.addMessage(4, pid, additionalInput);
		assertEquals(expected, result);
	}
	
	@Test
	void testGetDoctorLastName(String doctorID) {
		System.out.println("getDoctorLastName");
		DBManager instance = new DBManager();
		String expect = "select lastName from doctors where doctorID = " + "'" + doctorID + "'" + "";
		instance.getDoctorLastName(doctorID);
		String result = "lastName";
		assertEquals(expect, result);
	}
	@Test
	void testConstructAppointmentDate(String[] appointmentDetails) {
		System.out.println("constructAppointmentDate");
	}
	@Test
	void testSubmittedBooking(String[] input, String bID) {
		System.out.println("submittedBooking");
		DBManager instance = new DBManager();
		String expected = " Update Bookings Set day = ?, month = ?, year = ? where bookingID = ?\"";
		String result = "5";
		instance.submittedBooking(input, bID);
		assertEquals(expected, result);
	}
	
	@Test
	void testIsValidBookinID() {
		System.out.println("isValidBookingID");
		DBManager instance = new DBManager();
		String pID = "001";
		String bID = "001";
		boolean result = true;
		String expected = "select BookingID from Bookings where PatientID = " + "'" + pID + "'" + " AND BookingID = " + "'" + bID + "'" + "";
		instance.isValidBookingID(pID, bID);
		instance.isValidBookingID(pID, bID);
		assertEquals(expected, result);
	}
	@Test
	void testIsValidBookingData(String[] input) {
		System.out.println("isValidBookingData");
		DBManager instance = new DBManager();
		boolean result = true;
		instance.isValidBookingData(input);
		assertEquals(input, result);
	}
	@Test
	void testIsDoctorAvailable(String[] input, String pID) {
		System.out.println("isDoctorAvailable");
		DBManager instance = new DBManager();
		boolean result = false;
		instance.isDoctorAvailable(input, pID);
		assertEquals(input, result);
	}
	@Test
	void testGetDoctors() {
		System.out.println("getDoctor");
		DBManager instance = new DBManager();
		String expected = "select * from Doctors";
		instance.getDoctors(expected);
		String result = "001";
		instance.getDoctors(result);
		assertEquals(expected, result);
	}
	
	@Test
	void testChangeCurrentDoctor() {
		System.out.println("changeCurrentDoctor");
		DBManager instance = new DBManager();
		String expected = " Update Patient Set DoctorID = ? where DoctorID = ?";
		String result = "5";
		instance.changeCurrentDoctor(expected, result);
		instance.changeCurrentDoctor(expected, result);
		assertEquals(expected, result);
	}
	@Test
	void testIsValidDoctorID(String dID) {
		System.out.println("isValidDoctorID");
		DBManager instance = new DBManager();
		boolean result = true;
		instance.isValidDoctorID(dID);
		assertEquals(dID, result);
	}
	@Test
	void testRegisterPatient(String Email, String Username, String Password, String FirstName, String LastName, String Sex, String PhoneNo, String Address) {
		System.out.println("isValidBookingData");
		DBManager instance = new DBManager();
		boolean expect = false;
		boolean result = true;
		instance.registerPatient(Email, Username, Password, FirstName, LastName, Sex, PhoneNo, Address);
		assertEquals(expect, result);
	}
	@Test
	void testGetVisitDetails(String pID) {
		System.out.println("getVisitDetails");
		DBManager instance = new DBManager();
		String expected = "select * from VPDetails where PatientID = " + "'" + pID + "'" + "";
		instance.getVisitDetails(pID);
		String result = "001";
		assertEquals(expected, result);
	}
	@Test
	void testAccessLogs() {
		System.out.println("accessLogs");
		DBManager instance = new DBManager();
		String pid = "001";
		String funct = "View Messages";
		String expected = " insert into logAccess (patientId, dateTime, functionality)"
		        + " values (?, ?, ?)";
		instance.accessLogs(pid, funct);
		String result = "001";
		instance.accessLogs(pid, funct);
		assertEquals(expected, result);
	}
}
