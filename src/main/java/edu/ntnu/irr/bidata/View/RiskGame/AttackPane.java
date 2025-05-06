package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Dice;
import edu.ntnu.irr.bidata.Model.Die;
import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.View.LadderGameOverview.DieView;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class AttackPane extends AbstractSidebarPane implements IObserver<Pair<Dice, Dice>> {
  private final ComboBox<Country> attackFromComboBox;
  private final ComboBox<Country> attackTargetComboBox;
  private final Button performAttackOnceButton;
  private final Button performAttackUntilResultButton;
  private final Button ok;
  private final FlowPane dieBox;

  public AttackPane(Risk risk) {
    super(risk);
    getContainer().getStyleClass().add("attack-pane");
    risk.registerObserver(this);
    this.dieBox = new FlowPane();
    dieBox.setHgap(10);
    dieBox.setVgap(10);
    this.attackFromComboBox = new ComboBox<>();
    this.attackTargetComboBox = new ComboBox<>();
    this.performAttackOnceButton = new Button("Perform attack once");
    this.performAttackUntilResultButton = new Button("Perform until result");
    this.ok = new Button("OK, i am done");
    this.setText("Attack");
    this.setLineSpacing(10);

    attackFromComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      boolean isFromDefined = newFrom != null;
      updateOnIsFromDefined(newFrom != null);
      if (isFromDefined) {
        attackTargetComboBox.setItems(FXCollections.observableArrayList(risk.getCountriesCurrentPlayerCanAttackFromCountry(newFrom)));
      } else {
        attackTargetComboBox.getItems().clear();
      }
    });
    
    performAttackOnceButton.setOnAction(event -> {
      Country from = attackFromComboBox.getValue();
      Country to = attackTargetComboBox.getValue();
      if (from != null && to != null) {
        risk.attackOnce(from.getName(), to.getName());
        updateMap();
      }
    });

    performAttackUntilResultButton.setOnAction(event -> {
      Country from = attackFromComboBox.getValue();
      Country to = attackTargetComboBox.getValue();
      if (from != null && to != null) {
        risk.attackUntilResult(from.getName(), to.getName());
        updateMap();
      }
    });

    ok.setOnAction(event -> {
      notifyObservers(this.getNextSidebarPane());
    });

    getContainer().getChildren().addAll(
        new Label("Attack from"),
        attackFromComboBox,
        new Label("Attack country"),
        attackTargetComboBox,
        performAttackOnceButton,
        performAttackUntilResultButton,
        dieBox,
        ok
    );

    this.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
      if (isNowExpanded) updateMap();
    });

    updateMap();
  }

  private void updateOnIsFromDefined(boolean isFromDefined) {
    attackTargetComboBox.setVisible(isFromDefined);
    attackTargetComboBox.setManaged(isFromDefined);
    performAttackUntilResultButton.setVisible(isFromDefined);
    performAttackOnceButton.setVisible(isFromDefined);
  }

  private void updateMap() {
    Country selectedFrom = attackFromComboBox.getValue();
    Country selectedTo = attackTargetComboBox.getValue();
    
    List<Country> attackFromOptions = risk.getCountriesCurrentPlayerCanAttackFrom();
    attackFromComboBox.setItems(FXCollections.observableArrayList(attackFromOptions));
    attackFromComboBox.setValue(null);
    attackTargetComboBox.setValue(null);
    updateOnIsFromDefined(false);
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
