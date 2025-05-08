package edu.ntnu.irr.bidata.view.snakesandladders;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * This class represents the side panel view of the Snakes and Ladders game. It contains buttons for
 * rolling the dice, saving the game, and exiting the game. It also includes a label to display the
 * current player's username.
 */
public class SnakesAndLaddersSidePanelView extends VBox {

  // Instance variables for the side panel elements
  private final Label usernameLabel;
  private final Button rollButton = new Button("Roll");
  private final Button saveButton = new Button("Save current game");
  private final FlowPane diceBox = new FlowPane();
  private final Button exitGameButton = new Button("Exit game");

  /** Constructs the side panel view with buttons and a label. */
  public SnakesAndLaddersSidePanelView() {
    // Initialize the username label and apply styles
    this.usernameLabel = new Label();
    this.usernameLabel.getStyleClass().addAll("fantasy", "text-30", "text-gold");

    // Set the style of the entire side panel using inline CSS
    this.setStyle(
        "-fx-padding: 20px;-fx-background-radius: 20px;-fx-border-width: 3;-fx-border-radius:"
            + " 8;-fx-background-color: linear-gradient(to bottom, rgba(0, 0, 0, 0.44), rgba(0, 0,"
            + " 0, 0.66));-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 5, 0.5, 0, 2);");

    // Set spacing between elements in the VBox
    setSpacing(10);

    // Apply styles to the buttons
    rollButton.getStyleClass().addAll("button", "normal-size", "fantasy");
    saveButton.getStyleClass().addAll("button", "normal-size", "fantasy");
    exitGameButton.getStyleClass().addAll("button", "normal-size", "fantasy");

    // Set the horizontal and vertical gaps for the dice box
    diceBox.setHgap(5);
    diceBox.setVgap(5);

    // Create a splitter region to allow flexible resizing between the elements
    Region splitterRegion = new Region();
    VBox.setVgrow(splitterRegion, Priority.ALWAYS);

    // Add all the elements to the side panel
    this.getChildren()
        .addAll(
            usernameLabel, // Label for displaying the current player's username
            rollButton, // Button to roll the dice
            diceBox, // FlowPane for displaying the dice (empty initially)
            splitterRegion, // Flexible region to allow resizing
            exitGameButton, // Button to exit the game
            saveButton // Button to save the current game state
        );
  }

  // Getter methods for the elements in the side panel

  /**
   * Returns the username label to display the current player's name.
   *
   * @return the username label
   */
  public Label getUsernameLabel() {
    return usernameLabel;
  }

  /**
   * Returns the roll button to roll the dice.
   *
   * @return the roll button
   */
  public Button getRollButton() {
    return rollButton;
  }

  /**
   * Returns the save button to save the current game.
   *
   * @return the save button
   */
  public Button getSaveButton() {
    return saveButton;
  }

  /**
   * Returns the FlowPane containing dice elements.
   *
   * @return the dice box
   */
  public FlowPane getDiceBox() {
    return diceBox;
  }

  /**
   * Returns the exit game button to exit the current game.
   *
   * @return the exit game button
   */
  public Button getExitGameButton() {
    return exitGameButton;
  }
}
