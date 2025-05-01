package edu.ntnu.irr.bidata.Controler;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class is responsible for switching java fx scenes based of given scene
 * */
public class NavigationManager {
  private static Stage primaryStage;

  /**
   * Sets the new primary stage which user is going to view.
   *
   * @param stage New stage
   * */
  public static void setStage(Stage stage) {
    primaryStage = stage;
  }

  /**
   * Responsible for switching between scenes. Creates a new scene based of a loaded view
   * and then changes to it.
   *
   * @param scene new scene
   * */
  public static void switchScene(Scene scene) {
    primaryStage.hide();
    primaryStage.setMaximized(false);
    primaryStage.setScene(scene);
    primaryStage.setMaximized(true);
    primaryStage.show();
  }
}