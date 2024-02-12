package com.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Objects;
public class Main extends Application{
    public static void main(String[] args) {
        System.out.println("������ ����������");
        launch(args);
    }
    @Override
    public void init() throws Exception {
        System.out.println("������������� ����������");
        super.init();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image(Objects
                .requireNonNull(getClass()
                .getResourceAsStream("/icon.png"))));
        Parent root = FXMLLoader.load(Objects
                .requireNonNull(getClass()
                .getResource("/Main.fxml")));
        Scene scene = new Scene(root, 600, 250, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(250);
        primaryStage.setMinWidth(600);
        primaryStage.setTitle("�������� ��������� ������");
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    @Override
    public void stop() throws Exception {
        System.out.println("��������� ����������");
        super.stop();
    }
}