import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginAppTest {
// meri bas ho gyi hai :,(
    @Test
    public void testSuccessfulLogin() {
        // Mock database interaction
        LoginApp app = spy(new LoginApp());
        String email = "johndoe@example.com";
        String password = "password123";
        String expectedName = "John Doe";

        doReturn(expectedName).when(app).authenticateUser(email, password);

        String result = app.authenticateUser(email, password);

        assertEquals("The user should be authenticated successfully.", expectedName, result);
        verify(app, times(1)).authenticateUser(email, password);
    }

    @Test
    public void testFailedLogin() {
        // Mock database interaction
        LoginApp app = spy(new LoginApp());
        String email = "nonexistent@example.com";
        String password = "wrongpassword";

        doReturn(null).when(app).authenticateUser(email, password);

        String result = app.authenticateUser(email, password);

        assertNull("The user should not be authenticated.", result);
        verify(app, times(1)).authenticateUser(email, password);
    }

    @Test
    public void testIncorrectPassword() {
        // Mock database interaction
        LoginApp app = spy(new LoginApp());
        String email = "johndoe@example.com";
        String password = "wrongpassword";

        doReturn(null).when(app).authenticateUser(email, password);

        String result = app.authenticateUser(email, password);

        assertNull("The authentication should fail for an incorrect password.", result);
        verify(app, times(1)).authenticateUser(email, password);
    }

    @Test
    public void testEmptyEmailOrPassword() {
        // Mock database interaction
        LoginApp app = spy(new LoginApp());
        String email = "";
        String password = "";

        doReturn(null).when(app).authenticateUser(email, password);

        String result = app.authenticateUser(email, password);

        assertNull("The authentication should fail for empty email or password.", result);
        verify(app, times(1)).authenticateUser(email, password);
    }

    @Test
    public void testSqlInjectionAttempt() {
        // Mock database interaction
        LoginApp app = spy(new LoginApp());
        String email = "anything' OR '1'='1";
        String password = "anything' OR '1'='1";

        doReturn(null).when(app).authenticateUser(email, password);

        String result = app.authenticateUser(email, password);

        assertNull("The authentication should fail for a SQL injection attempt.", result);
        verify(app, times(1)).authenticateUser(email, password);
    }
}
