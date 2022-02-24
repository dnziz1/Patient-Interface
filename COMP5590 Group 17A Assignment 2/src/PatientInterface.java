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
                //Proxys for sql get commands
                //Change here to add the sql commands to get the username and password
                String SqlUsername;
                String SqlPassword;
                //If to check if a username matches the database and password also matches
                //Change here to match to the above variables
                if(Username.equals("John") && Password.equals("Password"))
                {
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

        frame2.setSize(600, 600);
        label2 = new JLabel("View Messages");
        frame2.add(label2, constraint2);
        constraint2.gridx = 0;
        constraint2.gridy = 1;
        frame2.setVisible(true);
    }
}