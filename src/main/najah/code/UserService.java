package main.najah.code;

public class UserService {
    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        int atIndex = email.indexOf('@');
        int dotIndex = email.indexOf('.', atIndex);  // '.' must come after '@'

        // Check if '@' exists and '.' exists after '@', and the '.' is not at the end
        return atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < email.length() - 1;
    }

    public boolean authenticate(String username, String password) {
        return "admin".equals(username) && "1234".equals(password);
    }
}
