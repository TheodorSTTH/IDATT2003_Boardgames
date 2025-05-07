package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.model.risk.Country;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;

public class PlaceTroopsPaneView extends AbstractSidebarPaneView{
  private final Label infoLabel = new Label();
  private final ComboBox<Country> countryComboBox;
  private final Spinner<Integer> amountOfTroopsSpinner;
  private final Button ok = new Button("Place troops");
  private final Label currentUserLabel = new Label();

  public PlaceTroopsPaneView() {
    getContainer().getStyleClass().add("place-troops-pane");
    this.setText("Place Troops");
    this.setLineSpacing(10);

    this.currentUserLabel.getStyleClass().add("fantasy-title-sidbar");
    VBox.setMargin(currentUserLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    infoLabel.getStyleClass().add("fantasy-text-sidbar");
    VBox.setMargin(infoLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    this.countryComboBox = new ComboBox<>();
    this.countryComboBox.getStyleClass().add("fantasy-combo-box-sidbar");
    VBox.setMargin(countryComboBox, new javafx.geometry.Insets(0, 5, 10, 10));

    this.amountOfTroopsSpinner = new Spinner<>(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1)
    );
    this.amountOfTroopsSpinner.getStyleClass().add("fantasy-spinner-sidbar");
    this.amountOfTroopsSpinner.setEditable(true);
    VBox.setMargin(amountOfTroopsSpinner, new javafx.geometry.Insets(10, 5, 10, 10));

    this.ok.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(ok, new javafx.geometry.Insets(0, 5, 10, 10));

    getContainer().getChildren().addAll(
        currentUserLabel,
        infoLabel,
        countryComboBox,
        amountOfTroopsSpinner,
        ok
    );
  }

  public Label getInfoLabel() {
    return infoLabel;
  }

  public ComboBox<Country> getCountryComboBox() {
    return countryComboBox;
  }

  public Spinner<Integer> getAmountOfTroopsSpinner() {
    return amountOfTroopsSpinner;
  }

  public Button getOk() {
    return ok;
  }

  public Label getCurrentUserLabel() {
    return currentUserLabel;
  }
}
