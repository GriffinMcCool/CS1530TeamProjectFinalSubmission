import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Provides the GUI for the welcome panel
public class WelcomePanel extends JPanel {
    private JLabel welcomeLabel; // Label to display the welcome message

    public WelcomePanel(ActionListener logoutAction,ActionListener challenge) {
        setLayout(new FlowLayout());
        welcomeLabel = new JLabel("Welcome, user!");
        add(welcomeLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(logoutAction);
        add(logoutButton);

        JButton button=new JButton("Create Challenge"); 
        button.addActionListener(challenge);
        add(button);
    }


    public void setWelcomeMessage(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }
}


