//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Random;

	/**
	* A class to handle the database intergration and all the methods for dealing with the database
	* which will allow the GUI class to import and use those methods
	*/
	public class DBManager 
	{
		private Connection connection;
		private Statement statement;
		private ResultSet resultSet;

        //Main
		public static void main(String[] args) 
		{
			//Create instance of database and run connection
			DBManager db = new DBManager();		
			db.testConnection();
		}
		
		/**
	    * A method to test connectivity with the database and print to command line the username, 
		* password, and patientID present in every row of the patient table
	    */
		public void testConnection() 
		{
			try 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from patient");
				while (resultSet.next())
					System.out.println(resultSet.getString("username") + " - " + resultSet.getString("password") + " - " + resultSet.getString("patientID"));
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}
		
		/**
	    * A method that checks if the given credentials match those of one of the patient accounts, 
		* returns true if so and false in any other situation
	    */
		public boolean checkLogin(String un, String pw) 
		{
			try 
			{
				//Establish connection to DB
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select username, password from patient where username = " + "'" + un + "'" + " AND password = " + "'" + pw + "'" + "");
				if (resultSet.next()) 
				{
					return true;
				} 
				else 
				{
					return false;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return false;
			}
			
		}
		
		/**
	    * A method that takes the username String as input and searches for the matching patient ID, then returns it as a string
	    */
		public String getPID(String un) 
		{
			String result;
			try 
			{
				//Establish connection to DB
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select patientid from patient where username = " + "'" + un + "'" + "");
				if (resultSet.next())
				{
					//Return PID
					result = resultSet.getString("patientid");
					return result;
				} 
				else 
				{
					return null;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return null;
			}	
		}
		
		/**
	    * A method that searches the messages table for all stored messages by the current user, and returns them as an array
	    */
		public String[][] getMessages(String pid) 
		{
			//Create array
			String[][] resultData = new String[25][25];
			int i = 0;	

			try 
			{
				//Establish connection to DB
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from messages where pid = " + "'" + pid + "'" + "");
				while (resultSet.next()) 
				{
					//Get message details
					String messageID = resultSet.getString("messageID");
					String messageBody = resultSet.getString("messageBody");
					int j = 0;
					//Insert into array
					resultData[i][j] = messageID;
					j++;
					resultData[i][j] = messageBody;
					i++;
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return resultData;
		}
		
		/**
	    * A method that searches the bookings table for all stored bookings by the current user, and returns them as an array
	    */
		public String[][] getBookings(String pid) 
		{
			//Create array
			String[][] resultData = new String[20][20];
			int i = 0;
				
			try 
			{
				//Establish connection to DB
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from Bookings where patientID = " + "'" + pid + "'" + "");
				//Iterate through DB
				while (resultSet.next()) 
				{
					//Get details
					String BookingID = resultSet.getString("BookingID");
					String Room = resultSet.getString("Room");
					String Day = resultSet.getString("Day");
					String Month = resultSet.getString("Month");
					String Year = resultSet.getString("Year");
					int j = 0;
					//Insert into array
					resultData[i][j] = BookingID;
					j++;
					resultData[i][j] = Room;
					j++;
					resultData[i][j] = Day;
					j++;
					resultData[i][j] = Month;
					j++;
					resultData[i][j] = Year;
					i++;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}	
			return resultData;
		}
		
		/**
	    * A method that takes an input from a String array, as well as the current PatientID, and uses them 
		* to prepare an SQL statement to insert a new booking entry into the Bookings table before executing it
	    */
		public void arrangeBooking(String[] input, String pid) 
		{
			//Get inputted deatils passed in by the method
			String room = input[0];
			int day = Integer.parseInt(input[1]);
			int month = Integer.parseInt(input[2]);
			int year = Integer.parseInt(input[3]);
            //Generate random patient ID and get the doctor ID from the DB
			int bookingID = generateRandomIDNumber(1);
			int doctorID = Integer.parseInt(getDoctorID(pid));
			try 
			{
				//Establish connection to DB
			    Class.forName("com.mysql.cj.jdbc.Driver");
			    connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
			    statement = connection.createStatement();

				//Create a statement to enter the details into the database
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
                //Run statement
			    preparedStmt.execute();     
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		/**
	    * A method that generates a random ID number and checks it against a given database table, 
		* if the number is unique then it is returned, else the method loops
	    */ 
		public int generateRandomIDNumber(int code) 
		{
			int id = 0;
			//Create random
			Random rm = new Random();
			id = 1 + rm.nextInt((500 - 1) + 1);
			try 
			{
				//Establish connection to DB
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				if (code == 1) {
					
					resultSet = statement.executeQuery("select * from Bookings where BookingID = " + "'" + id + "'" + "");
					
					if (resultSet.next()) 
					{
						generateRandomIDNumber(1); 
					} 
					else 
					{
						return id;
					}
					
				} else if (code == 2) {
					
					resultSet = statement.executeQuery("select * from Messages where messageID = " + "'" + id + "'" + "");
					
					if (resultSet.next()) 
					{
						generateRandomIDNumber(2); 
					} 
					else 
					{
						return id;
					}
					
				} else {
					
					resultSet = statement.executeQuery("select * from Patient where patientID = " + "'" + id + "'" + "");
					
					if (resultSet.next()) 
					{
						generateRandomIDNumber(3); 
					} 
					else 
					{
						return id;
					}
					
				}
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return -1;
			}
			return -1;
		}
		
		/**
	    * A method that prepares and launches an SQL query searching the database for the doctorID number using the 
		* patientID as parameter, and then returning it
	    */ 
		public String getDoctorID(String pid) 
		{
			String result;
			try 
			{
				//Establish connection to DB
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				//Excecute SQL query
				resultSet = statement.executeQuery("select doctorID from patient where patientID = " + "'" + pid + "'" + "");
				if (resultSet.next()) 
				{
					result = resultSet.getString("doctorID");
					return result;
				} 
				else 
				{
					return null;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return null;
			}	
		}
		
		/**
	    * A method that generates a message and adds it to the Messages Table in the database based on the parameter msgCode
	    */ 
		public void addMessage(int msgCode, String pid, String additionalInput) 
		{	
			String msgBody = null;
			//1 = New Patient, 2 = Change Doctor, 3 = Arrange Booking, 4 = Reschedule Booking 
			//Switch code based on the above input key
			switch (msgCode) 
			{
			case 1:
				
				msgBody = "Your Patient Registration is Complete";
				
				break;
			
			case 2:
				
				msgBody = "Your Primary Doctor is Now Doctor " + additionalInput;
				
				break;
				
			case 3:
				
				msgBody = "New Appointment Scheduled for " + additionalInput;
				
				break;
				
			case 4:
				
				msgBody = "Appointment Rescheduled for " + additionalInput;
				
				break;
			
			}
			int messageID = generateRandomIDNumber(2);	

			try 
			{
				//Establish connection to DB
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				//Create SQL query
				String query = " insert into Messages (messageID, messageBody, pID)"
				        + " values (?, ?, ?)";

				      PreparedStatement preparedStmt = connection.prepareStatement(query);
				      preparedStmt.setInt    (1, messageID);
				      preparedStmt.setString (2, msgBody);
				      preparedStmt.setString (3, pid);

				      preparedStmt.execute();
				      
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
		}
		
		/**
	    * A method that excecutes an SQL query to get a doctors last name based on a passed in doctor ID
	    */
		public String getDoctorLastName(String doctorID) 
		{	
			String result;
			try 
			{
				//Establish connection to DB
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
				statement = connection.createStatement();
				//Excecute SQL statement
				resultSet = statement.executeQuery("select lastName from doctors where doctorID = " + "'" + doctorID + "'" + "");
				if (resultSet.next())
				{
					result = resultSet.getString("lastName");
					return result;
				} 
				else 
				{
					return null;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return null;
			}
		}
		
		/**
	    * A method that takes the details of an appointment form and builds them into a formatted string, 
		* returning it when completed
	    */
		public String constructAppointmentDate(String[] appointmentDetails) 
		{
			String appointmentDate = appointmentDetails[0] + "/" + appointmentDetails[1] + "/" + appointmentDetails[2];
			return appointmentDate;
		}
		
		/**
		    * A method that takes an input from a String array, as well as a bookingID, and uses them 
			* to prepare an SQL statement to update an already existing entry into the Bookings table before executing it
		    */
		public void submittedBooking(String[]input, String bID) 
		{
			//Get data passed in to be used in the SQL statement
			int bookingID = Integer.parseInt(bID);
			int day = Integer.parseInt(input[0]);
			int month = Integer.parseInt(input[1]);
			int year = Integer.parseInt(input[2]);
	
			try 
			{
				//Establish connection to DB
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
			   statement = connection.createStatement();
			   //Create and execute an SQL statement based on the inputted data
			   String query = " Update Bookings Set day = ?, month = ?, year = ? where bookingID = ?";

			      PreparedStatement preparedStmt = connection.prepareStatement(query);
			      preparedStmt.setInt    (1, day);
			      preparedStmt.setInt    (2, month);
			      preparedStmt.setInt    (3, year);
			      preparedStmt.setInt    (4, bookingID);

			      preparedStmt.execute();    
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	}
	
	/**
	* A method that checks if a given booking is valid against a patient ID and booking ID
	*/
	public Boolean isValidBookingID (String pID, String bID) 
	{
		try 
		{
			//Establish connection to DB
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
			statement = connection.createStatement();
			//Create an SQL statement from passed in IDs
			resultSet = statement.executeQuery("select BookingID from Bookings where PatientID = " + "'" + pID + "'" + " AND BookingID = " + "'" + bID + "'" + "");
			if (resultSet.next()) 
			{
				return true;
			} 
			else 
			{
				return false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	* A auxiliary method that searches through given input data for a booking and determines if 
	* it is valid by attempting to parse each item
	*/
	public Boolean isValidBookingData(String[] input) {
		
		for (String s: input) {
			try {
				int i = Integer.parseInt(s);
			} catch (NumberFormatException E) {
				return false;
			}
		}
		return true;
	}
	
	/**
	* A method that checks if there are any conflicting bookings with the given input data, matching against the doctor ID
	* and returning false if so
	*/
	public Boolean isDoctorAvailable(String[] input, String pID) {
		
		String doctorID = getDoctorID(pID);
		String day = input[0];
		String month = input[1];
		String year = input[2];
		
		try 
		{
			//Establish connection to DB
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
			statement = connection.createStatement();
			//Create an SQL statement from passed in IDs
			resultSet = statement.executeQuery("select BookingID from Bookings where DoctorID = " + "'" + doctorID + "'" + " AND Day = " + "'" + day + "'" + " AND Month = " + "'" + month + "'" + " AND Year = " + "'" + year + "'" + "");
			
			try {
				if (resultSet.next() || resultSet.getString("BookingID") != null) {
					return false;
				} 
			} catch (SQLException se) {
				return true;
			}
			
			return false;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	* A method that searches the messages table for all stored messages by the current user, 
	* and returns them as an array
	*/
	public String[][] getDoctors(String pid) 
	{		
		//Create array for results
		String[][] resultData = new String[20][20];
		int i = 0;
		
		try 
		{
			//Establish connection to DB
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
			statement = connection.createStatement();
			//Execute SQL statement
			resultSet = statement.executeQuery("select * from Doctors");
			//Iterate through results of SQL
			while (resultSet.next()) 
			{
				//Get results from the DB
				String DoctorID = resultSet.getString("DoctorID");
				String LastName = resultSet.getString("lastName");
				String Speciality = resultSet.getString("Speciality");
				int j = 0;
				//Add data to the array
				resultData[i][j] = DoctorID;
				j++;
				resultData[i][j] = LastName;
				j++;
				resultData[i][j] = Speciality;
				i++;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return resultData;
	}

    /**
	* A method that changes the patients current doctor based on a given input and patient ID
	*/
	public void changeCurrentDoctor(String input, String pid) 
	{
		//Get IDs for doctors
		int DoctorID = Integer.parseInt(input);
		int prevDoctorID = Integer.parseInt(getDoctorID(pid));

		try 
		{
			//Establish connection to DB
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
			statement = connection.createStatement();
            //Create SQL query to update the database with the new doctor
			String query = " Update Patient Set DoctorID = ? where DoctorID = ?";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt    (1, DoctorID);
			preparedStmt.setInt    (2, prevDoctorID);

			preparedStmt.execute();
	  
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	* A method that checks if the given id matches an entry in the database
	*/
	public boolean isValidDoctorID (String dID) {
		try 
		{
			//Checks that the input contains only numbers, by parsing it prior to attempting SQL statement
			try {
				Integer doctorID = Integer.parseInt(dID);
			} catch (NumberFormatException E) {
				return false;
			}
			
			//Establish connection to DB
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
			statement = connection.createStatement();
			//Create an SQL statement from passed in IDs
			resultSet = statement.executeQuery("select doctorID from Doctors where doctorID = " + "'" + dID + "'");
			if (resultSet.next()) 
			{
				return true;
			} 
			else 
			{
				return false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	* A method that registers a new patient into the database with all their given info
	*/
	public Boolean registerPatient(String Email, String Username, String Password, String FirstName, String LastName, String Sex, String PhoneNo, String Address) 
	{
		//Store all information passed into method to variables
		String email = Email;
		String username = Username;
		String password = Password;
		String firstName = FirstName;
		String lastName = LastName;
		String sex = Sex;
		String phoneNo = PhoneNo;
		String address = Address;
		int patientID = generateRandomIDNumber(3);
		int doctorID = 1;
		
		if (sex.equals("m") || sex.equals("M") || sex.equals("w") || sex.equals("W")) {
			try 
			{
				//If the supplied username already exists in the database, throws error and doesn't attempt insert
				if (getPID(username) != null) {
					return false;
				} else {
					//Establish connection to DB
					Class.forName("com.mysql.cj.jdbc.Driver");
					connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
					statement = connection.createStatement();
					//Create a query to add the new patient into the DB
			    	String query = " insert into Patient (email, username, password, patientID, firstName, lastName, sex, phoneNo, address, doctorID)"
			            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			    	//Add to the query the passed details of the patient
			    	PreparedStatement preparedStmt = connection.prepareStatement(query);
			    	preparedStmt.setString (1, email);
			    	preparedStmt.setString (2, username);
			    	preparedStmt.setString (3, password);
			    	preparedStmt.setInt    (4, patientID);
			    	preparedStmt.setString (5, firstName);
			    	preparedStmt.setString (6, lastName);
			    	preparedStmt.setString (7, sex);
			    	preparedStmt.setString (8, phoneNo);
			    	preparedStmt.setString (9, address);
			    	preparedStmt.setInt    (10, doctorID);

			    	preparedStmt.execute();
			    	
			    	return true;
			    	
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
		
	}
	
	/**
	* A method that gets the visit details for a patient with a given patient ID
	*/
	public String[][] getVisitDetails(String pID) 
	{
		//Create an array to hold the data collected from the DB
		String[][] resultData = new String[20][20];
		int i = 0;
			
		try 
		{
			//Establish connection to DB
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
			statement = connection.createStatement();
			//Create a statement from the passed in PID
			resultSet = statement.executeQuery("select * from VPDetails where PatientID = " + "'" + pID + "'" + "");
			//Iterate through the results of the query
			while (resultSet.next()) 
			{
				//Get data from database
				String DoctorID = resultSet.getString("DoctorID");
				String BookingID = resultSet.getString("BookingID");
				String visitDetails = resultSet.getString("visitDetails");
				String Prescriptions = resultSet.getString("Prescriptions");
				int j = 0;
				//Add data to the array
				resultData[i][j] = DoctorID;
				j++;
				resultData[i][j] = BookingID;
				j++;
				resultData[i][j] = visitDetails;
				j++;
				resultData[i][j] = Prescriptions;
				i++;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return resultData;
	}

    /**
	* A method that creates a log of a users access to be added into the logAcess table
	*/
	public void accessLogs(String pid, String funct ) 
	{
		//Get data to be added
		int patientId = Integer.parseInt(pid);
		String functionality = funct;
		Timestamp dateTime = new Timestamp(System.currentTimeMillis());
		
		try 
		{
			//Establish connection to DB
			Class.forName("com.mysql.cj.jdbc.Driver");
		    connection = DriverManager.getConnection("jdbc:mysql://localhost/a217a?user=root&password=root");
		    statement = connection.createStatement();
		    //Create a new query to add into the logAccess table the data passed into the method
		    String query = " insert into logAccess (patientId, dateAccessed, functionality)"
		            + " values (?, ?, ?)";

		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		      preparedStmt.setInt    (1, patientId);
		      preparedStmt.setTimestamp(2, dateTime);
		      preparedStmt.setString    (3, functionality);

		      preparedStmt.execute();
		      
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}		
}
