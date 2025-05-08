package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.controller.risk.RiskSidePanelController;
import edu.ntnu.irr.bidata.model.risk.Risk;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The RiskPageView class is the main UI view for the Risk game. It contains the game board, the side
 * panel, and buttons for saving the game or exiting. This page is part of the game interface where
 * users interact with the board and the game state.
 */
public class RiskPageView extends HBox {
  private final Button saveButton;
  private final Button exitGameButton;

  /**
   * Constructs a new RiskPageView which represents the main view of the Risk game. The page includes
   * the game board, the side panel, and buttons for game actions.
   *
   * @param risk The current state of the Risk game, containing the game board and other
   *     game-related data.
   */
  public RiskPageView(Risk risk) { // Use whole Risk object - KISS
    super(new HBox()); // Creates a new horizontal box (HBox) layout for the page

    // Set background color for the page
    this.setStyle("-fx-background-color:rgb(72, 163, 255);");

    // Create and style the label that shows the bonus territories in different regions
    Label bonusesLabel =
        new Label(
            "Europe: 5   Asia: 7   North America: 5\nSouth America: 2   Africa: 3   Australia: 2");
    bonusesLabel.getStyleClass().addAll("fantasy", "text-black", "text-30");

    // Create and style the "Save" button
    saveButton = new Button("SAVE");
    saveButton.getStyleClass().addAll("button", "text-18", "fantasy");

    // Create and style the "Exit game" button, which navigates to the start page
    exitGameButton = new Button("Exit game");
    exitGameButton.getStyleClass().addAll("button", "text-18", "fantasy");

    // Create an HBox to hold the labels and buttons at the bottom of the page
    HBox underlay = new HBox(bonusesLabel, saveButton, exitGameButton);
    underlay.setAlignment(Pos.CENTER); // Center-align the buttons and label
    underlay.setSpacing(15); // Add spacing between elements

    // Initialize the Risk game board and side panel
    RiskBoardView board = new RiskBoardView(risk.getBoard().getCountries()); // The view representing the Risk game board
    RiskSidePanelView sidePanel = new RiskSidePanelController(risk).getView(); // The side panel view showing additional game information

    // Create a main stage container for the board and underlay elements
    VBox mainStage = new VBox(10);
    mainStage.getChildren().addAll(board, underlay); // Add the board and underlay to the main stage

    getChildren().addAll(sidePanel, mainStage);
  }

  /**
   * Save game button to attach listeners / action listeners.
   *
   * @return Button for saving game.
   * */
  public Button getSaveButton() {
    return saveButton;
  }

  /**
   * Exit game button to attach listeners / action listeners.
   *
   * @return Button for exiting game.
   * */
  public Button getExitGameButton() {
    return exitGameButton;
  }
}
