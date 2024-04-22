import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

public class CodingGameGUI extends JFrame {
    private static HashMap<String, String> accounts = new HashMap<>(); // Holds username-password pairs from the "accounts.properties" file (fake database) for quick lookup 
    private static final String ACCOUNTS_FILE = "accounts.properties"; // File path for storing account data
    private Properties properties = new Properties(); // Properties object to interact with account data file
    private CardLayout cardLayout; // Manages the switching between login and welcome panels
    private JPanel cardPanel; // Contains the cards (panels)

    private JPanel loginPanel; // Holds login components
    private JTextField usernameField; // Field for user to input username
    private JPasswordField passwordField; // Field for user to input password
    private JButton loginButton; // Button that triggers the login process
    private JButton createAccountButton; // Button that triggers the account creation process
    private JLabel messageLabel; // Label to display feedback messages

    public CodingGameGUI() {
        super("Login System"); // Setting the title of the window
        initializeUI(); // Setting up the user interface components
        loadAccounts(); // Loading account data from file into the application
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensuring the application closes when the window is closed
        setPreferredSize(new Dimension(250, 200)); // Gives a preferred size for the JFrame
        pack(); // Sizes the frame so all contents are at or above their preferred sizes
        setLocationRelativeTo(null); // Centering the window on the screen

        JLabel appNameLabel = new JLabel("Coding Game", JLabel.CENTER); // Creating label for the application name
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Setting font for the application name

        cardLayout = new CardLayout(); // Initializing the layout manager for switching panels
        cardPanel = new JPanel(cardLayout); // Panel that will hold the different "cards" or panels

        createLoginPanel(); // Creating and adding components to the login panel
        createWelcomePanel(); // Creating and adding components to the welcome panel

        setLayout(new BorderLayout()); // Setting layout of the JFrame
        add(appNameLabel, BorderLayout.NORTH); // Adding the application name label at the top of the layout
        add(cardPanel, BorderLayout.CENTER); // Adding the panel that contains cards to the center of the layout
        
        setResizable(false); // Disabling the resizing of the window
        setVisible(true); // Making the JFrame visible
    }

    private void createLoginPanel() {
        loginPanel = new JPanel(new FlowLayout()); // Using FlowLayout for the login panel
        loginPanel.add(new JLabel("Username:")); // Adding a label for username
        usernameField = new JTextField(20); // Creating text field for username input
        loginPanel.add(usernameField); // Adding the username field to the panel

        loginPanel.add(new JLabel("Password:")); // Adding a label for password
        passwordField = new JPasswordField(20); // Creating password field for password input
        loginPanel.add(passwordField); // Adding the password field to the panel

        loginButton = new JButton("Login"); // Creating the login button
        createAccountButton = new JButton("Create Account"); // Creating the create account button
        loginPanel.add(loginButton); // Adding the login button to the panel
        loginPanel.add(createAccountButton); // Adding the create account button to the panel

        messageLabel = new JLabel(" "); // Creating a label for displaying messages
        loginPanel.add(messageLabel); // Adding the message label to the panel

        loginButton.addActionListener(e -> login()); // Adding action listener to the login button
        createAccountButton.addActionListener(e -> createAccount()); // Adding action listener to the create account button

        cardPanel.add(loginPanel, "Login"); // Adding the login panel as a card to the card panel
    }

    private void createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new FlowLayout()); // Using FlowLayout for the welcome panel
        JLabel welcomeLabel = new JLabel("Welcome, user!"); // Creating a welcome label
        welcomePanel.add(welcomeLabel); // Adding the welcome label to the panel

        JButton logoutButton = new JButton("Logout"); // Creating a logout button
        logoutButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Login"); // Switching back to the login panel when logout is pressed
            setSize(250, 200); // Resizing the window when showing the login panel
        });
        welcomePanel.add(logoutButton); // Adding the logout button to the panel

        cardPanel.add(welcomePanel, "Welcome"); // Adding the welcome panel as a card to the card panel
    }

    private void login() {
        String username = usernameField.getText(); // Retrieving the username from the text field
        String password = new String(passwordField.getPassword()); // Retrieving the password from the password field

        if (password.equals(accounts.get(username))) { // If username/password is in hashmap of accounts
            messageLabel.setText("Login successful."); // Setting text to indicate successful login
            ((JLabel) ((JPanel) cardPanel.getComponent(1)).getComponent(0)).setText("Welcome, " + username + "!"); // Personalizing the welcome message
            setSize(1280, 720); // Increasing size for welcome screen
            setLocationRelativeTo(null); // Centering the screen for the new size
            cardLayout.show(cardPanel, "Welcome"); // Showing the welcome panel
        } else {
            messageLabel.setText("Invalid username or password."); // Setting text to indicate failed login
        }
    }

    private void createAccount() {
        String username = usernameField.getText(); // Retrieving the username from the text field
        String password = new String(passwordField.getPassword()); // Retrieving the password from the password field

        if (!username.isEmpty() && !password.isEmpty() && !accounts.containsKey(username)) { // If username doesn't exist in the hashmap 
            accounts.put(username, password); // Adding the new username-password pair to accounts
            properties.setProperty(username, password); // Storing the new account in properties
            saveAccounts(); // Saving the updated accounts to file
            messageLabel.setText("Account created successfully."); // Indicating successful account creation
        } else {
            messageLabel.setText("Username already exists or invalid."); // Indicating failure to create account
        }
    }

    private void loadAccounts() {
        try (InputStream input = new FileInputStream(ACCOUNTS_FILE)) {
            properties.load(input); // Loading account data from the properties file
            for (String key : properties.stringPropertyNames()) {
                accounts.put(key, properties.getProperty(key)); // Populating the accounts HashMap with data from properties
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Printing any error that occurs during loading
        }
    }

    private void saveAccounts() {
        try (OutputStream output = new FileOutputStream(ACCOUNTS_FILE)) {
            properties.store(output, "Accounts"); // Storing the accounts data in the properties file
        } catch (IOException ex) {
            ex.printStackTrace(); 
        }
    }

    public static void main(String[] args) {
        new CodingGameGUI(); // Creating an instance of the GUI
    }
}


