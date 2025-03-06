package edu.ntnu.irr.bidata.GeneralClassedFrontend;


import javafx.application.Application;
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
    UI.toAmountOfPlayersPage();
    

  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args); // Launch the JavaFX application
  }
}
