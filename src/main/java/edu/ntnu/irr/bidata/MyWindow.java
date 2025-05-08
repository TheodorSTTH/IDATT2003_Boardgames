package edu.ntnu.irr.bidata;

import edu.ntnu.irr.bidata.controller.StartPageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The entry point for the Runeborne application.
 * This class sets up the main window and loads the initial scene.
 */
public class MyWindow extends Application {
  private static final Logger log = LoggerFactory.getLogger(MyWindow.class);

  // Reference to the primary JavaFX stage (window)
  private static Stage primaryStage;

  /**
   * Starts the JavaFX application by initializing the main stage.
   *
   * @param primaryStage the primary stage provided by JavaFX
   */
  @Override
  public void start(Stage primaryStage) {
    MyWindow.primaryStage = primaryStage;

    // Sets the stage in the navigation manager for later use
    NavigationManager.setStage(primaryStage);

    // Sets the application icon (make sure /favicon.png exists in resources)
    Image iconImage = new Image("/images/favicon.png");
    primaryStage.setTitle("Runeborne");
    primaryStage.getIcons().add(iconImage);

    // Load the start page UI from its controller
    Scene scene = new Scene(new StartPageController().getView());

    // Apply the stylesheet
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

    // Set up and show the stage
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.setMaximized(true); // Launch in fullscreen
  }

  /**
   * Returns the primary stage of the application.
   *
   * @return the primary JavaFX stage
   */
  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  /**
   * Gracefully closes the application.
   * Calls both Platform.exit() and System.exit().
   */
  public static void closeApplication() {
    log.info("Application is closing...");
    Platform.exit();    // Requests JavaFX application thread to shut down
    System.exit(0);     // Forces JVM termination
  }

  /**
   * The main method — Java application entry point.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    launch(args); // Launch the JavaFX application
  }
}