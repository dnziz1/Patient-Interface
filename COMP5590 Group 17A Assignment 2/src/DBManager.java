import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
	
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
					String Location = resultSet.getString("Location");
					String Day = resultSet.getString("Day");
					String Month = resultSet.getString("Month");
					String Year = resultSet.getString("Year");
					int j = 0;
					resultData[i][j] = Location;
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
		
		//In Progress Code
		public void arrangeBooking(String[] input) {
			
			String pid = input[0];
			String location = input[1];
			String day = input[2];
			String month = input[3];
			String year = input[4];
			
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
			statement = connection.createStatement();
			String inputSet = ("Insert into Bookings Values ( " + "'" + 2 + "', " + "'" + pid + "', " + location + "', " + "'" + day + "', " + "'" + month + "', " + "'" + year + "' );");
			
			String query = " insert into Bookings (BookingID, PatientID, Location, Day, Month, Year)"
			        + " values (?, ?, ?, ?, ?, ?)";

			      PreparedStatement preparedStmt = connection.prepareStatement(query);
			      //preparedStmt.setInt (1, "2");
			      //preparedStmt.setString (2, pid);
			      //preparedStmt.setString   (3, location);
			      //preparedStmt.setBoolean(4, day);
			      //preparedStmt.setInt    (5, month.toInt());
			      //preparedStmt.setInt    (6, year);

			      preparedStmt.execute();
			      
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

