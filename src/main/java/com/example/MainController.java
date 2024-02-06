package com.example;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.FlowPane;
public class MainController {
    @FXML
    private Button checkPassword;
    @FXML
    private FlowPane root;
    @FXML
    private Group group;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField password2Field;
    @FXML
    private Label labelOut;
    @FXML
    private void check() {
        String pass = passwordField.getText();

        if (!pass.equals(password2Field.getText())){
            labelOut.setText("Password doesn't match!");
        } else if (pass.equals(password2Field.getText()) &&
                    pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
            labelOut.setText("Strong password!");
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(passwordField.getText());
            clipboard.setContent(content);
        } else {
            labelOut.setText("Weak password!");
        }
    }
}