package com.example;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
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
    private final Tooltip tooltipStrong = new Tooltip("Надёжный пароль!");
    private final Tooltip tooltipWeak = new Tooltip("Слабый пароль!");
    private final Tooltip tooltipMismatch = new Tooltip("Пароли не совпадают!");
    private final Tooltip tooltipPass = new Tooltip("""
            Пароль должен быть не менее 8 символов и содержать:\s
             - Одну заглавную букву(русскую или английскую);\s
             - Одну строчную букву(русскую или английскую);\s
             - Одну цифру;\s
             - Один специальный символ(@#$%^&+=).""");
    PauseTransition pause = new PauseTransition(Duration.seconds(2.5));

    private void setParams4ToolTip(Tooltip tt, double showDelay, double hideDelay) {
        tt.setShowDelay(Duration.millis(showDelay));
        tt.setHideDelay(Duration.seconds(hideDelay));
    }

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
            checkPassword.setTooltip(tooltipMismatch);
            tooltipMismatch.show(checkPassword,
                    checkPassword.localToScreen(checkPassword.getBoundsInLocal()).getMinX(),
                    checkPassword.localToScreen(checkPassword.getBoundsInLocal()).getMaxY());
            pause.play();
        } else if (pass.matches("^((?=.*[а-яёА-ЯЁ])|(?=.*[a-zA-Z]))(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
            checkPassword.setTooltip(tooltipStrong);
            tooltipStrong.show(checkPassword,
                    checkPassword.localToScreen(checkPassword.getBoundsInLocal()).getMinX(),
                    checkPassword.localToScreen(checkPassword.getBoundsInLocal()).getMaxY());
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(pass);
            clipboard.setContent(content);
            pause.play();
        } else {
            checkPassword.setTooltip(tooltipWeak);
            tooltipWeak.show(checkPassword,
                    checkPassword.localToScreen(checkPassword.getBoundsInLocal()).getMinX(),
                    checkPassword.localToScreen(checkPassword.getBoundsInLocal()).getMaxY());
            pause.play();
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
            checkPassword.fire();
        }
    }
    @FXML
    public void initialize() {
        showHidePassword.setFocusTraversable(false);
        pause.setOnFinished(event -> {
            tooltipMismatch.hide();
            tooltipStrong.hide();
            tooltipWeak.hide();
        });
        tooltipMismatch.setStyle("-fx-font-family: \"Century Gothic\"; "
                + "-fx-font-size: 14; "
                + "-fx-background-color: #ffffff; "
                + "-fx-text-fill: #ff0000ff;");
        tooltipStrong.setStyle("-fx-font-family: \"Century Gothic\"; "
                + "-fx-font-size: 14; "
                + "-fx-background-color: #ffffff; "
                + "-fx-text-fill: #00bf00ff;");
        tooltipWeak.setStyle("-fx-font-family: \"Century Gothic\"; "
                + "-fx-font-size: 14; "
                + "-fx-background-color: #ffffff; "
                + "-fx-text-fill: #ff0000ff;");
        tooltipPass.setStyle("-fx-font-family: \"Century Gothic\"; "
                + "-fx-font-size: 16; "
                + "-fx-background-color: #ffffff; "
                + "-fx-text-fill: #00bf00ff;");
        setParams4ToolTip(tooltipMismatch, 1, 2.5);
        setParams4ToolTip(tooltipStrong, 1, 2.5);
        setParams4ToolTip(tooltipWeak, 1, 2.5);
        setParams4ToolTip(tooltipPass, 2000, 2.5);
        passwordField.setTooltip(tooltipPass);
        passwordFieldText.setTooltip(tooltipPass);
        // Устанавливаем слушателя событий на изменение текста в поле passwordField
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            // При изменении текста удаляем Tooltip из кнопки
            checkPassword.setTooltip(null);
        });
        // Устанавливаем слушателя событий на изменение текста в поле passwordFieldText
        passwordFieldText.textProperty().addListener((observable, oldValue, newValue) -> {
            // При изменении текста удаляем Tooltip из кнопки
            checkPassword.setTooltip(null);
        });
        // Устанавливаем слушателя событий на изменение текста в поле password2Field
        password2Field.textProperty().addListener((observable, oldValue, newValue) -> {
            // При изменении текста удаляем Tooltip из кнопки
            checkPassword.setTooltip(null);
        });
        // Устанавливаем слушателя событий на изменение текста в поле password2FieldText
        password2FieldText.textProperty().addListener((observable, oldValue, newValue) -> {
            // При изменении текста удаляем Tooltip из кнопки
            checkPassword.setTooltip(null);
        });
    }
}