package edu.ntnu.irr.bidata.Controler;

import edu.ntnu.irr.bidata.View.StartPage.StartPagePage;
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
    NavigationManager.setStage(primaryStage);
    NavigationManager.switchScene(new StartPagePage());
  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args); // Launch the JavaFX application
  }
}
