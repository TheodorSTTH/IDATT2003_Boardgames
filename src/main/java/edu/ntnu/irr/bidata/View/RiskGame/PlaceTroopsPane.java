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
import javafx.scene.layout.VBox;

public class PlaceTroopsPane extends AbstractSidebarPane {
  private Label infoLabel;
  private final ComboBox<Country> countryComboBox;
  private final Spinner<Integer> amountOfTroopsSpinner;
  private final Button ok;

  public PlaceTroopsPane(Risk risk) {
    super(risk);
    getContainer().getStyleClass().add("place-troops-pane");
    this.setText("Place Troops");
    this.setLineSpacing(10);


    this.infoLabel = new Label("Place troops on country\n"+"You have " + Integer.toString(risk.getTroopsAvailable()) + " troops available");
    infoLabel.getStyleClass().add("fantasy-text-sidbar");
    VBox.setMargin(infoLabel, new javafx.geometry.Insets(0, 0, 0, 10));


    this.countryComboBox = new ComboBox<>();
    this.countryComboBox.getStyleClass().add("fantasy-combo-box-sidbar");
    VBox.setMargin(countryComboBox, new javafx.geometry.Insets(5, 5, 10, 10));


    this.amountOfTroopsSpinner = new Spinner<>(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, risk.getTroopsAvailable(), 1)
    );
    this.amountOfTroopsSpinner.getStyleClass().add("fantasy-spinner-sidbar");
    this.amountOfTroopsSpinner.setEditable(true);
    VBox.setMargin(amountOfTroopsSpinner, new javafx.geometry.Insets(10, 5, 10, 10));
    
    
    this.ok = new Button("OK");
    this.ok.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(ok, new javafx.geometry.Insets(10, 5, 10, 10));


    ok.setOnAction(event -> {
      if (countryComboBox.getValue() == null) {
        PopUp.showError("Must select a county","Please select a country to place troops on.");
      } else {
        boolean placingTroopsWasSuccessful = risk.placeTroops(
            countryComboBox.getValue().getName(),
            amountOfTroopsSpinner.getValue());
        if (placingTroopsWasSuccessful)
          notifyObservers(this.getNextSidebarPane());
        else
          update();
      }
    });

    getContainer().getChildren().addAll(
        infoLabel,
        countryComboBox,
        amountOfTroopsSpinner,
        ok
    );

    // Call update when the pane is expanded
    this.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
      if (isNowExpanded) {
        update();
      }
    });

    // Initial update for when it's first created
    update();

  }

  private void update() {
    List<Country> placeOptions = risk.getCountriesControlledByActivePlayer();
    this.infoLabel.setText("Place troops on country\n"+"You have " + Integer.toString(risk.getTroopsAvailable()) + " troops available");
    countryComboBox.setItems(FXCollections.observableArrayList(placeOptions));
    countryComboBox.setValue(null);
    ((SpinnerValueFactory.IntegerSpinnerValueFactory) amountOfTroopsSpinner.getValueFactory())
        .setMax(risk.getTroopsAvailable());
  }
}
