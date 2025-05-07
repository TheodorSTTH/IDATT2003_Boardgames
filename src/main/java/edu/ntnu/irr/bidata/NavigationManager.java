package edu.ntnu.irr.bidata;

import javafx.scene.Parent;
import javafx.stage.Stage;

/** Class is responsible for switching java fx scenes based of given scene */
public class NavigationManager {
  private static Stage primaryStage;

  /**
   * Sets the new primary stage which user is going to view.
   *
   * @param stage New stage
   */
  public static void setStage(Stage stage) {
    primaryStage = stage;
  }

  public static Stage getStage() {
    return primaryStage;
  }

  /**
   * Switches out the current parent node of the scene with a new parent root.
   *
   * @param parentNode Node we are switching to
   */
  public static void navigate(Parent parentNode) {
    primaryStage.getScene().setRoot(parentNode);
  }
}
