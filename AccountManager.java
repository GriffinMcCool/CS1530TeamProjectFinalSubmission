import java.io.*;
import java.util.HashMap;
import java.util.Properties;

// Manages user accounts by loading, validating, and creating new entries
public class AccountManager {
    private static final String ACCOUNTS_FILE = "accounts.properties";
    private Properties properties = new Properties();
    private HashMap<String, String> accounts = new HashMap<>(); // Holds username-password pairs from the "accounts.properties" file (fake database) for quick lookup

    public AccountManager() {
        loadAccounts();
    }

    // Validates username and password against the stored entries
    public boolean validateLogin(String username, String password) {
        return password.equals(accounts.get(username));
    }

    // Creates a new account if the username is unique and fields are not empty
    public boolean createAccount(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty() && !accounts.containsKey(username)) { // If username doesn't exist in the hashmap and fields are valid (not empty)
            accounts.put(username, password);
            properties.setProperty(username, password); // Storing the new account in properties
            saveAccounts(); // Saving the updated accounts to file
            return true;
        }
        return false;
    }

    // Loads account data from a file into the properties object and HashMap
    private void loadAccounts() {
        try (InputStream input = new FileInputStream(ACCOUNTS_FILE)) {
            properties.load(input);
            for (String key : properties.stringPropertyNames()) {
                accounts.put(key, properties.getProperty(key));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Saves current account data to the file
    private void saveAccounts() {
        try (OutputStream output = new FileOutputStream(ACCOUNTS_FILE)) {
            properties.store(output, "Accounts");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
