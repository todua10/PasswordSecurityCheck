package com.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Objects;
public class Main extends Application{
    public static void main(String[] args) {
        System.out.println("Launching Application");
        launch(args);
    }
    @Override
    public void init() throws Exception {
        System.out.println("Application init");
        super.init();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main.fxml")));
        Scene scene = new Scene(root, 500, 520, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(320);
        primaryStage.setMinWidth(300);
        primaryStage.setTitle("Password security check");
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    @Override
    public void stop() throws Exception {
        System.out.println("Application stops");
        super.stop();
    }
}