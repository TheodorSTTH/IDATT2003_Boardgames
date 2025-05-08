package edu.ntnu.irr.bidata.controller.risk;

import edu.ntnu.irr.bidata.model.risk.Country;
import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.risk.PlaceTroopsPaneView;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.SpinnerValueFactory;

/**
 * Controller responsible for handling the placement of troops at the start of a turn in the Risk
 * game. It manages user input for selecting a country and specifying the number of troops to place,
 * updates the view accordingly, and transitions to the next game phase when all troops have been
 * placed.
 */
public class PlaceTroopsPaneController extends AbstractSidebarPaneController {

  private PlaceTroopsPaneView view;

  /**
   * Constructs a PlaceTroopsPaneController and sets up interaction logic with the view and model.
   *
   * @param risk the Risk game model instance
   */
  public PlaceTroopsPaneController(Risk risk) {
    super(risk);
    this.view = new PlaceTroopsPaneView();

    // Handle OK button click for placing troops
    view.getOk()
        .setOnAction(
            event -> {
              if (view.getCountryComboBox().getValue() == null) {
                PopUp.showError(
                    "Must select a country", "Please select a country to place troops on.");
              } else {
                risk.placeTroops(
                    view.getCountryComboBox().getValue().getName(),
                    view.getAmountOfTroopsSpinner().getValue());
                update(); // Refresh view after troop placement
              }
            });

    // Update options when pane is expanded (e.g., when user switches tabs)
    view.expandedProperty()
        .addListener(
            (obs, wasExpanded, isNowExpanded) -> {
              if (isNowExpanded) {
                update();
              }
            });

    // Enable OK button only when a country is selected
    view.getCountryComboBox()
        .valueProperty()
        .addListener(
            (obs, oldFrom, newFrom) -> {
              view.getOk().setDisable(newFrom == null);
            });

    update(); // Initial view setup
  }

  /**
   * Updates the placement options available in the view. Called on initialization and after each
   * placement. If no troops remain, moves to the next sidebar pane.
   */
  private void update() {
    if (risk.getTroopsAvailable() > 0) {
      // Limit spinner value to the number of available troops
      ((SpinnerValueFactory.IntegerSpinnerValueFactory)
              view.getAmountOfTroopsSpinner().getValueFactory())
          .setMax(risk.getTroopsAvailable());
    } else {
      // All troops placed — proceed to next pane
      notifyObservers(this.getNextSidebarPane());
    }

    List<Country> placeOptions = risk.getCountriesControlledByActivePlayer();
    Collections.sort(placeOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));

    // Update view labels and combo box contents
    view.getCurrentUserLabel().setText("Current Player: " + risk.getCurrentPlayer().getName());
    view.getInfoLabel()
        .setText(
            "Place troops on country\nYou have " + risk.getTroopsAvailable() + " troops available");
    view.getCountryComboBox().setItems(FXCollections.observableArrayList(placeOptions));
    view.getCountryComboBox().setValue(null);
    view.getOk().setDisable(true);
  }

  /**
   * Returns the associated view for this controller.
   *
   * @return the PlaceTroopsPaneView instance
   */
  public PlaceTroopsPaneView getView() {
    return view;
  }
}
