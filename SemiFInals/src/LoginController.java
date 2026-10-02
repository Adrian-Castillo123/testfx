import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {

        String email = emailField.getText();
        String password = passwordField.getText();

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }

    @FXML
    private void handleRegister() {

        System.out.println("Register button clicked");
    }
}