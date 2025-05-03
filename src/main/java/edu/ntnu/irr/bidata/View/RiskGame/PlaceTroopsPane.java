package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.View.PopUp;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PlaceTroopsPane extends AbstractSidebarPane {
  private Label avalibuleTropesTextField;
  private final ComboBox<Country> countryComboBox;
  private final Button ok;

  public PlaceTroopsPane(Risk risk) {
    super(risk);
    this.setText("Place Troops");
    this.setLineSpacing(10);
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
            risk.getTroopsAvailable()
        );
        if (placingTroopsWasSuccessful) notifyObservers();
        else PopUp.showInfo("Something went wrong", "Adding troops on a square failed");
    });

    getContainer().getChildren().addAll(
        new Label("Place troops on country"),
        countryComboBox,
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
    ok.setVisible(false);
  }

  


  
  
}
