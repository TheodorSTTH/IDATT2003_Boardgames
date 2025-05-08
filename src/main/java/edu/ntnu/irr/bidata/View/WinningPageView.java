package edu.ntnu.irr.bidata.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Represents the final page displayed when a player wins the game.
 * <p>
 * This view shows the winner's name and provides navigation options
 * for the player to return to the start page or exit the game.
 * </p>
 */
public class WinningPageView extends VBox {
  /** Label displaying the winner's name. */
  Label winLabel = new Label();

  /** Button to navigate back to the start menu. */
  Button backToMenuButton = new Button("Back to the start page");

  /** Button to exit the application. */
  Button exitButton = new Button("Leave Runeborne");

  /**
   * Constructs a new {@code WinningPageView} with the given winner's name and style class.
   *
   * @param winner the name of the player who won the game
   * @param winPageClass the style class to apply to this page for theming
   */
  public WinningPageView(String winner, String winPageClass) {
    // Apply the custom style class for this winning page.
    getStyleClass().addAll(winPageClass, "background");

    // Set alignment for the elements in the VBox to be at the top-right corner.
    setAlignment(Pos.TOP_RIGHT);

    // Create a label to display the winner's name.
    winLabel.setText("You win " + winner + "!");
    VBox.setMargin(winLabel, new Insets(10, 20, 10, 20));
    winLabel.getStyleClass().add("fantasy-title");

    // Style button that navigates back to the start page.
    backToMenuButton.getStyleClass().addAll("fantasy-button-sidbar");
    VBox.setMargin(backToMenuButton, new Insets(25, 25, 25, 25));

    // Create a button to exit the application.
    exitButton.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(exitButton, new Insets(25, 25, 25, 25));

    // Add all the components (win label, back button, exit button) to the VBox.
    getChildren().addAll(winLabel, backToMenuButton, exitButton);
  }

  /**
   * Returns the button that allows the user to navigate back to the start menu.
   *
   * @return the "Back to Menu" button
   */
  public Button getBackToMenuButton() {
    return backToMenuButton;
  }

  /**
   * Returns the button that allows the user to exit the application.
   *
   * @return the "Exit" button
   */
  public Button getExitButton() {
    return exitButton;
  }
}