package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.model.risk.Country;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.layout.VBox;

/**
 * MoveTroopsPaneView represents the sidebar UI for the troop movement phase of the game. It allows
 * the user to choose which country to move troops from, which country to move troops to, and how
 * many troops to move. It also provides options to confirm or cancel the move.
 */
public class MoveTroopsPaneView extends AbstractSidebarPaneView {

  // UI Components for troop movement
  private final ComboBox<Country> moveFromComboBox;
  private final ComboBox<Country> moveTargetComboBox;
  private final Label moveToLabel;
  private final Spinner<Integer> amountOfTroopsSpinner;
  private final Button ok;
  private final Button dontMoveTroops;
  private final Label currentUserLabel;
  private SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory;

  /**
   * Constructs the MoveTroopsPaneView with necessary UI components. Initializes the layout, sets up
   * style classes, and adds event handlers.
   *
   * @param currentPlayerName The name of the current player (displayed in the view)
   */
  public MoveTroopsPaneView(String currentPlayerName) {
    // Apply style to the container
    getContainer().getStyleClass().addAll("move-troops-pane", "background");

    // Initialize and style the current user label
    currentUserLabel = new Label("Current Player: " + currentPlayerName);
    this.currentUserLabel.getStyleClass().add("fantasy-title-sidbar");
    VBox.setMargin(currentUserLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    // Create and style the "Move from" label and ComboBox
    Label moveFromLabel = new Label("Move from:");
    moveFromLabel.getStyleClass().add("fantasy-text-sidbar");
    VBox.setMargin(moveFromLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    this.moveFromComboBox = new ComboBox<>();
    this.moveFromComboBox.getStyleClass().add("fantasy-combo-box-sidbar");
    moveFromComboBox.setPromptText("Select country to move from");
    VBox.setMargin(moveFromComboBox, new javafx.geometry.Insets(0, 0, 0, 10));

    // Create and style the "Move to" label and ComboBox
    moveToLabel = new Label("Move to:");
    moveToLabel.getStyleClass().add("fantasy-text-sidbar");
    VBox.setMargin(moveToLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    this.moveTargetComboBox = new ComboBox<>();
    this.moveTargetComboBox.getStyleClass().add("fantasy-combo-box-sidbar");
    moveTargetComboBox.setPromptText("Select country to move to");
    VBox.setMargin(moveTargetComboBox, new javafx.geometry.Insets(0, 0, 10, 10));

    // Create and configure the spinner for troop amount
    this.amountOfTroopsSpinner =
        new Spinner<>(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1, 100, 1) // Min: 1, Max: 100, Initial: 1
            );
    spinnerValueFactory =
        (SpinnerValueFactory.IntegerSpinnerValueFactory) amountOfTroopsSpinner.getValueFactory();
    this.amountOfTroopsSpinner.getStyleClass().add("fantasy-spinner-sidbar");
    amountOfTroopsSpinner.setEditable(true); // Allows user to manually type the amount
    VBox.setMargin(amountOfTroopsSpinner, new javafx.geometry.Insets(0, 0, 10, 10));

    // Initialize and style the "Move & finish round" button
    this.ok = new Button("Move & finish round");
    this.ok.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(ok, new javafx.geometry.Insets(0, 0, 10, 10));

    // Initialize and style the "Don't move troops" button
    this.dontMoveTroops = new Button("Don't move troops");
    this.dontMoveTroops.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(dontMoveTroops, new javafx.geometry.Insets(0, 0, 10, 10));

    // Set the title and line spacing for the pane
    this.setText("Move");
    this.setLineSpacing(10);

    // Add all the UI components to the container (VBox)
    getContainer()
        .getChildren()
        .addAll(
            currentUserLabel,
            moveFromLabel,
            moveFromComboBox,
            moveToLabel,
            moveTargetComboBox,
            amountOfTroopsSpinner,
            ok,
            dontMoveTroops);
  }

  // Getter methods for the components to allow interaction with other parts of the application

  /**
   * Returns the SpinnerValueFactory used for controlling the troop amount spinner.
   *
   * @return the IntegerSpinnerValueFactory that controls the troop amount spinner
   */
  public IntegerSpinnerValueFactory getSpinnerValueFactory() {
    return spinnerValueFactory;
  }

  /**
   * Returns the button that cancels the troop movement action.
   *
   * @return the "Don't move troops" button
   */
  public Button getDontMoveTroops() {
    return dontMoveTroops;
  }

  /**
   * Returns the label that displays the "Move to" text.
   *
   * @return the "Move to" label
   */
  public Label getMoveToLabel() {
    return moveToLabel;
  }

  /**
   * Returns the ComboBox that lets the user choose which country to move troops from.
   *
   * @return the ComboBox for selecting the "from" country
   */
  public ComboBox<Country> getMoveFromComboBox() {
    return moveFromComboBox;
  }

  /**
   * Returns the ComboBox that lets the user choose which country to move troops to.
   *
   * @return the ComboBox for selecting the "to" country
   */
  public ComboBox<Country> getMoveTargetComboBox() {
    return moveTargetComboBox;
  }

  /**
   * Returns the Spinner that allows the user to choose the number of troops to move.
   *
   * @return the Spinner for selecting the amount of troops
   */
  public Spinner<Integer> getAmountOfTroopsSpinner() {
    return amountOfTroopsSpinner;
  }

  /**
   * Returns the button that confirms the troop movement action.
   *
   * @return the "Move & finish round" button
   */
  public Button getOk() {
    return ok;
  }

  /**
   * Returns the label displaying the current player's name or status.
   *
   * @return the label showing the current player's information
   */
  public Label getCurrentUserLabel() {
    return currentUserLabel;
  }
}
