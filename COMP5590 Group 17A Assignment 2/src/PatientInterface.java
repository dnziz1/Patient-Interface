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
    
    
}
