//UI imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Date;
import java.util.logging.Logger;

/**
* Main class to handle all of the UI aspect of the project will methods to create
* each aspect of the interface with a main method to initialise the starting UI for the user
*/
public class PatientInterface
{
    //Variables for the first frame which will be the main login frame
    private JFrame frameMain;
    private JLabel labelMain;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton buttonMain;
    
    //Variables for the second frame which will show messages
    private JFrame frameViewM;
    private JLabel labelViewM;
    private JTable tableViewM;
    private JButton buttonViewM1;
    private JButton buttonViewM2;
    
    //Variables for the sub third frame which will allow parameter input to select bookings
    private JFrame frameVBP;
    private JLabel labelVBP;
    private JLabel labelVBP2;
    private JTextField monthFieldVBP;
    private JTextField yearFieldVBP;
    private JButton buttonVBP;
    
    //Variables for the third frame which will show bookings
    private JFrame frameShowB;
    private JLabel labelShowB;
    private JTable tableShowB;
    
    //Variables for the fourth frame which will arrange new bookings
    private JFrame frameArrangeB;
    private JLabel labelArrangeB;
    private JButton buttonArrangeB;
    private JTextField locationField;
    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;
    private JLabel labelArrangeB1;
    private JLabel labelArrangeB2;
    private JLabel labelArrangeB3;
    private JLabel labelArrangeB4;
    
    //Variables for the fifth frame which will reschedule bookings
    private JButton buttonResB1;
    private JFrame frameResB;
    private JLabel labelResB;
    private JTable tableResB;
    private JTextField textResB;
    private JButton buttonResB2;
    
    //Variables for the sixth frame which will amend bookings
    private JFrame frameAmendB;
    private JLabel labelAmendB1;
    private JLabel labelAmendB2;
    private JLabel labelAmendB3;
    private JTextField textAmendB1;
    private JTextField textAmendB2;
    private JTextField textAmendB3;
    private JButton buttonAmendB;
    
    //Variables for the seventh frame which will change doctor
    private JButton buttonChangeD1;
    private JFrame frameChangeD;
    private JLabel labelChangeD;
    private JTable tableChangeD;
    private JTextField textChangeD;
    private JButton buttonChangeD2;
    
    //Variables for the eight frame which will give the patient an option to register and login
    private JFrame frameLogReg;
    private JLabel labelLogReg1;
    private JLabel labelLogReg2;
    private JButton buttonLogReg1;
    private JButton buttonLogReg2;
    
    //Variables for the eight frame which will give the patient an option to register and login
    private JFrame frameReg;
    private JLabel labelReg;
    private JLabel labelReg1;
    private JTextField textReg1;
    private JLabel labelReg2;
    private JTextField textReg2;
    private JLabel labelReg3;
    private JPasswordField textReg3;
    private JLabel labelReg4;
    private JTextField textReg4;
    private JLabel labelReg5;
    private JTextField textReg5;
    private JLabel labelReg6;
    private JTextField textReg6;
    private JLabel labelReg7;
    private JTextField textReg7;
    private JLabel labelReg8;
    private JTextField textReg8;
    private JButton buttonReg;
    
    //variables for view visit details and prescriptions JFrame
    private JFrame frameVP;
    private JLabel labelVP;
    private JTable tableVP;
    private JButton buttonViewDP;
    
    //Used to log ongoing feature access and output to terminal
    private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    Date date = new Date();
    
    //Link to DBManager
    public DBManager dbm = new DBManager();	
    public String loggedUser;

    //Main
    public static void main(String[] args)
    {
    	
        //Create the patient interface
        PatientInterface pi = new PatientInterface();
        //Create the starting interface which will give the user
        //the option to login or Register
        pi.loginRegisterInterface();
        
    }
    
    /**
    * First method to create the starting interface which will be the first thing
    * the user will see and will prompt them to either login or Register
    */
    private void loginRegisterInterface()
    {
    	frameLogReg = new JFrame("Patient Interface: Register or Login");
    	frameLogReg.setSize(400, 400);
    	frameLogReg.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;

        labelLogReg1 = new JLabel("Login as an existing patient:");
        constraint.gridx = 1;
        constraint.gridy = 1;
        frameLogReg.add(labelLogReg1, constraint);
        
        buttonLogReg1 = new JButton("Login");
        constraint.gridx = 1;
        constraint.gridy = 2;
        frameLogReg.add(buttonLogReg1, constraint);
        //Action listener to check for the users click
        buttonLogReg1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //If the user chooses to login they will be sent to the login
                //frame
            	frameLogReg.setVisible(false);
                setupInterface();
            }
        });
        
        labelLogReg2 = new JLabel("Register as a new patient:");
        constraint.gridx = 1;
        constraint.gridy = 3;
        frameLogReg.add(labelLogReg2, constraint);
        
        buttonLogReg2 = new JButton("Register");
        constraint.gridx = 1;
        constraint.gridy = 4;
        frameLogReg.add(buttonLogReg2, constraint);
        //Action listener to check for the users click
        buttonLogReg2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //If the user choses to Register they will be sent to the Register
                //frame
            	frameLogReg.setVisible(false);
            	registerInterface();
            }
        });
        frameLogReg.setVisible(true);
    }
    
    /**
    * This method will create a frame to allow the user to register as a new
    * patient which will also update the DB with their details and also
    * let them into the system
    */
    //Method to create the register jframe
    private void registerInterface()
    {
        frameReg = new JFrame("Patient Interface: Register");
        frameReg.setSize(600, 600);
        frameReg.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        
        //A label and button for each field the user is require to enter
        labelReg = new JLabel("Register");
        constraint.gridx = 0;
        constraint.gridy = 1;
        frameReg.add(labelReg, constraint);
        
        labelReg1 = new JLabel("Enter an Email:");
        constraint.gridx = 0;
        constraint.gridy = 2;
        frameReg.add(labelReg1, constraint);
        
        textReg1 = new JTextField(12);
        constraint.gridx = 0;
        constraint.gridy = 3;
        frameReg.add(textReg1, constraint);
        
        labelReg2 = new JLabel("Enter a Username:");
        constraint.gridx = 0;
        constraint.gridy = 4;
        frameReg.add(labelReg2, constraint);
        
        textReg2 = new JTextField(12);
        constraint.gridx = 0;
        constraint.gridy = 5;
        frameReg.add(textReg2, constraint);
        
        labelReg3 = new JLabel("Enter a Password:");
        constraint.gridx = 0;
        constraint.gridy = 6;
        frameReg.add(labelReg3, constraint);
        
        textReg3 = new JPasswordField(12);
        constraint.gridx = 0;
        constraint.gridy = 7;
        frameReg.add(textReg3, constraint);
        
        labelReg4 = new JLabel("Enter your Forename:");
        constraint.gridx = 0;
        constraint.gridy = 8;
        frameReg.add(labelReg4, constraint);
        
        textReg4 = new JTextField(12);
        constraint.gridx = 0;
        constraint.gridy = 9;
        frameReg.add(textReg4, constraint);
        
        labelReg5 = new JLabel("Enter your Surname:");
        constraint.gridx = 0;
        constraint.gridy = 10;
        frameReg.add(labelReg5, constraint);
        
        textReg5 = new JTextField(12);
        constraint.gridx = 0;
        constraint.gridy = 11;
        frameReg.add(textReg5, constraint);
        
        labelReg6 = new JLabel("Enter your Sex:");
        constraint.gridx = 0;
        constraint.gridy = 12;
        frameReg.add(labelReg6, constraint);
        
        textReg6 = new JTextField(12);
        constraint.gridx = 0;
        constraint.gridy = 13;
        frameReg.add(textReg6, constraint);
        
        labelReg7 = new JLabel("Enter your Phone-number:");
        constraint.gridx = 0;
        constraint.gridy = 14;
        frameReg.add(labelReg7, constraint);
        
        textReg7 = new JTextField(12);
        constraint.gridx = 0;
        constraint.gridy = 15;
        frameReg.add(textReg7, constraint);
        
        labelReg8 = new JLabel("Enter your Address:");
        constraint.gridx = 0;
        constraint.gridy = 16;
        frameReg.add(labelReg8, constraint);
        
        textReg8 = new JTextField(12);
        constraint.gridx = 0;
        constraint.gridy = 17;
        frameReg.add(textReg8, constraint);
        
        //Button to submit the registered details
        buttonReg= new JButton("Register");
        constraint.gridx = 0;
        constraint.gridy = 19;
        frameReg.add(buttonReg, constraint);
        //Action listener to check for the users click
        buttonReg.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //Collect all the users entered information
                String email = textReg1.getText();
                String username = textReg2.getText();
                String password = textReg3.getText();
                String firstName = textReg4.getText();
                String lastName = textReg5.getText();
                String sex = textReg6.getText();
                String phoneNo =  textReg7.getText();
                String address = textReg8.getText();
            	
                //Use the DB manager method to register a patient with the above details into the DB
                if (dbm.registerPatient(email, username, password, firstName, lastName, sex, phoneNo, address)) {
                	
                	frameReg.setVisible(false);
            	
                	//Set the new user as the currently logged in user
                	loggedUser = dbm.getPID(username);

                	//Send messages out
                	log.info("A New Patient Has Been Registered at " + date);
                	String msg = "New Patient Registered";
                	dbm.accessLogs(loggedUser, msg);
              	
                	dbm.addMessage(1, loggedUser, null);
            	
                	//Allow the user to change their doctor
                	changeDoctor();     
            	
                } else {
                	JOptionPane.showMessageDialog(frameReg, "There was an error processing your details. Either the information was not valid or your username may already be taken.");
                }
            }
        });
        frameReg.setVisible(true);
    }
    
    /**
    * This method will create a frame to allow the user to Login as an existing
    * patient provided they pass the checks on the DB to make sure the user does in fact
    * exist
    */
    private void setupInterface()
    {
        frameMain = new JFrame("Patient Interface: Login");
        frameMain.setSize(400, 200);
        frameMain.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        labelMain = new JLabel("Login");
        constraint.gridx = 0;
        constraint.gridy = 0;
        frameMain.add(labelMain, constraint);

        //Username text field
        labelMain = new JLabel("Username: ");
        constraint.gridx = 0;
        constraint.gridy = 1;
        frameMain.add(labelMain, constraint);
        
        usernameField = new JTextField(12);
        constraint.gridx = 1;
        constraint.gridy = 1;
        frameMain.add(usernameField, constraint);

        //Password password field (in order to hide password when its entered)
        labelMain = new JLabel("Password: ");
        constraint.gridx = 0;
        constraint.gridy = 2;
        frameMain.add(labelMain, constraint);
        
        passwordField = new JPasswordField(12);
        constraint.gridx = 1;
        constraint.gridy = 2;
        frameMain.add(passwordField, constraint);

        //Button to submit the login details
        buttonMain = new JButton("Enter");
        constraint.gridx = 1;
        constraint.gridy = 3;
        frameMain.add(buttonMain, constraint);
        
        //Action listener to check for the users click
        buttonMain.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //Get users inputs
                String Username = usernameField.getText();
                String Password = passwordField.getText();

                //If to check if a username matches the database and password also matches
                if(dbm.checkLogin(Username, Password))
                {
                	loggedUser = dbm.getPID(Username);
                    log.info(loggedUser + " has Logged into the system at " + date);
                    String msg = "Logged in";
                  	dbm.accessLogs(loggedUser, msg);
                    frameMain.setVisible(false);
                    viewMessages();
                }
                else
                {
                    //Error message
                    JOptionPane.showMessageDialog(frameMain, "Incorrect Username or Password");
                    //Set password to blank
                    passwordField.setText("");
                }
            }
        });
        frameMain.setVisible(true);	
    }

    /**
    * This method will create a frame to show the user their current messages when called
    */
    private void viewMessages()
    {
        frameViewM = new JFrame("Patient Interface: View Messages");
        frameViewM.setLayout(new GridBagLayout());
        GridBagConstraints constraint2 = new GridBagConstraints();
        constraint2.gridx = 0;
        constraint2.gridy = 0;
        
        //Create button to allow user to make a new booking
        buttonViewM2 = new JButton("Make New Bookings");
        buttonViewM2.setBounds(50,100,95,30);  
        frameViewM.add(buttonViewM2, constraint2);
        buttonViewM2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //Call arrange new booking interface
            	arrangeNewBooking();
            }
        });
        
        //Create button to allow user to open the View Bookings Window
        constraint2.gridx = 2;
        buttonViewM1 = new JButton("View Bookings");
        buttonViewM1.setBounds(50,100,95,30);  
        frameViewM.add(buttonViewM1, constraint2);
        //Action listener to populate the table once the user presses view bookings
        buttonViewM1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //Call the view bookings parameters interface
            	viewBookingsParameters();
            }
        });
        //Create button to allow user to ammend bookings
        constraint2.gridy = 1;
        constraint2.gridx = 0;
        buttonResB1 = new JButton("Ammend Bookings");
        buttonResB1.setBounds(50,100,95,30);  
        frameViewM.add(buttonResB1, constraint2);
        buttonResB1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //Call reschedule bookings interface
            	rescheduleBookings();
            }
        });
        //Create button to allow user to change doctor
        constraint2.gridy = 1;
        constraint2.gridx = 2;
        buttonChangeD1 = new JButton("Change Doctor");
        buttonChangeD1.setBounds(50,100,95,30);  
        frameViewM.add(buttonChangeD1, constraint2);
        buttonChangeD1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //Call change doctor interface
            	frameViewM.setVisible(false);
            	changeDoctor();
            }
        });
        
      //Create button to allow user to view past visit details and prescriptions
        constraint2.gridy = 0;
        constraint2.gridx = 1;
        buttonViewDP = new JButton("View Past Visit Details");
        buttonViewDP.setBounds(50,100,95,30);  
        frameViewM.add(buttonViewDP, constraint2);
        buttonViewDP.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //Call arrange new booking interface
            	viewVisitPres();
            }
        });
        
        constraint2.gridx = 1;
        constraint2.gridy = 2;
        frameViewM.setSize(750, 600);
        labelViewM = new JLabel("View Messages");
        frameViewM.add(labelViewM, constraint2);
        constraint2.gridx = 1;
        constraint2.gridy = 3;
        
        //Create table to display the users current messages
        String columns[] = {"ID", "Message"};
        tableViewM = new JTable(dbm.getMessages(loggedUser), columns);
        tableViewM.setBounds(30,40,200,300);  
        tableViewM.getColumnModel().getColumn(0).setPreferredWidth(1);
        JScrollPane sc = new JScrollPane(tableViewM);
        frameViewM.add(sc, constraint2);
        
        //Create message for user logging
        frameViewM.setVisible(true);
        log.info(loggedUser + " has accessed View Messages at " + date);
        String msg = "View Messages";
      	dbm.accessLogs(loggedUser, msg);
    } 
    
    /**
     * This method will create a frame that will allow the user to enter parameters for 
     * searching their current bookings, within a span of a given month and year
     */
     private void viewBookingsParameters()
     {
         frameVBP = new JFrame("Patient Interface: View Bookings Parameters");
         frameVBP.setSize(400, 200);
         frameVBP.setLayout(new GridBagLayout());
         GridBagConstraints constraintVBP = new GridBagConstraints();

         labelVBP = new JLabel("Input Booking Parameters");
         constraintVBP.gridx = 0;
         constraintVBP.gridy = 0;
         frameVBP.add(labelVBP, constraintVBP);

         //Month text field
         labelVBP = new JLabel("Month: ");
         constraintVBP.gridx = 0;
         constraintVBP.gridy = 1;
         frameVBP.add(labelVBP, constraintVBP);
         
         monthFieldVBP = new JTextField(12);
         constraintVBP.gridx = 1;
         constraintVBP.gridy = 1;
         frameVBP.add(monthFieldVBP, constraintVBP);

         //Year text field
         labelVBP2 = new JLabel("Year: ");
         constraintVBP.gridx = 0;
         constraintVBP.gridy = 2;
         frameVBP.add(labelVBP2, constraintVBP);
         
         yearFieldVBP = new JTextField(12);
         constraintVBP.gridx = 1;
         constraintVBP.gridy = 2;
         frameVBP.add(yearFieldVBP, constraintVBP);

         //Button to submit the login details
         buttonVBP = new JButton("Enter");
         constraintVBP.gridx = 1;
         constraintVBP.gridy = 3;
         frameVBP.add(buttonVBP, constraintVBP);
         
         //Action listener to check for the users click
         buttonVBP.addActionListener(new ActionListener()
         {
        	 public void actionPerformed(ActionEvent event)
             {
        		 String selectedMonth = monthFieldVBP.getText();
        		 String selectedYear = yearFieldVBP.getText();
        		 
        		 Integer monthCheck = 0;
    			 Integer yearCheck = 0;
        		 
        		 try {
        			 monthCheck = Integer.parseInt(selectedMonth);
        			 yearCheck = Integer.parseInt(selectedYear);
        		 } catch (Exception E) {
         			JOptionPane.showMessageDialog(frameVBP, "Input was invalid, please only enter numbers");
        		 }
        		 
        		 if(monthCheck <= 12 && monthCheck >= 0 && yearCheck <= 2099 && yearCheck >= 2022) {
        			 frameVBP.setVisible(false);
        			 viewBookings(selectedMonth, selectedYear);
        		 } else {
        			 JOptionPane.showMessageDialog(frameVBP, "Input was invalid, please enter values that are within a correct range");
        		 }
             }
             
         });
         
         frameVBP.setVisible(true);	
     }
    
    /**
    * This method will create a frame to show the user their bookings by populating
    * the frame with all current bookings for the currently logged in patient that 
    * fall within the given month and year parameters
    */
    private void viewBookings(String month, String year) 
    {
    	frameShowB = new JFrame("Patient Interface: View Bookings");
        frameShowB.setLayout(new GridBagLayout());
        GridBagConstraints constraint3 = new GridBagConstraints();

        frameShowB.setSize(600, 600);
        labelShowB = new JLabel("All Matching Bookings");
        constraint3.gridx = 0;
        constraint3.gridy = 0;
        frameShowB.add(labelShowB, constraint3);

        //Create table to show the user their bookings from the DB
        String columns[] = {"Id","Room", "Day","Month", "Year"};
        tableShowB = new JTable(dbm.getBookings(loggedUser, month, year), columns);
        tableShowB.setBounds(30,40,200,300);  
        constraint3.gridx = 0;
        constraint3.gridy = 1;
        JScrollPane sc = new JScrollPane(tableShowB);
        frameShowB.add(sc, constraint3);
        
        //Create message to log the users action
        frameShowB.setVisible(true);
        log.info(dbm.getPID(loggedUser) + " has accessed View Bookings at " + date);
        String msg = "View Bookings";
      	dbm.accessLogs(loggedUser, msg);
    }
    
    /**
    * This method will create a frame to allow the user to arrange bookings by displaying a form
    * for the user to input information for that booking
    */
    private void arrangeNewBooking() 
    {
    	frameArrangeB = new JFrame("Book New Appointment");
        frameArrangeB.setSize(400, 200);
        frameArrangeB.setLayout(new GridBagLayout());
        GridBagConstraints constraint4 = new GridBagConstraints();

        //Title
        labelArrangeB = new JLabel("Booking Appointment");
        constraint4.gridx = 0;
        constraint4.gridy = 0;
        frameArrangeB.add(labelArrangeB, constraint4); 
        
        //Text fields
        constraint4.gridx = 1;
        constraint4.gridy = 1;
        locationField = new JTextField(12);
        locationField.setSize(100,20);
        frameArrangeB.add(locationField, constraint4);
        
        constraint4.gridy = 2;
        dayField = new JTextField(12);
        dayField.setSize(100,20);
        frameArrangeB.add(dayField, constraint4);
        
        constraint4.gridy = 3;
        monthField = new JTextField(12);
        monthField.setSize(100,20);
        frameArrangeB.add(monthField, constraint4);
        
        constraint4.gridy = 4;
        yearField = new JTextField(12);
        yearField.setSize(100,20);
        frameArrangeB.add(yearField, constraint4);
        
        constraint4.gridx = 0;
        constraint4.gridy = 1;
        labelArrangeB1 = new JLabel("Room");
        frameArrangeB.add(labelArrangeB1, constraint4);
        
        constraint4.gridy = 2;
        labelArrangeB2 = new JLabel("Day");
        frameArrangeB.add(labelArrangeB2, constraint4);
        
        constraint4.gridy = 3;
        labelArrangeB3 = new JLabel("Month");
        frameArrangeB.add(labelArrangeB3, constraint4);
        
        constraint4.gridy = 4;
        labelArrangeB4 = new JLabel("Year");
        frameArrangeB.add(labelArrangeB4, constraint4);
        
        //Button for user to confirm their data
        constraint4.gridx = 1;
        constraint4.gridy = 5;
        buttonArrangeB = new JButton("Confirm Booking");
        buttonArrangeB.setBounds(50,100,95,30);  
        frameArrangeB.add(buttonArrangeB, constraint4);
        
        //Action listener to input the data entered by the user into the database
        buttonArrangeB.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //Input users data using the DB manager class
            	String[] input = new String[4];

            	input[0] = locationField.getText();
            	input[1] = dayField.getText();
            	input[2] = monthField.getText();
            	input[3] = yearField.getText();
            	
            	Integer dayCheck = 0;
            	Integer monthCheck = 0;
            	Integer yearCheck = 0;
            	
            	try {
            		dayCheck = Integer.parseInt(dayField.getText());
                	monthCheck = Integer.parseInt(monthField.getText());
                	yearCheck = Integer.parseInt(yearField.getText());
       		 	} catch (Exception E) {
        			JOptionPane.showMessageDialog(frameVBP, "Input was invalid, please only enter numbers");
       		 	}
       		 	
       		 	if(dayCheck <= 31 && dayCheck >= 0 && monthCheck <= 12 && monthCheck >= 0 && yearCheck <= 2099 && yearCheck >= 2022) {
       		 		
       		 		if (dbm.isValidBookingData(input)) {    		
       		 			String[] dateInput = new String[3];
       		 			dateInput[0] = dayField.getText();
       		 			dateInput[1] = monthField.getText();
       		 			dateInput[2] = yearField.getText();
                	
       		 			if (dbm.isDoctorAvailable(dateInput, loggedUser)) {
       		 				dbm.arrangeBooking(input, loggedUser);
       		 				
       		 				String bookingDate = dbm.constructAppointmentDate(dateInput);
       		 				dbm.addMessage(3, loggedUser, bookingDate);
       		 				
       		 				frameViewM.setVisible(false);
       		 				frameArrangeB.setVisible(false);
       		 				//Show the users bookings
       		 				viewBookings(monthField.getText(), yearField.getText());
       		 				viewMessages();
                		
       		 			} else {
       		 				JOptionPane.showMessageDialog(frameChangeD, "Your Doctor is not available at your chosen date, please choose another");
       		 			}
       		 			
       		 		} else {
       		 			JOptionPane.showMessageDialog(frameChangeD, "The input date and room number given is not valid");
       		 			locationField.setText("");
       		 			dayField.setText("");
       		 			monthField.setText("");
       		 			yearField.setText("");
       		 		}
       		 			
       		 	} else {
       		 		JOptionPane.showMessageDialog(frameVBP, "Input was invalid, please enter values that are within a correct range");
       			}
       		 	
            }
        });
        
        //Create message to log users activity
        frameArrangeB.setVisible(true);
        log.info(loggedUser + " has accessed Arrange Booking at " + date);
        String msg = "Arrange Booking";
      	dbm.accessLogs(loggedUser, msg); 	
    }
    
    /**
    * This method will create a frame to allow the user to reschedule Bookings
    */
    private void rescheduleBookings() 
    {
    	frameResB = new JFrame("Patient Interface: Reschedule Bookings");
        frameResB.setLayout(new GridBagLayout());
        GridBagConstraints constraintRB = new GridBagConstraints();

        frameResB.setSize(600, 600);
        labelResB = new JLabel("Previous bookings: ");
        constraintRB.gridx = 0;
        constraintRB.gridy = 0;
        frameResB.add(labelResB, constraintRB);
          
        //Table
        String columns[] = {"BookingID", "Room", "Day", "Month", "Year"};
        tableResB = new JTable(dbm.getAllBookings(loggedUser), columns);
        tableResB.setBounds(30,40,200,300);  
        JScrollPane scRB = new JScrollPane(tableResB);
        constraintRB.gridx = 0;
        constraintRB.gridy = 1;
        frameResB.add(scRB, constraintRB);
          
        frameResB.setVisible(true);
          
        //Label, input field and button
        labelResB = new JLabel("Insert BookingID: ");
        constraintRB.gridx = 0;
        constraintRB.gridy = 3;
        frameResB.add(labelResB, constraintRB);
          
        textResB = new JTextField(5);
        textResB.setSize(100,20);
        constraintRB.gridx = 0;
        constraintRB.gridy = 4;
        frameResB.add(textResB, constraintRB);
          
        //Button to submit the users changed information
        buttonResB2 = new JButton("Submit");
        buttonResB2.setBounds(50,100,95,30);
        constraintRB.gridx = 0;
        constraintRB.gridy = 5;
        frameResB.add(buttonResB2, constraintRB);
        buttonResB2.addActionListener(new ActionListener() 
        {
        	@Override
            public void actionPerformed(ActionEvent event)
            {
        		String input = textResB.getText();
                //Validate the booking information
                if (dbm.isValidBookingID(loggedUser,input)) 
                {
                	frameViewM.setVisible(false);
                	frameResB.setVisible(false);
                	amendBooking(input);
                }
                else 
                {
                    //Show popup to inform user of incorrect booking
                	JOptionPane.showMessageDialog(frameResB, "Not a Valid Booking ID for This User");
                    textResB.setText("");
                }
              }
          });
        
        //Create message to log users activity
        log.info(loggedUser + " has accessed Reschedule Booking at " + date);
        String msg = "Reschedule Booking";
      	dbm.accessLogs(loggedUser, msg);     
      }
      
      /**
      * This method will create a frame to allow the user to ammend their bookings
      */
      private void amendBooking(String bookingID) 
      {
    	  frameAmendB = new JFrame("Patient Interface: Amend Booking");
          frameAmendB.setLayout(new GridBagLayout());
          GridBagConstraints constraintRB1 = new GridBagConstraints();

          //Setup user inputs
          frameAmendB.setSize(600, 600);
          labelAmendB1 = new JLabel("Day: ");
          constraintRB1.gridx = 0;
          constraintRB1.gridy = 1;
          frameAmendB.add(labelAmendB1, constraintRB1);
          
          textAmendB1 = new JTextField(12);
          textAmendB1.setSize(100,20);
          constraintRB1.gridx = 1;
          constraintRB1.gridy = 1;
          frameAmendB.add(textAmendB1, constraintRB1);
          
          constraintRB1.gridx = 0;
          constraintRB1.gridy = 2;
          frameAmendB.setSize(600, 600);
          labelAmendB2 = new JLabel("Month: ");
          frameAmendB.add(labelAmendB2, constraintRB1);

          constraintRB1.gridx = 1;
          constraintRB1.gridy = 2;
          textAmendB2 = new JTextField(12);
          textAmendB2.setSize(100,20);
          frameAmendB.add(textAmendB2, constraintRB1);
          
          constraintRB1.gridx = 0;
          constraintRB1.gridy = 3;
          frameAmendB.setSize(600, 600);
          labelAmendB3 = new JLabel("Year: ");
          frameAmendB.add(labelAmendB3, constraintRB1);

          constraintRB1.gridx = 1;
          constraintRB1.gridy = 3;
          textAmendB3 = new JTextField(12);
          textAmendB3.setSize(100,20);
          frameAmendB.add(textAmendB3, constraintRB1);
          
          //Button to allow the user to submit their changes to the DB
          constraintRB1.gridx = 1;
          constraintRB1.gridy = 4;
          buttonAmendB = new JButton("Submit");
          buttonAmendB.setBounds(50,100,95,30);  
          frameAmendB.add(buttonAmendB, constraintRB1);
          buttonAmendB.addActionListener(new ActionListener() 
          {
              @Override
              public void actionPerformed(ActionEvent event)
              {
                  //Submit booking using DB method
                  String[]input = new String[3];
                 
                  input[0] = textAmendB1.getText();
                  input[1] = textAmendB2.getText();
                  input[2] = textAmendB3.getText();
                  
                  //Following checks validity of booking data in a couple ways, and if the doctor would be free to attend  
                  if (dbm.isValidBookingData(input)) {
                	  
                      if (dbm.isDoctorAvailable(input, loggedUser)) {
                    	  
                    	  Integer dayCheck = Integer.parseInt(input[0]);
                    	  Integer monthCheck = Integer.parseInt(input[1]);
                    	  Integer yearCheck = Integer.parseInt(input[2]);
                    	  
                    	  if(dayCheck <= 31 && dayCheck >= 0 && monthCheck <= 12 && monthCheck >= 0 && yearCheck <= 2099 && yearCheck >= 2021) {
                    	  
                    		  dbm.submittedBooking(input, bookingID);
                    		  
                    		  String bookingDate = dbm.constructAppointmentDate(input);
                    		  dbm.addMessage(4, loggedUser, bookingDate);
                 	      
                    		  frameAmendB.setVisible(false);
                    		  viewBookings(textAmendB2.getText(), textAmendB3.getText());  
                    		  viewMessages();
                    		  
                    	  } else {
                    		  JOptionPane.showMessageDialog(frameVBP, "Input was invalid, please enter values that are within a correct range");
                    	  }
                  		
              		} else {
              			JOptionPane.showMessageDialog(frameChangeD, "Your Doctor is not available at your chosen date, please choose another");
              		} 
                  }
                  else {
                	  JOptionPane.showMessageDialog(frameChangeD, "The input date given is not valid");
                      textAmendB1.setText("");
                      textAmendB2.setText("");
                      textAmendB3.setText("");
                  }
              }
          });
          
          //Log users access
          frameAmendB.setVisible(true);
          log.info(loggedUser + " has accessed Amend Booking at " + date);
          String msg = "Amend Booking";
          dbm.accessLogs(loggedUser, msg);     
      }
      
      /**
      * This method will create a frame to allow the user to change their doctor to whatever
      * Doctor ID they select from a displayed table of all doctors and IDs
      */
      private void changeDoctor()
      {
    	  frameChangeD = new JFrame("Patient Interface: Change Doctor");
      	  frameChangeD.setLayout(new GridBagLayout());
      	  GridBagConstraints constraint4 = new GridBagConstraints();
      	
          //Table to display current doctors and their IDs
      	  frameChangeD.setSize(600, 600);
      	  labelChangeD = new JLabel("Available Doctors List");
          constraint4.gridx = 0;
      	  constraint4.gridy = 0;
      	  frameChangeD.add(labelChangeD, constraint4);
      	
      	  String columns[] = {"doctorID", "Last Name", "Speciality"};
      	  tableChangeD = new JTable(dbm.getDoctors(loggedUser), columns);
      	  tableChangeD.setBounds(30,40,200,300);
      	  constraint4.gridx = 0;
    	  constraint4.gridy = 1;
      	  JScrollPane sc = new JScrollPane(tableChangeD);
      	  frameChangeD.add(sc, constraint4);
      	
      	  //Button to submit change
      	  constraint4.gridy = 5;
      	  buttonChangeD2 = new JButton("Change Doctor");
          buttonChangeD2.setBounds(50,100,95,30);  
          constraint4.gridx = 0;
      	  constraint4.gridy = 4;
          frameChangeD.add(buttonChangeD2, constraint4);
          
          textChangeD = new JTextField(5);
          textChangeD.setSize(100,20);
          constraint4.gridx = 0;
          constraint4.gridy = 3;
          frameChangeD.add(textChangeD, constraint4);
          buttonChangeD2.addActionListener(new ActionListener()
          {
        	  @Override
              public void actionPerformed(ActionEvent event) 
        	  {
                  //Change doctor using the DB manager method
        		  String doctorID = textChangeD.getText();
        		  if (dbm.isValidDoctorID(doctorID)) {
        			  
        			  dbm.changeCurrentDoctor(doctorID, loggedUser);
        			  String doctorLastName = dbm.getDoctorLastName(doctorID);
        			  dbm.addMessage(2, loggedUser, doctorLastName);
        			  //Take user back to view messages frame
        			  frameChangeD.setVisible(false);
        			  viewMessages();
        			  
        		  } else {
        			  JOptionPane.showMessageDialog(frameChangeD, "Not a Valid Doctor ID");
                      textChangeD.setText("");
        		  }
              }
         });
          
         //Log users access
         frameChangeD.setVisible(true);
         log.info(loggedUser + " has accessed Change Doctor at " + date);
       	 String msg = "Change Doctor";
         dbm.accessLogs(loggedUser, msg);
      }
      
      /**
      * This method will create a frame to allow the user to view their details and prescriptions
      */
      private void viewVisitPres() 
      {
      	  frameVP = new JFrame("Patient Interface: View Visit Details and Prescriptions");
      	  frameVP.setLayout(new GridBagLayout());
      	  GridBagConstraints constraintVP = new GridBagConstraints();

          frameVP.setSize(600, 600);
          labelVP = new JLabel("Visit Details");
          constraintVP.gridx = 0;
          constraintVP.gridy = 0;
          frameVP.add(labelVP, constraintVP);
          
          //Create table of the users details and prescriptions
          String columns[] = {"DoctorID", "BookingID", "Visit Details", "Prescriptions"};
          tableVP = new JTable(dbm.getVisitDetails(loggedUser), columns);
          tableVP.setBounds(30,40,200,300);  
          constraintVP.gridx = 0;
          constraintVP.gridy = 1;
          JScrollPane scVP = new JScrollPane(tableVP);
          frameVP.add(scVP, constraintVP);
          frameVP.setVisible(true);
          
          //Log users access
          log.info(loggedUser + " has accessed View Visit Details and Prescriptions at " + date);
          String msg = "View Visit Details and Prescriptions";
          dbm.accessLogs(loggedUser, msg);
      }
}
