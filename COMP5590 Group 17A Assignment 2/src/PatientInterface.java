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
    private JButton button4;
    
    private JFrame frame3;
    private JLabel label3;
    private JTable table2;
    
    private JFrame frame4;
    private JLabel label4;
    private JButton button3;
    private JTextField locationField;
    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;
    
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
        
        button4 = new JButton("Make New Bookings");
        button4.setBounds(50,100,95,30);  
        frame2.add(button4, constraint2);

        button4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	arrangeNewBooking();
            }
        });
        
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
        
        constraint2.gridx = 0;
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

        String columns[] = {"Location", "Day","Month", "Year"};
        table = new JTable(dbm.getBookings(loggedUser), columns);
        table.setBounds(30,40,200,300);  
        JScrollPane sc = new JScrollPane(table);
        frame3.add(sc, constraint3);
        
        frame3.setVisible(true);
    }
    
    private void arrangeNewBooking() 
    {
    	frame4 = new JFrame("Book New Appointment");
        frame4.setSize(400, 200);
        frame4.setLayout(new GridBagLayout());
        GridBagConstraints constraint4 = new GridBagConstraints();
        constraint4.gridx = 0;
        constraint4.gridy = 0;

        label4 = new JLabel("Booking Appointment");
        frame4.add(label, constraint4);
        constraint4.gridx = 0;
        constraint4.gridy = 1;
        
    	constraint4.gridx = 1;
        locationField = new JTextField(12);
        frame4.add(locationField, constraint4);
        
        constraint4.gridx = 2;
        dayField = new JTextField(12);
        frame4.add(dayField, constraint4);
        
        constraint4.gridx = 3;
        monthField = new JTextField(12);
        frame4.add(monthField, constraint4);
        
        constraint4.gridx = 4;
        yearField = new JTextField(12);
        frame4.add(yearField, constraint4);
        
        constraint4.gridx = 1;
        constraint4.gridy = 2;
        button3 = new JButton("Confirm Booking");
        button3.setBounds(50,100,95,30);  
        frame4.add(button3, constraint4);
        
        button3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                
            	String[] input = new String[5];
            	input[0] = loggedUser;
            	
            	input[1] = locationField.getText();
            	input[2] = dayField.getText();
            	input[3] = monthField.getText();
            	input[4] = yearField.getText();
            	
            	dbm.arrangeBooking(input);
            	frame4.setVisible(false);
            	viewBookings();
            }
        });
        
        frame4.setVisible(true);
    }
    
    
}






