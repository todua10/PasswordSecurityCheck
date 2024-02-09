package com.example;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.FlowPane;
import java.util.Objects;

public class MainController {
    private final ImageView imageShow =
            new ImageView(Objects.requireNonNull(getClass().getResource("/show.png")).toExternalForm());
    private final ImageView imageHide =
            new ImageView(Objects.requireNonNull(getClass().getResource("/hide.png")).toExternalForm());
    private boolean showPasswords = false;
    @FXML
    private Button showHidePassword;
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
    private TextField passwordFieldText;
    @FXML
    private TextField password2FieldText;
    @FXML
    private Label labelOut;
    @FXML
    private void check() {
        String pass;
        String pass2;
        if (!showPasswords){
            pass = passwordField.getText();
            pass2 = password2Field.getText();
        } else {
            pass = passwordFieldText.getText();
            pass2 = password2FieldText.getText();
        }
        if (!pass.equals(pass2)){
            labelOut.setText("Пароли не совпадают!");
        } else if (pass.matches("^((?=.*[а-яёА-ЯЁ])|(?=.*[a-zA-Z]))(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
            labelOut.setText("Надёжный пароль!");
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(pass);
            clipboard.setContent(content);
        } else {
            labelOut.setText("Слабый пароль!");
        }
    }
    @FXML
    private void show(){
        if (!showPasswords){
            showPasswords = true;
            showHidePassword.setGraphic(imageHide);
            passwordFieldText.setText(passwordField.getText());
            passwordFieldText.setVisible(true);
            passwordField.setVisible(false);
            password2FieldText.setText(password2Field.getText());
            password2FieldText.setVisible(true);
            password2Field.setVisible(false);
        } else {
            showPasswords = false;
            showHidePassword.setGraphic(imageShow);
            passwordField.setText(passwordFieldText.getText());
            passwordFieldText.setVisible(false);
            passwordField.setVisible(true);
            password2Field.setText(password2FieldText.getText());
            password2FieldText.setVisible(false);
            password2Field.setVisible(true);
        }
    }
    @FXML
    public void onEnter(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER) {
            check();
        }
    }
}