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
    //Variables for the first JFrame
    private JFrame frame;
    private JLabel label;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton button;
    //Variables for the second JFrame
    private JFrame frame2;
    private JLabel label2;
    private JTable table;
    private JButton button2;
    
    private JFrame frame3;
    private JLabel label3;
    private JTable table2;
    
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
    
    public DBManager dbm = new DBManager();	
    public String loggedUser;

    //Main
    public static void main(String[] args)
    {
    	
        //Create the patient interface
        PatientInterface pi = new PatientInterface();
        pi.setupInterface();
        
    }
    //Method to create the starting jframe
    private void setupInterface()
    {
        frame = new JFrame("Patient Interface");
        frame.setSize(400, 200);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;

        label = new JLabel("Login");
        frame.add(label, constraint);
        constraint.gridx = 0;
        constraint.gridy = 1;

        //Username text field
        label = new JLabel("Username: ");
        frame.add(label, constraint);
        constraint.gridx = 1;
        usernameField = new JTextField(12);
        frame.add(usernameField, constraint);
        constraint.gridx = 0;
        constraint.gridy = 2;

        //Password password field (in order to hide password when its entered)
        label = new JLabel("Password: ");
        frame.add(label, constraint);
        constraint.gridx = 1;
        passwordField = new JPasswordField(12);
        frame.add(passwordField, constraint);
        constraint.gridx = 1;
        constraint.gridy = 3;

        //Button to submit the login details
        button = new JButton("Enter");
        frame.add(button, constraint);
        button.addActionListener(new ActionListener()
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
                    frame.setVisible(false);
                    ViewMessages();
                }
                else
                {
                    //Error message
                    JOptionPane.showMessageDialog(frame, "Incorrect Username or Password");
                    //Set password to blank
                    passwordField.setText("");
                }
            }
        });
        frame.setVisible(true);
    }
    //Method to create the viewmessages jframe
    private void ViewMessages()
    {
        frame2 = new JFrame("Patient Interface: View Messages");
        frame2.setLayout(new GridBagLayout());
        GridBagConstraints constraint2 = new GridBagConstraints();
        constraint2.gridx = 0;
        constraint2.gridy = 0;

        //Button to open the View Bookings Window
        button2 = new JButton("View Bookings");
        button2.setBounds(50,100,95,30);  
        frame2.add(button2, constraint2);

        button2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	viewBookings();
            }
        });
        
        //constraint2.gridx = 2;
        //constraint2.gridy = 0;
        buttonAB = new JButton("Ammend Bookings");
        buttonAB.setBounds(50,100,95,30);  
        frame2.add(buttonAB, constraint2);
        
        buttonAB.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	rescheduleBookings();
            }
        });
        
        constraint2.gridx = 1;
        constraint2.gridy = 2;

        frame2.setSize(600, 600);
        label2 = new JLabel("View Messages");
        frame2.add(label2, constraint2);
        constraint2.gridx = 0;
        constraint2.gridy = 3;

        String columns[] = {"ID", "Message"};
        table = new JTable(dbm.getMessages(loggedUser), columns);
        table.setBounds(30,40,200,300);  
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        JScrollPane sc = new JScrollPane(table);
        frame2.add(sc, constraint2);
        
        frame2.setVisible(true);
    } 
    
    //Brings up the viewBookings JFrame and populates it with all current bookings for the logged in patient
    private void viewBookings() 
    {
    	frame3 = new JFrame("Patient Interface: View Bookings");
        frame3.setLayout(new GridBagLayout());
        GridBagConstraints constraint3 = new GridBagConstraints();
        constraint3.gridx = 0;
        constraint3.gridy = 0;

        frame3.setSize(600, 600);
        label3 = new JLabel("All Current Bookings");
        frame3.add(label3, constraint3);
        constraint3.gridx = 0;
        constraint3.gridy = 2;

        String columns[] = {"BookingID", "Room", "Day","Month", "Year"};
        table = new JTable(dbm.getBookings(loggedUser), columns);
        table.setBounds(30,40,200,300);  
        JScrollPane sc = new JScrollPane(table);
        frame3.add(sc, constraint3);
        
        frame3.setVisible(true);
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
        table.setBounds(30,40,200,300);  
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

    
}






