package edu.ntnu.irr.bidata.Controler;

import edu.ntnu.irr.bidata.View.StartPage.StartPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main window for my application!
 */
public class MyWindow extends Application {
  private static Stage primaryStage;

  @Override
  public void start(Stage primaryStage) {
    MyWindow.primaryStage = primaryStage;
    NavigationManager.setStage(primaryStage);
    primaryStage.setTitle("BoardGame");
    primaryStage.setScene(new Scene(new StartPage()));
    primaryStage.show();
    primaryStage.setMaximized(true);
  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args); // Launch the JavaFX application
  }
}
