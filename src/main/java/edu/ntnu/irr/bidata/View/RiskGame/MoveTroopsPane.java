package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class MoveTroopsPane extends AbstractSidebarPane {
  private final ComboBox<Country> moveFromComboBox;
  private final ComboBox<Country> moveTargetComboBox;
  private final Spinner<Integer> amountOfTroopsSpinner;
  private final Button ok;
  private final Button dontMoveTroops;

  public MoveTroopsPane(Risk risk) {
    super(risk);
    getContainer().getStyleClass().add("move-troops-pane");
    this.moveFromComboBox = new ComboBox<>();
    this.moveTargetComboBox = new ComboBox<>();
    this.amountOfTroopsSpinner = new Spinner<>(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1)
    );
    SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory = (SpinnerValueFactory.IntegerSpinnerValueFactory) amountOfTroopsSpinner.getValueFactory();
    amountOfTroopsSpinner.setEditable(true);
    this.ok = new Button("Move & finish round");
    this.dontMoveTroops = new Button("Don't move troops");
    this.setText("Move");
    this.setLineSpacing(10);

    moveFromComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      boolean isFromDefined = newFrom != null;
      updateOnIsFromDefined(newFrom != null);
      if (isFromDefined) {
        moveTargetComboBox.setItems(FXCollections.observableArrayList(
            risk.getCountriesControlledByActivePlayer()
        ));
        amountOfTroopsSpinner.getValueFactory().setValue(1);
        spinnerValueFactory.setMax(newFrom.getArmies()-1);
      } else {
        moveTargetComboBox.getItems().clear();
      }
    });
    
    ok.setOnAction(event -> {
      Country from = moveFromComboBox.getValue();
      Country to = moveTargetComboBox.getValue();
      int amount = amountOfTroopsSpinner.getValue();
      if (from != null && to != null) {
        risk.transferTroops(from.getName(), to.getName(), amount);
        risk.endTurn();
        notifyObservers(this.getNextSidebarPane());
      }
    });

    dontMoveTroops.setOnAction(event -> {
      risk.endTurn();
      notifyObservers(this.getNextSidebarPane());
    });

    getContainer().getChildren().addAll(
        new Label("Move from"),
        moveFromComboBox,
        new Label("Move to"),
        moveTargetComboBox,
        amountOfTroopsSpinner,
        ok
        , dontMoveTroops
    );

    this.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
      if (isNowExpanded) update();
    });
  
    update();
  }

  private void updateOnIsFromDefined(boolean isFromDefined) {
    moveTargetComboBox.setVisible(isFromDefined);
    moveTargetComboBox.setManaged(isFromDefined);
    amountOfTroopsSpinner.setVisible(isFromDefined);
    ok.setVisible(isFromDefined);
  }

  private void update() {
    List<Country> moveFromOptions = risk.getCountriesCurrentPlayerCanMoveFrom();
    moveFromComboBox.setItems(FXCollections.observableArrayList(moveFromOptions));
    moveTargetComboBox.setValue(null);
    updateOnIsFromDefined(false);
    moveTargetComboBox.getItems().clear();
  }
}
