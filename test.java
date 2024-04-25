import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;

public class test {
    
    private CodingGameGUI game;

    @Before
    public void setUp() throws IOException {
        game = new CodingGameGUI();
        game.getAccountManager().resetAccounts();
    }

    @Test
    public void testCreateAccount() {
        // Simulate a successful login
        game.getLoginPanel().setUsername("TestUsername");
        game.getLoginPanel().setPassword("TestPassword");
        game.createAccount();
        assertEquals("Account created successfully.", game.getLoginPanel().getMessage().getText());
    }

}
