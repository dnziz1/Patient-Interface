import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Random;
	
	public class DBManager {
		private Connection connection;
		private Statement statement;
		private ResultSet resultSet;

		public static void main(String[] args) {
			DBManager db = new DBManager();		
			db.testConnection();
		}
		
		//Basic method to test connectivity with the database and print to command line the username, password, and patientID present in every row of the patient table
		public void testConnection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from patient");
				while (resultSet.next())
					System.out.println(resultSet.getString("username") + " - " + resultSet.getString("password") + " - " + resultSet.getString("patientID"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		//Checks if given credentials match those of one of the patient accounts, returns true if so and false in any other situation
		public boolean checkLogin(String un, String pw) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select username, password from patient where username = " + "'" + un + "'" + " AND password = " + "'" + pw + "'" + "");
				if (resultSet.next()) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		//Takes the username String as input and searches for the matching patient ID, then returns it as a string
		public String getPID(String un) {
			String result;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select patientid from patient where username = " + "'" + un + "'" + "");
				if (resultSet.next()) {
					result = resultSet.getString("patientid");
					return result;
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
		//Searches the messages table for all stored messages by the current user, and returns them as an array
		public String[][] getMessages(String pid) {
			
			String[][] resultData = new String[10][10];
			int i = 0;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from messages where pid = " + "'" + pid + "'" + "");
				while (resultSet.next()) {
					String messageID = resultSet.getString("messageID");
					String messageBody = resultSet.getString("messageBody");
					int j = 0;
					resultData[i][j] = messageID;
					j++;
					resultData[i][j] = messageBody;
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return resultData;
		}
		
		//Searches the messages table for all stored messages by the current user, and returns them as an array
		public String[][] getBookings(String pid) {
				
			String[][] resultData = new String[10][10];
			int i = 0;
				
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from Bookings where patientID = " + "'" + pid + "'" + "");
				while (resultSet.next()) {
					String Room = resultSet.getString("Room");
					String Day = resultSet.getString("Day");
					String Month = resultSet.getString("Month");
					String Year = resultSet.getString("Year");
					int j = 0;
					resultData[i][j] = Room;
					j++;
					resultData[i][j] = Day;
					j++;
					resultData[i][j] = Month;
					j++;
					resultData[i][j] = Year;
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			return resultData;
		}
		
		// Takes input from a String array, as well as the current PatientID, and uses them to prepare an SQL statement to insert a new booking entry into the Bookings table before executing it
		public void arrangeBooking(String[] input, String pid) {
			
			String room = input[0];
			int day = Integer.parseInt(input[1]);
			int month = Integer.parseInt(input[2]);
			int year = Integer.parseInt(input[3]);
			
			int bookingID = generateRandomIDNumber();
			int doctorID = Integer.parseInt(getDoctorID(pid));
			
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
			statement = connection.createStatement();
			
			String query = " insert into Bookings (BookingID, PatientID, DoctorID, Room, Day, Month, Year)"
			        + " values (?, ?, ?, ?, ?, ?, ?)";

			      PreparedStatement preparedStmt = connection.prepareStatement(query);
			      preparedStmt.setInt    (1, bookingID);
			      preparedStmt.setString (2, pid);
			      preparedStmt.setInt    (3, doctorID);
			      preparedStmt.setString (4, room);
			      preparedStmt.setInt    (5, day);
			      preparedStmt.setInt    (6, month);
			      preparedStmt.setInt    (7, year);

			      preparedStmt.execute();
			      
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Generates a random ID number and checks it against a given database table, if the number is unique then it is returned, else the method loops 
		public int generateRandomIDNumber() {
			
			int id = 0;
			
			Random rm = new Random();
			id = 1 + rm.nextInt((100 - 1) + 1);
			
			// Method needs rewriting to account for checking different tables. Having a check prior would be sufficient
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from Bookings where BookingID = " + "'" + id + "'" + "");
				if (resultSet.next()) {
					generateRandomIDNumber(); 
				} else {
					return id;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
			return -1;
		}
		
		// Prepares and launches an SQL query searching the database for the doctorID number using the patientID as parameter, and then returning it.
		public String getDoctorID(String pid) {
			
			String result;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select doctorID from patient where patientID = " + "'" + pid + "'" + "");
				if (resultSet.next()) {
					result = resultSet.getString("doctorID");
					return result;
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
		//Generates a message and adds it to the Messages Table in the database based on the parameter msgCode
		public void addMessage(int msgCode, String pid) {
			
			String msgBody = null;
			
			//1 = New Patient, 2 = Change Doctor, 3 = Arrange Booking, 4 = Reschedule Booking 
			switch (msgCode) {
			case 1:
				
				msgBody = "NEW PATIENT";
				
				break;
			
			case 2:
				
				msgBody = "CHANGE DOCTOR";
				
				break;
				
			case 3:
				
				msgBody = "You have made a new appointment at the clinic";
				
				break;
				
			case 4:
				
				msgBody = "RESCHEDULE BOOKING";
				
				break;
			
			}
			
			int messageID = generateRandomIDNumber();	
				
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				
				String query = " insert into Messages (messageID, messageBody, pID)"
				        + " values (?, ?, ?)";

				      PreparedStatement preparedStmt = connection.prepareStatement(query);
				      preparedStmt.setInt    (1, messageID);
				      preparedStmt.setString (2, msgBody);
				      preparedStmt.setString (3, pid);

				      preparedStmt.execute();
				      
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
		
	}

