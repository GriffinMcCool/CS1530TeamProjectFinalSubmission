import java.util.HashMap;
import java.util.Scanner;

public class codingGame{
    public static HashMap<String, String> accounts = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean continueLoop = true;

        // loop thru input
        while(continueLoop){
            System.out.print("input \'C\' to create account, \'L\' to login, \'E\' to exit: ");
            String input = scanner.nextLine();
            switch (input) {
                case "C":
                    System.out.println("Create account selected");
                    createAccount();
                    break;
                case "L":
                    System.out.println("Login selected");
                    break;
                case "E":
                    System.out.println("Exit app selected");
                    continueLoop = false;
                    break;
                default:
                System.out.println("Invalid input.");
                    break;
            }
        }

        scanner.close();
    }

    public static void createAccount(){
        boolean continueLoop = true;
        String username = "", password = "";

        // get username
        while (continueLoop){
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Entered: " + username + ". Is this correct (Y/N)? ");
            String input = scanner.nextLine();
            switch (input){
                case "Y":
                    continueLoop = false;
                    break;
                default:
                    break;
            }
        }

        continueLoop = true;

        // get password (no encryption)
        while (continueLoop){
            System.out.print("Enter password (no encryption): ");
            password = scanner.nextLine();
            System.out.print("Entered: " + password + ". Is this correct (Y/N)? ");
            String input = scanner.nextLine();
            switch (input){
                case "Y":
                    continueLoop = false;
                    break;
                default:
                    break;
            }
        }

        // add to dictionary
        accounts.put(username, password);
    }

    public void login(String username, String password){

    }
}
