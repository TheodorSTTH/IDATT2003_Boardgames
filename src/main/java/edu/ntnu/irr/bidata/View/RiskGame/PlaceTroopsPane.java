package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.View.PopUp;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class PlaceTroopsPane extends AbstractSidebarPane {
  private Label avalibuleTropesTextField;
  private final ComboBox<Country> countryComboBox;
  private final Spinner<Integer> amountOfTroopsSpinner;
  private final Button ok;

  public PlaceTroopsPane(Risk risk) {
    super(risk);
    this.setText("Place Troops");
    this.setLineSpacing(10);
    this.amountOfTroopsSpinner = new Spinner<>(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, risk.getTroopsAvailable(), 1)
    );
    this.amountOfTroopsSpinner.setEditable(true);
    this.countryComboBox = new ComboBox<>();
    this.ok = new Button("OK");
    avalibuleTropesTextField = new Label("You have " + Integer.toString(risk.getTroopsAvailable()) + " troops available");

    countryComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
        boolean isDefined = newFrom != null;
        ok.setVisible(isDefined);
    });

    ok.setOnAction(event -> {
        boolean placingTroopsWasSuccessful = risk.placeTroops(
            countryComboBox.getValue().getName(), 
            amountOfTroopsSpinner.getValue()
        );
      if (placingTroopsWasSuccessful)
        notifyObservers();
      else update();
    });

    getContainer().getChildren().addAll(
        new Label("Place troops on country"),
        countryComboBox,
            amountOfTroopsSpinner,
        ok,
        avalibuleTropesTextField
    );

    // Call update when the pane is expanded
    this.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
        if (isNowExpanded) update();
    });

    // Initial update for when it's first created
    update();

  }
  private void update() {
    List<Country> placeOptions = risk.getCountriesControlledByActivePlayer();
    avalibuleTropesTextField.setText("You have " + Integer.toString(risk.getTroopsAvailable()) + " troops available");
    countryComboBox.setItems(FXCollections.observableArrayList(placeOptions));
    countryComboBox.setValue(null);
    ((SpinnerValueFactory.IntegerSpinnerValueFactory) amountOfTroopsSpinner.getValueFactory())
        .setMax(risk.getTroopsAvailable());
    ok.setVisible(false);
  }

  


  
  
}
