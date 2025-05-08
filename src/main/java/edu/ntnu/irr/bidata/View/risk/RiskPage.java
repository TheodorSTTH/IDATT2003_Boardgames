package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.NavigationManager;
import edu.ntnu.irr.bidata.controller.StartPageController;
import edu.ntnu.irr.bidata.controller.risk.RiskSidePanelController;
import edu.ntnu.irr.bidata.model.risk.Country;
import edu.ntnu.irr.bidata.model.risk.Risk;
import java.util.HashMap;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The RiskPage class is the main UI view for the Risk game. It contains the game board, the side
 * panel, and buttons for saving the game or exiting. This page is part of the game interface where
 * users interact with the board and the game state.
 */
public class RiskPage extends HBox {

  private RiskBoardView board; // The view representing the Risk game board
  private RiskSidePanelView sidePanel; // The side panel view showing additional game information

  /**
   * Constructs a new RiskPage which represents the main view of the Risk game. The page includes
   * the game board, the side panel, and buttons for game actions.
   *
   * @param risk The current state of the Risk game, containing the game board and other
   *     game-related data.
   */
  public RiskPage(Risk risk) {
    super(new HBox()); // Creates a new horizontal box (HBox) layout for the page

    // Initialize the Risk game board and side panel
    this.board = new RiskBoardView(risk.getBoard().getCountries());
    this.sidePanel = new RiskSidePanelController(risk).getView();

    // Set background color for the page
    this.setStyle("-fx-background-color:rgb(72, 163, 255);");

    // Update the game views (board and side panel) based on the current countries
    updateViews(risk.getBoard().getCountries());

    // Create and style the label that shows the bonus territories in different regions
    Label bonusesLabel =
        new Label(
            "Europe: 5   Asia: 7   North America: 5\nSouth America: 2   Africa: 3   Australia: 2");
    bonusesLabel.getStyleClass().add("fantasy-text-sidbar");

    // Create and style the "Save" button
    Button saveButton = new Button("SAVE");
    saveButton.getStyleClass().add("fantasy-button");

    // Create and style the "Exit game" button, which navigates to the start page
    Button exitGameButton = new Button("Exit game");
    exitGameButton.getStyleClass().addAll("fantasy-button");
    exitGameButton.setOnAction(
        e -> {
          NavigationManager.navigate(
              new StartPageController().getView()); // Navigate to the start page
        });

    // Create an HBox to hold the labels and buttons at the bottom of the page
    HBox underlay = new HBox(bonusesLabel, saveButton, exitGameButton);
    underlay.setAlignment(Pos.CENTER); // Center-align the buttons and label
    underlay.setSpacing(15); // Add spacing between elements

    // Create a main stage container for the board and underlay elements
    VBox mainStage = new VBox(10);
    mainStage.getChildren().addAll(board, underlay); // Add the board and underlay to the main stage

    // Set the action for the "Save" button to save the game state
    saveButton.setOnAction(
        e -> {
          risk.saveGame(); // Save the game using the provided Risk model
        });

    // Add the side panel and the main stage (board + buttons) to the page layout
    getChildren().addAll(sidePanel, mainStage);
  }

  /**
   * Updates the views of the game page (board and side panel) based on the given countries. This
   * method can be used to refresh the displayed information whenever the state changes.
   *
   * @param countries A HashMap linking country names to Country objects representing the game's
   *     countries.
   */
  public void updateViews(HashMap<String, Country> countries) {
    // Logic to update the views (board and side panel) based on the countries would go here.
    // Currently, it's an empty implementation and might be extended to update UI components
    // dynamically.
  }

  /**
   * Gets the side panel view that is displayed alongside the Risk game board.
   *
   * @return The side panel view (RiskSidePanelView) showing game statistics, actions, etc.
   */
  public RiskSidePanelView getSidePanelView() {
    return sidePanel;
  }
}
