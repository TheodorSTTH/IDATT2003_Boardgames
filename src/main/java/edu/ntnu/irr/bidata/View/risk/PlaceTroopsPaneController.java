package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.model.risk.Country;
import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.view.PopUp;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.SpinnerValueFactory;

public class PlaceTroopsPaneController extends AbstractSidebarPaneController {
  private PlaceTroopsPaneView view;

  public PlaceTroopsPaneController(Risk risk) {
    super(risk);
    this.view = new PlaceTroopsPaneView();

    view.getOk().setOnAction(event -> {
      if (view.getCountryComboBox().getValue() == null) {
        PopUp.showError("Must select a county","Please select a country to place troops on.");
      } else {
        risk.placeTroops(view.getCountryComboBox().getValue().getName(), view.getAmountOfTroopsSpinner().getValue());
        update();
      }
    });

    // Call update when the pane is expanded
    view.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
      if (isNowExpanded) {
        update();
      }
    });

    view.getCountryComboBox().valueProperty().addListener((obs, oldFrom, newFrom) -> {
      view.getOk().setDisable(newFrom == null);
    });

    // Initial update for when it's first created
    update();
  }


  private void update() {
    if (risk.getTroopsAvailable() > 0) {
      ((SpinnerValueFactory.IntegerSpinnerValueFactory) view.getAmountOfTroopsSpinner().getValueFactory())
          .setMax(risk.getTroopsAvailable());
    } else {
      notifyObservers(this.getNextSidebarPane());
    }
    List<Country> placeOptions = risk.getCountriesControlledByActivePlayer();
    Collections.sort(placeOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));
    view.getCurrentUserLabel().setText("Current Player: " + risk.getCurrentPlayer().getName());
    view.getInfoLabel().setText("Place troops on country\n" + "You have " + risk.getTroopsAvailable() + " troops available");
    view.getCountryComboBox().setItems(FXCollections.observableArrayList(placeOptions));
    view.getCountryComboBox().setValue(null);
    view.getOk().setDisable(true);
  }

  public PlaceTroopsPaneView getView() {
    return view;
  }
}
