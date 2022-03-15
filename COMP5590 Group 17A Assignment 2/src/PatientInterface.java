//UI imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//DB imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
    
    //Link to DBManager
    public DBManager dbm = new DBManager();	
    public String loggedUser;

    //Main
    public static void main(String[] args)
    {
    	
        //Create the patient interface
        PatientInterface pi = new PatientInterface();
        pi.loginRegisterInterface();
        
    }
    
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
            	frameLogReg.setVisible(false);
                setupInterface();
            }
        });
        frameLogReg.setVisible(true);
    }
    
    //Method to create the starting login jframe
    private void setupInterface()
    {
        frameMain = new JFrame("Patient Interface");
        frameMain.setSize(400, 200);
        frameMain.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;

        labelMain = new JLabel("Login");
        frameMain.add(labelMain, constraint);
        constraint.gridx = 0;
        constraint.gridy = 1;

        //Username text field
        labelMain = new JLabel("Username: ");
        frameMain.add(labelMain, constraint);
        constraint.gridx = 1;
        usernameField = new JTextField(12);
        frameMain.add(usernameField, constraint);
        constraint.gridx = 0;
        constraint.gridy = 2;

        //Password password field (in order to hide password when its entered)
        labelMain = new JLabel("Password: ");
        frameMain.add(labelMain, constraint);
        constraint.gridx = 1;
        passwordField = new JPasswordField(12);
        frameMain.add(passwordField, constraint);
        constraint.gridx = 1;
        constraint.gridy = 3;

        //Button to submit the login details
        buttonMain = new JButton("Enter");
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
                    frameMain.setVisible(false);
                    ViewMessages();
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
    //Method to create the viewmessages jframe
    private void ViewMessages()
    {
        frameViewM = new JFrame("Patient Interface: View Messages");
        frameViewM.setLayout(new GridBagLayout());
        GridBagConstraints constraint2 = new GridBagConstraints();
        constraint2.gridx = 0;
        constraint2.gridy = 0;
        
        buttonViewM2 = new JButton("Make New Bookings");
        buttonViewM2.setBounds(50,100,95,30);  
        frameViewM.add(buttonViewM2, constraint2);

        buttonViewM2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	arrangeNewBooking();
            }
        });
        
        //Button to open the View Bookings Window
        constraint2.gridx = 1;
        buttonViewM1 = new JButton("View Bookings");
        buttonViewM1.setBounds(50,100,95,30);  
        frameViewM.add(buttonViewM1, constraint2);
        //Action listener to populate the table once the user presses view bookings
        buttonViewM1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	viewBookings();
            }
        });
        
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
            	rescheduleBookings();
            }
        });
        
        constraint2.gridy = 1;
        constraint2.gridx = 1;
        buttonChangeD1 = new JButton("Change Doctor");
        buttonChangeD1.setBounds(50,100,95,30);  
        frameViewM.add(buttonChangeD1, constraint2);
        
        buttonChangeD1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	changeDoctor();
            }
        });
        
        constraint2.gridx = 0;
        constraint2.gridy = 2;

        frameViewM.setSize(600, 600);
        labelViewM = new JLabel("View Messages");
        frameViewM.add(labelViewM, constraint2);
        constraint2.gridx = 0;
        constraint2.gridy = 3;

        String columns[] = {"ID", "Message"};
        tableViewM = new JTable(dbm.getMessages(loggedUser), columns);
        tableViewM.setBounds(30,40,200,300);  
        tableViewM.getColumnModel().getColumn(0).setPreferredWidth(1);
        JScrollPane sc = new JScrollPane(tableViewM);
        frameViewM.add(sc, constraint2);
        
        frameViewM.setVisible(true);
    } 
    
    //Method to create the viewbookings jframe
    //Populates the frame with all current bookings for the logged in patient
    private void viewBookings() 
    {
    	frameShowB = new JFrame("Patient Interface: View Bookings");
        frameShowB.setLayout(new GridBagLayout());
        GridBagConstraints constraint3 = new GridBagConstraints();
        constraint3.gridx = 0;
        constraint3.gridy = 0;

        frameShowB.setSize(600, 600);
        labelShowB = new JLabel("All Current Bookings");
        frameShowB.add(labelShowB, constraint3);
        constraint3.gridx = 0;
        constraint3.gridy = 2;

        String columns[] = {"Id","Room", "Day","Month", "Year"};
        tableViewM = new JTable(dbm.getBookings(loggedUser), columns);
        tableViewM.setBounds(30,40,200,300);  
        JScrollPane sc = new JScrollPane(tableViewM);
        frameShowB.add(sc, constraint3);
        
        frameShowB.setVisible(true);
    }
    
    //Method to create the arrangenewbookings jframe
    //Displays a form to input new booking information
    private void arrangeNewBooking() 
    {
    	frameArrangeB = new JFrame("Book New Appointment");
        frameArrangeB.setSize(400, 200);
        frameArrangeB.setLayout(new GridBagLayout());
        GridBagConstraints constraint4 = new GridBagConstraints();
        constraint4.gridx = 0;
        constraint4.gridy = 0;

        //Title
        labelArrangeB = new JLabel("Booking Appointment");
        frameArrangeB.add(labelArrangeB, constraint4);
        constraint4.gridx = 1;
        
        //Text fields
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
                
            	String[] input = new String[4];

            	input[0] = locationField.getText();
            	input[1] = dayField.getText();
            	input[2] = monthField.getText();
            	input[3] = yearField.getText();
            	
            	dbm.arrangeBooking(input, loggedUser);
            	frameArrangeB.setVisible(false);
            	viewBookings();
            
            }
        });
        
        frameArrangeB.setVisible(true);
    }
    
    //Method to create the reschedulebookings jframe
    private void rescheduleBookings() 
    {
    	frameResB = new JFrame("Patient Interface: Reschedule Bookings");
        frameResB.setLayout(new GridBagLayout());
        GridBagConstraints constraintRB = new GridBagConstraints();
        constraintRB.gridx = 0;
        constraintRB.gridy = 0;

        frameResB.setSize(600, 600);
        labelResB = new JLabel("Previous bookings: ");
        frameResB.add(labelResB, constraintRB);
        constraintRB.gridx = 0;
        constraintRB.gridy = 2;
          
        //Table
        String columns[] = {"BookingID", "Room", "Day", "Month", "Year"};
        tableResB = new JTable(dbm.getBookings(loggedUser), columns);
        tableResB.setBounds(30,40,200,300);  
        JScrollPane scRB = new JScrollPane(tableResB);
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
                 
                if (dbm.isValidBookingID(loggedUser,input)) 
                {
                	amendBooking(input);
                }
                else 
                {
                	JOptionPane.showMessageDialog(frameResB, "Not a Valid Booking ID for This User");
                    textResB.setText("");
                }
                 
              }
          });
        
        
      }
      
      //Method to create the amendbookings jframe
      private void amendBooking(String bookingID) 
      {
    	  frameAmendB = new JFrame("Patient Interface: Amend Booking");
          frameAmendB.setLayout(new GridBagLayout());
          GridBagConstraints constraintRB1 = new GridBagConstraints();
          constraintRB1.gridx = 0;
          constraintRB1.gridy = 1;

          frameAmendB.setSize(600, 600);
          labelAmendB1 = new JLabel("Day: ");
          frameAmendB.add(labelAmendB1, constraintRB1);
          constraintRB1.gridx = 1;
          constraintRB1.gridy = 1;
          
          textAmendB1 = new JTextField(12);
          textAmendB1.setSize(100,20);
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
                 String[]input = new String[3];
                 
                 input[0] = textAmendB1.getText();
                 input[1] = textAmendB2.getText();
                 input[2] = textAmendB3.getText();
                    
                 dbm.submittedBooking(input, bookingID);
                 
                 viewBookings();  
              }
          });
          
          frameAmendB.setVisible(true);
          
      }
      
      //Method to create the changedoctor jframe
      private void changeDoctor()
      {
    	  frameChangeD = new JFrame("Patient Interface: Change Doctor");
      	  frameChangeD.setLayout(new GridBagLayout());
      	  GridBagConstraints constraint4 = new GridBagConstraints();
      	  constraint4.gridx = 0;
      	  constraint4.gridy = 0;
      	
      	  frameChangeD.setSize(600, 600);
      	  labelChangeD = new JLabel("Available Doctors List");
      	  frameChangeD.add(labelChangeD, constraint4);
      	  constraint4.gridx = 0;
      	  constraint4.gridy = 3;
      	
      	  String columns[] = {"doctorID", "Last Name", "Speciality"};
      	  tableChangeD = new JTable(dbm.getDoctors(loggedUser), columns);
      	  tableChangeD.setBounds(30,40,200,300);
      	  JScrollPane sc = new JScrollPane(tableChangeD);
      	  frameChangeD.add(sc, constraint4);
      	
      	  //Button to submit change
      	  constraint4.gridy = 5;
      	  buttonChangeD2 = new JButton("Change Doctor");
          buttonChangeD2.setBounds(50,100,95,30);  
          frameChangeD.add(buttonChangeD2, constraint4);
          
          textChangeD = new JTextField(5);
          textChangeD.setSize(100,20);
          constraint4.gridx = 0;
          constraint4.gridy = 4;
          frameChangeD.add(textChangeD, constraint4);

          buttonChangeD2.addActionListener(new ActionListener()
          {
        	  @Override
              public void actionPerformed(ActionEvent event) 
        	  {
        		  String doctorID = textChangeD.getText();
          	      dbm.changeCurrentDoctor(doctorID, loggedUser);
          	      JOptionPane.showMessageDialog(frameChangeD, "Doctor has Been Changed to Doctor ID: " + doctorID + " confirmation message will be received shortly.");
          	      dbm.addMessage(2, loggedUser);
              }
         });
      	 frameChangeD.setVisible(true);
      	
      }
      
}
