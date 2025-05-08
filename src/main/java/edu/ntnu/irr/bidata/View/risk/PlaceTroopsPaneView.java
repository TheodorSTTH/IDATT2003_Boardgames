package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.model.risk.Country;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;

/**
 * PlaceTroopsPaneView represents the sidebar UI for the troop placement phase of the game. It
 * allows the user to select a country to place troops in and specify the number of troops to be
 * placed. The user can then confirm the placement of the troops.
 */
public class PlaceTroopsPaneView extends AbstractSidebarPaneView {

  // UI Components for troop placement
  private final Label infoLabel = new Label();
  private final ComboBox<Country> countryComboBox;
  private final Spinner<Integer> amountOfTroopsSpinner;
  private final Button ok = new Button("Place troops");
  private final Label currentUserLabel = new Label();

  /**
   * Constructs the PlaceTroopsPaneView with necessary UI components. Initializes the layout, sets
   * up style classes, and adds event handlers.
   */
  public PlaceTroopsPaneView() {
    // Apply style to the container
    getContainer().getStyleClass().addAll("place-troops-pane", "background");
    this.setText("Place Troops");
    this.setLineSpacing(10);

    // Initialize and style the current user label
    this.currentUserLabel.getStyleClass().addAll("fantasy", "taxt-black", "text-20");
    VBox.setMargin(currentUserLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    // Initialize and style the info label
    infoLabel.getStyleClass().addAll("fantasy", "text-black", "text-30");
    VBox.setMargin(infoLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    // Create and style the ComboBox for selecting a country
    this.countryComboBox = new ComboBox<>();
    this.countryComboBox.getStyleClass().addAll("fantasy-combo-box-sidbar", "fantasy");
    VBox.setMargin(countryComboBox, new javafx.geometry.Insets(0, 5, 10, 10));

    // Create and configure the Spinner for selecting the number of troops to place
    this.amountOfTroopsSpinner =
        new Spinner<>(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1, 100, 1) // Min: 1, Max: 100, Initial: 1
            );
    this.amountOfTroopsSpinner.getStyleClass().addAll("fantasy-spinner-sidbar", "fantasy");
    this.amountOfTroopsSpinner.setEditable(true); // Allows manual input for troop number
    VBox.setMargin(amountOfTroopsSpinner, new javafx.geometry.Insets(10, 5, 10, 10));

    // Initialize and style the "Place troops" button
    this.ok.getStyleClass().addAll("fantasy-button-sidbar", "fantasy");
    VBox.setMargin(ok, new javafx.geometry.Insets(0, 5, 10, 10));

    // Add all components to the container (VBox)
    getContainer()
        .getChildren()
        .addAll(currentUserLabel, infoLabel, countryComboBox, amountOfTroopsSpinner, ok);
  }

  // Getter methods for each UI component to allow interaction from other parts of the application

  /**
   * Returns the label displaying the information about troop placement.
   *
   * @return the label showing information or instructions to the user
   */
  public Label getInfoLabel() {
    return infoLabel;
  }

  /**
   * Returns the ComboBox for selecting the country to place troops in.
   *
   * @return the ComboBox for selecting the country
   */
  public ComboBox<Country> getCountryComboBox() {
    return countryComboBox;
  }

  /**
   * Returns the Spinner for selecting the number of troops to place in the selected country.
   *
   * @return the Spinner for troop amount
   */
  public Spinner<Integer> getAmountOfTroopsSpinner() {
    return amountOfTroopsSpinner;
  }

  /**
   * Returns the button that confirms the placement of troops.
   *
   * @return the "Place troops" button
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
