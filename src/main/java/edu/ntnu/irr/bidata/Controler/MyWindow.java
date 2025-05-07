package edu.ntnu.irr.bidata.Controler;

import edu.ntnu.irr.bidata.Model.FileHandler;
import edu.ntnu.irr.bidata.View.StartPage.StartPage;
import edu.ntnu.irr.bidata.View.StartPage.StartPageController;
import edu.ntnu.irr.bidata.View.StartPage.StartPageView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    Image iconImage = new Image("/favicon.png");
    primaryStage.setTitle("Runeborne");
    primaryStage.getIcons().add(iconImage);

    StartPageView view = new StartPageView(FileHandler.getSavedGames());
    new StartPageController(view);
    Scene scene = new Scene(view);
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.setMaximized(true);
  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void closeApplication() {
    System.out.println("Application is closing...");
    Platform.exit();
    System.exit(0);
  }

  public static void main(String[] args) {
    launch(args); // Launch the JavaFX application
  }
}
