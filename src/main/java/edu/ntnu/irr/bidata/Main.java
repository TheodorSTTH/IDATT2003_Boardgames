package edu.ntnu.irr.bidata;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    Label helloLabel = new Label("Hello, JavaFX!");
    Scene scene = new Scene(helloLabel, 400, 300);

    primaryStage.setTitle("My JavaFX App");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}