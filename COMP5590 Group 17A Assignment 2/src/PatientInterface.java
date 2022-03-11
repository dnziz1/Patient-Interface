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
    
    //variables for reschedule bookings JFrame
    private JButton buttonAB;
    private JFrame frameRB;
    private JLabel labelRB;
    private JTable tableRB;
    private JTextField textRB;
    private JButton buttonRB;
    
    private JFrame frameRB1;
    private JLabel labelRB1;
    private JLabel labelRB2;
    private JLabel labelRB3;
    private JTable tableRB1;
    private JTextField textRB1;
    private JTextField textRB2;
    private JTextField textRB3;
    private JButton buttonRB1;
    
    //variables for Change Doctor JFrame
    
    private JButton buttonCD;
    private JFrame frame4;
    private JLabel label4;
    private JTable table3;
    private JTextField text3;
    private JButton button3;
    
    //Link to DBManager
    public DBManager dbm = new DBManager();	
    public String loggedUser;

    //Main
    public static void main(String[] args)
    {
    	
        //Create the patient interface
        PatientInterface pi = new PatientInterface();
        pi.setupInterface();
        
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
        buttonAB = new JButton("Ammend Bookings");
        buttonAB.setBounds(50,100,95,30);  
        frameViewM.add(buttonAB, constraint2);
        
        buttonAB.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	rescheduleBookings();
            }
        });
        
        constraint2.gridy = 1;
        constraint2.gridx = 1;
        buttonCD = new JButton("Change Doctor");
        buttonCD.setBounds(50,100,95,30);  
        frameViewM.add(buttonCD, constraint2);
        
        buttonCD.addActionListener(new ActionListener()
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
    
    private void rescheduleBookings() {
        frameRB = new JFrame("Patient Interface: Reschedule Bookings");
        frameRB.setLayout(new GridBagLayout());
        GridBagConstraints constraintRB = new GridBagConstraints();
          constraintRB.gridx = 0;
          constraintRB.gridy = 0;

          frameRB.setSize(600, 600);
          labelRB = new JLabel("Previous bookings: ");
          frameRB.add(labelRB, constraintRB);
          constraintRB.gridx = 0;
          constraintRB.gridy = 2;
          
          //table
          String columns[] = {"BookingID", "Room", "Day", "Month", "Year"};
          tableRB = new JTable(dbm.getBookings(loggedUser), columns);
          tableRB.setBounds(30,40,200,300);  
          JScrollPane scRB = new JScrollPane(tableRB);
          frameRB.add(scRB, constraintRB);
          
          frameRB.setVisible(true);
          
          //label, input field and button
          labelRB = new JLabel("Insert BookingID: ");
          constraintRB.gridx = 0;
          constraintRB.gridy = 3;
          frameRB.add(labelRB, constraintRB);
          
          textRB = new JTextField(5);
          textRB.setSize(100,20);
          constraintRB.gridx = 0;
          constraintRB.gridy = 4;
          frameRB.add(textRB, constraintRB);
          
          buttonRB = new JButton("Submit");
          buttonRB.setBounds(50,100,95,30);
          constraintRB.gridx = 0;
          constraintRB.gridy = 5;
          frameRB.add(buttonRB, constraintRB);

          buttonRB.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event)
              {
                 String input = textRB.getText();
                 
                 if (dbm.isValidBookingID(loggedUser,input)) {
                    amendBooking(input);
                 } else {
                    JOptionPane.showMessageDialog(frameRB, "Not a Valid Booking ID for This User");
                      textRB.setText("");
                 }
                 
              }
          });
        
        
      }
      
      private void amendBooking(String bookingID) {
        frameRB1 = new JFrame("Patient Interface: Amend Booking");
        frameRB1.setLayout(new GridBagLayout());
        GridBagConstraints constraintRB1 = new GridBagConstraints();
          constraintRB1.gridx = 0;
          constraintRB1.gridy = 1;

          frameRB1.setSize(600, 600);
          labelRB1 = new JLabel("Day: ");
          frameRB1.add(labelRB1, constraintRB1);
          constraintRB1.gridx = 1;
          constraintRB1.gridy = 1;
          
          textRB1 = new JTextField(12);
          textRB1.setSize(100,20);
          frameRB1.add(textRB1, constraintRB1);
          
          constraintRB1.gridx = 0;
          constraintRB1.gridy = 2;
          frameRB1.setSize(600, 600);
          labelRB2 = new JLabel("Month: ");
          frameRB1.add(labelRB2, constraintRB1);
          constraintRB1.gridx = 1;
          constraintRB1.gridy = 2;
          
          textRB2 = new JTextField(12);
          textRB2.setSize(100,20);
          frameRB1.add(textRB2, constraintRB1);
          
          constraintRB1.gridx = 0;
          constraintRB1.gridy = 3;
          frameRB1.setSize(600, 600);
          labelRB3 = new JLabel("Year: ");
          frameRB1.add(labelRB3, constraintRB1);
          constraintRB1.gridx = 1;
          constraintRB1.gridy = 3;
          
          textRB3 = new JTextField(12);
          textRB3.setSize(100,20);
          frameRB1.add(textRB3, constraintRB1);
          
          constraintRB1.gridx = 1;
          constraintRB1.gridy = 4;
          buttonRB1 = new JButton("Submit");
          buttonRB1.setBounds(50,100,95,30);  
          frameRB1.add(buttonRB1, constraintRB1);

          buttonRB1.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event)
              {
                 String[]input = new String[3];
                 
                 input[0] = textRB1.getText();
                 input[1] = textRB2.getText();
                 input[2] = textRB3.getText();
                    
                 dbm.submittedBooking(input, bookingID);
                 
                 viewBookings();
                 
              }
          });
          
          frameRB1.setVisible(true);
          
      }
      
      private void changeDoctor()
      {
      	frame4 = new JFrame("Patient Interface: Change Doctor");
      	frame4.setLayout(new GridBagLayout());
      	GridBagConstraints constraint4 = new GridBagConstraints();
      	constraint4.gridx = 0;
      	constraint4.gridy = 0;
      	
      	frame4.setSize(600, 600);
      	label4 = new JLabel("Available Doctors List");
      	frame4.add(label4, constraint4);
      	constraint4.gridx = 0;
      	constraint4.gridy = 3;
      	
      	String columns[] = {"doctorID", "Last Name", "Speciality"};
      	table3 = new JTable(dbm.getDoctors(loggedUser), columns);
      	table3.setBounds(30,40,200,300);
      	JScrollPane sc = new JScrollPane(table3);
      	frame4.add(sc, constraint4);
      	
      	
      	//Button to submit change
      	constraint4.gridy = 5;
      	button3 = new JButton("Change Doctor");
        button3.setBounds(50,100,95,30);  
        frame4.add(button3, constraint4);
          
        text3 = new JTextField(5);
        text3.setSize(100,20);
        constraint4.gridx = 0;
        constraint4.gridy = 4;
        frame4.add(text3, constraint4);

          
        button3.addActionListener(new ActionListener()
        {
         	@Override
          public void actionPerformed(ActionEvent event) 
          {
          	String doctorID = text3.getText();
          	dbm.changeCurrentDoctor(doctorID, loggedUser);
          	JOptionPane.showMessageDialog(frame4, "Doctor has Been Changed to Doctor ID: " + doctorID + " confirmation message will be received shortly.");
          	dbm.addMessage(2, loggedUser);
          }
        });
      	
      	frame4.setVisible(true);
      	
      }
      
}
