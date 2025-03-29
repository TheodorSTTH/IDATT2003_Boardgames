package edu.ntnu.irr.bidata.Controler;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main window for my application!
 */
public class MyWindow extends Application {
  private static Stage primaryStage;


  @Override
  public void start(Stage primaryStage) {
    MyWindow.primaryStage = primaryStage;
    MyWindow.primaryStage.setTitle("BoardGame");
    
    // Switch scenes AFTER JavaFX is ready
    UI.toAmountOfPlayersPage();


  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args); // Launch the JavaFX application
  }
}
