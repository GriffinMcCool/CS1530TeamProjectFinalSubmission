import javax.swing.*;
import java.awt.*;

// Main application class that sets up the JFrame and integrates all panels
public class CodingGameGUI extends JFrame {
    private CardLayout cardLayout; // Layout to allow switching between panels
    private JPanel cardPanel; // Panel that holds the cards (login and welcome)
    private LoginPanel loginPanel;
    private WelcomePanel welcomePanel;
    private AccountManager accountManager = new AccountManager();

    // Constructor to set up the GUI components
    public CodingGameGUI() {
        super("Login System");
        initializeUI();
    }

    // Initializes user interface including frame properties and adding panels
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(250, 200)); // Gives a preferred size for the JFrame
        pack(); // Sizes the frame so all contents are at or above their preferred sizes
        setLocationRelativeTo(null); // Centering the window on the screen

        JLabel appNameLabel = new JLabel("Coding Game", JLabel.CENTER);
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(e -> login(), e -> createAccount());
        welcomePanel = new WelcomePanel(e -> logout());

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(welcomePanel, "Welcome");

        setLayout(new BorderLayout());
        add(appNameLabel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        setResizable(false);
        setVisible(true);
    }

    // Attempts to log the user in using the credentials provided
    private void login() {
        String username = loginPanel.getUsername();
        String password = loginPanel.getPassword();

        if (accountManager.validateLogin(username, password)) { // If username/password is in hashmap of accounts
            loginPanel.setMessage("Login successful.");
            welcomePanel.setWelcomeMessage(username);
            setSize(1280, 720);
            setLocationRelativeTo(null);
            cardLayout.show(cardPanel, "Welcome");
        } else {
            loginPanel.setMessage("Invalid username or password.");
        }
    }

    // Attempts to create a new account with the provided credentials
    private void createAccount() {
        String username = loginPanel.getUsername();
        String password = loginPanel.getPassword();
        
        if (accountManager.createAccount(username, password)) {
            loginPanel.setMessage("Account created successfully.");
        } else {
            loginPanel.setMessage("Username already exists or invalid.");
        }
    }

    // Logs the user out and shows the login panel
    private void logout() {
        setSize(250, 200);
        setLocationRelativeTo(null);
        cardLayout.show(cardPanel, "Login");
    }

    public static void main(String[] args) {
        new CodingGameGUI();
    }
}



