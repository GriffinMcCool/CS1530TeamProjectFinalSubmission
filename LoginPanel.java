import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField; // Field for user to enter username
    private JPasswordField passwordField; // Field for user to enter password
    private JLabel messageLabel; // Label to display messages
    private JButton loginButton; // Button to initiate login process
    private JButton createAccountButton; // Button to initiate account creation process 

    // Provides the GUI for the login panel
    public LoginPanel(ActionListener loginAction, ActionListener createAccountAction) {
        setLayout(new FlowLayout());
        add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(loginAction);
        add(loginButton);

        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(createAccountAction);
        add(createAccountButton);

        messageLabel = new JLabel(" ");
        add(messageLabel);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public JLabel getMessage(){
        return messageLabel;
    }

    public void setUsername(String username){
        usernameField = new JTextField(username);
    }

    public void setPassword(String password){
        passwordField = new JPasswordField(password);
    }
}
