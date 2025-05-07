package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.Model.Dice;
import edu.ntnu.irr.bidata.Model.Die;
import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.View.LadderGameOverview.DieView;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.View.StartPage.StartPageController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class AttackPaneController extends AbstractSidebarPaneController implements IObserver<Pair<Dice, Dice>> {
  private final AttackPaneView view;

  public AttackPaneController(Risk risk) {
    super(risk);
    this.view = new AttackPaneView();
    risk.registerObserver(this);
    view.getCurrentUserLabel().setText("Current Player: " + risk.getCurrentPlayer().getName());

    ComboBox<Country> attackFromComboBox = view.getAttackFromComboBox();
    ComboBox<Country> attackTargetComboBox = view.getAttackTargetComboBox();

    view.getPerformAttackOnceButton().setOnAction(event -> {
      Country from = attackFromComboBox.getValue();
      Country to = attackTargetComboBox.getValue();
      if (from != null && to != null) {
        risk.attackOnce(from.getName(), to.getName());
        updateView();
      } else {
        PopUp.showError("Must select a county", "Please select a country to attack from and a country to attack to.");
      }
    });

    view.getPerformAttackUntilResultButton().setOnAction(event -> {
      Country from = attackFromComboBox.getValue();
      Country to = attackTargetComboBox.getValue();
      if (from != null && to != null) {
        risk.attackUntilResult(from.getName(), to.getName());
        updateView();
      } else {
        PopUp.showError("Must select a county", "Please select a country to attack from and a country to attack to.");
      }
    });

    view.getAttackFromComboBox().valueProperty().addListener((obs, oldFrom, newFrom) -> {
      boolean isFromDefined = newFrom != null;
      view.getAttackTargetComboBox().setDisable(newFrom == null);
      if (isFromDefined) {
        List<Country> attackTargetOptions = risk.getCountriesCurrentPlayerCanAttackFromCountry(newFrom);
        Collections.sort(attackTargetOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        attackTargetComboBox
            .setItems(FXCollections.observableArrayList(attackTargetOptions));
      } else {
        attackTargetComboBox.getItems().clear();
      }
    });


    view.getAttackTargetComboBox().valueProperty().addListener((obs, oldFrom, newFrom) -> {
      Button performAttackUntilResultButton = view.getPerformAttackUntilResultButton();
      Button performAttackOnceButton = view.getPerformAttackOnceButton();
      if (newFrom != null && attackFromComboBox.getValue() != null) {
        performAttackUntilResultButton.setDisable(false);
        performAttackOnceButton.setDisable(false);
      } else {
        performAttackUntilResultButton.setDisable(true);
        performAttackOnceButton.setDisable(true);
      }
    });

    view.getOk().setOnAction(event -> {
      notifyObservers(this.getNextSidebarPane());
    });

    view.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
      if (isNowExpanded) {
        updateView();
      }
    });

    updateView();
  }

  private void updateView() {

    List<Country> attackFromOptions = new ArrayList<>(risk.getCountriesCurrentPlayerCanAttackFrom());
    Collections.sort(attackFromOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));

    ComboBox<Country> attackFromComboBox = view.getAttackFromComboBox();
    ComboBox<Country> attackTargetComboBox = view.getAttackTargetComboBox();

    attackFromComboBox.setItems(FXCollections.observableArrayList(attackFromOptions));
    attackFromComboBox.setValue(null);
    attackTargetComboBox.setValue(null);
    view.getAttackTargetComboBox().setDisable(true);

    view.getPerformAttackUntilResultButton().setDisable(true);
    view.getPerformAttackOnceButton().setDisable(true);
    view.getCurrentUserLabel().setText("Current Player: " + risk.getCurrentPlayer().getName());

    attackTargetComboBox.getItems().clear();

    Country selectedFrom = view.getAttackFromComboBox().getValue();
    Country selectedTo = view.getAttackTargetComboBox().getValue();
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
    FlowPane dieBox = view.getDieBox();
    dieBox.getChildren().clear();
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

  public AttackPaneView getView() {
    return view;
  }
}
