package edu.ntnu.irr.bidata.view.RiskGame;

import edu.ntnu.irr.bidata.model.Dice;
import edu.ntnu.irr.bidata.model.Die;
import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.risk.Country;
import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.LadderGameOverview.DieView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class AttackPane extends AbstractSidebarPane implements Observer<Pair<Dice, Dice>> {
  private final ComboBox<Country> attackFromComboBox;
  private final ComboBox<Country> attackTargetComboBox;
  private final Button performAttackOnceButton;
  private final Button performAttackUntilResultButton;
  private final Button ok;
  private final FlowPane dieBox;
  private Label currentUserLabel;

  public AttackPane(Risk risk) {
    super(risk);
    getContainer().getStyleClass().add("attack-pane");
    risk.registerObserver(this);
    this.dieBox = new FlowPane();
    dieBox.setHgap(12);
    dieBox.setVgap(12);
    VBox.setMargin(dieBox, new javafx.geometry.Insets(0, 0, 0, 50));


    currentUserLabel = new Label("Current Player: " + risk.getCurrentPlayer().getName());
    this.currentUserLabel.getStyleClass().add("fantasy-title-sidbar");
    VBox.setMargin(currentUserLabel, new javafx.geometry.Insets(0, 0, 0, 10));


    this.setText("Attack");
    this.setLineSpacing(10);

    Label attackFromLabel = new Label("Attack from:");
    attackFromLabel.getStyleClass().add("fantasy-text-sidbar");
    VBox.setMargin(attackFromLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    this.attackFromComboBox = new ComboBox<>();
    this.attackFromComboBox.getStyleClass().add("fantasy-combo-box-sidbar");
    attackFromComboBox.setPromptText("Select country to attack from");
    VBox.setMargin(attackFromComboBox, new javafx.geometry.Insets(0, 0, 0, 10));

    Label attackToLabel = new Label("Attack to:");
    attackToLabel.getStyleClass().add("fantasy-text-sidbar");
    VBox.setMargin(attackToLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    this.attackTargetComboBox = new ComboBox<>();
    this.attackTargetComboBox.getStyleClass().add("fantasy-combo-box-sidbar");
    attackTargetComboBox.setPromptText("Select country to attack to");
    VBox.setMargin(attackTargetComboBox, new javafx.geometry.Insets(0, 0, 10, 10));

    this.performAttackOnceButton = new Button("Perform attack once");
    this.performAttackOnceButton.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(performAttackOnceButton, new javafx.geometry.Insets(0, 0, 10, 10));

    performAttackOnceButton.setOnAction(event -> {
      Country from = attackFromComboBox.getValue();
      Country to = attackTargetComboBox.getValue();
      if (from != null && to != null) {
        risk.attackOnce(from.getName(), to.getName());
        updateMap();
      } else {
        PopUp.showError("Must select a county", "Please select a country to attack from and a country to attack to.");
      }
    });

    this.performAttackUntilResultButton = new Button("Perform until result");
    this.performAttackUntilResultButton.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(performAttackUntilResultButton, new javafx.geometry.Insets(0, 0, 10, 10));

    performAttackUntilResultButton.setOnAction(event -> {
      Country from = attackFromComboBox.getValue();
      Country to = attackTargetComboBox.getValue();
      if (from != null && to != null) {
        risk.attackUntilResult(from.getName(), to.getName());
        updateMap();
      } else {
        PopUp.showError("Must select a county", "Please select a country to attack from and a country to attack to.");
      }
    });

    this.ok = new Button("OK, i am done");
    this.ok.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(ok, new javafx.geometry.Insets(0, 0, 10, 10));

    attackFromComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      boolean isFromDefined = newFrom != null;
      updateOnIsFromDefined(newFrom != null);
      if (isFromDefined) {
        List<Country> attackTargetOptions = risk.getCountriesCurrentPlayerCanAttackFromCountry(newFrom);
        Collections.sort(attackTargetOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        attackTargetComboBox
            .setItems(FXCollections.observableArrayList(attackTargetOptions));
      } else {
        attackTargetComboBox.getItems().clear();
      }
    });


    attackTargetComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      if (newFrom != null && attackFromComboBox.getValue() != null) {
        performAttackUntilResultButton.setDisable(false);
        performAttackOnceButton.setDisable(false);
      } else {
        performAttackUntilResultButton.setDisable(true);
        performAttackOnceButton.setDisable(true);
      }
    });

    ok.setOnAction(event -> {
      notifyObservers(this.getNextSidebarPane());
    });

    getContainer().getChildren().addAll(
        currentUserLabel,
        attackFromLabel,
        attackFromComboBox,
        attackToLabel,
        attackTargetComboBox,
        performAttackOnceButton,
        performAttackUntilResultButton,
        ok,
        dieBox);

    this.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
      if (isNowExpanded)
        updateMap();
    });

    updateMap();
  }
  
  private void updateOnIsFromDefined(boolean isFromDefined) {
    attackTargetComboBox.setDisable(!isFromDefined);
  }
  

  private void updateMap() {
    Country selectedFrom = attackFromComboBox.getValue();
    Country selectedTo = attackTargetComboBox.getValue();
    
    List<Country> attackFromOptions = new ArrayList<>(risk.getCountriesCurrentPlayerCanAttackFrom());
    Collections.sort(attackFromOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));
    attackFromComboBox.setItems(FXCollections.observableArrayList(attackFromOptions));
    attackFromComboBox.setValue(null);
    attackTargetComboBox.setValue(null);
    updateOnIsFromDefined(false);
    performAttackUntilResultButton.setDisable(true);
    performAttackOnceButton.setDisable(true);
    this.currentUserLabel.setText("Current Player: " + risk.getCurrentPlayer().getName());

    attackTargetComboBox.getItems().clear();

    if (attackFromOptions.contains(selectedFrom) && risk.getCountriesCurrentPlayerCanAttackFromCountry(selectedFrom).contains(selectedTo)) {
      attackFromComboBox.setValue(selectedFrom);
      attackTargetComboBox.setItems(FXCollections.observableArrayList(risk.getCountriesCurrentPlayerCanAttackFromCountry(selectedFrom)));
      attackTargetComboBox.setValue(selectedTo);
    } 
  }

  /**
   * Is responsible for updating dice view
   * */
  public void update(Pair<Dice, Dice> dicePair) {
    this.dieBox.getChildren().clear();
    for (Die die : dicePair.getKey().getDice()) { // attack
      if (die.getWasRolledPreviousRound()) {
        DieView newDieView = new DieView(40, Color.BLACK, Color.WHITE);
        newDieView.update(die.getPreviousDieRoll());
        dieBox.getChildren().add(newDieView);
      }
    }
    for (Die die : dicePair.getValue().getDice()) { // defence
      if (die.getWasRolledPreviousRound()) {
        DieView newDieView = new DieView(40, Color.RED, Color.WHITE);
        newDieView.update(die.getPreviousDieRoll());
        dieBox.getChildren().add(newDieView);
      }
    }
  }
}
