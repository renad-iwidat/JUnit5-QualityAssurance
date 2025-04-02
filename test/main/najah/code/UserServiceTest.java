package main.najah.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

public class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    @DisplayName("Test valid and invalid emails")
    void testIsValidEmail() {
        // Valid emails
        assertTrue(userService.isValidEmail("test@example.com"), "Email should be valid");
        assertTrue(userService.isValidEmail("user.name@domain.com"), "Email should be valid");

        // Invalid emails
        assertFalse(userService.isValidEmail("test@domain"), "Email should be invalid (missing '.')");
        assertFalse(userService.isValidEmail("testdomain.com"), "Email should be invalid (missing '@')");
        assertFalse(userService.isValidEmail("test@.com"), "Email should be invalid (invalid placement of '.')");
        assertFalse(userService.isValidEmail("@domain.com"), "Email should be invalid (missing username)");
    }

    @Test
    @DisplayName("Test valid and invalid authentication")
    void testAuthenticate() {
        // Valid credentials
        assertTrue(userService.authenticate("admin", "1234"), "Credentials should be valid");

        // Invalid credentials
        assertFalse(userService.authenticate("admin", "wrongpassword"), "Credentials should be invalid (wrong password)");
        assertFalse(userService.authenticate("wronguser", "1234"), "Credentials should be invalid (wrong username)");
        assertFalse(userService.authenticate("wronguser", "wrongpassword"), "Credentials should be invalid (both wrong)");
    }

    @ParameterizedTest
    @CsvSource({
            "'test@example.com', true", // Valid email
            "'user.name@domain.com', true", // Valid email
            "'test@domain', false", // Invalid email (missing '.')
            "'testdomain.com', false", // Invalid email (missing '@')
            "'test@.com', false", // Invalid email (invalid placement of '.')
            "'@domain.com', false" // Invalid email (missing username)
    })
    @DisplayName("Parameterized test for email validation")
    void testIsValidEmailParameterized(String email, boolean expected) {
        assertEquals(expected, userService.isValidEmail(email), "Email validation result should match expected");
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    @DisplayName("Test for timeout (should finish within 2 seconds)")
    void testTimeout() {
        assertTrue(userService.isValidEmail("test@example.com"), "Email validation should be quick");
        assertTrue(userService.authenticate("admin", "1234"), "Authentication should be quick");
    }

    @Test
    @DisplayName("Test multiple assertions in a single test")
    void testMultipleAssertions() {
        // Testing isValidEmail
        assertTrue(userService.isValidEmail("test@example.com"), "Email should be valid");
        assertFalse(userService.isValidEmail("test@domain"), "Email should be invalid (missing '.')");

        // Testing authenticate
        assertTrue(userService.authenticate("admin", "1234"), "Credentials should be valid");
        assertFalse(userService.authenticate("admin", "wrongpassword"), "Credentials should be invalid");
    }
}
