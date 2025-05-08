package edu.ntnu.irr.bidata.controller.risk;

import edu.ntnu.irr.bidata.model.Dice;
import edu.ntnu.irr.bidata.model.Die;
import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.risk.Country;
import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.view.DieView;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.risk.AttackPaneView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * Controller class for managing the Attack Pane in the Risk game. Handles user interactions with
 * the attack phase of the game. Implements the Observer pattern to update the view when dice are
 * rolled.
 */
public class AttackPaneController extends AbstractSidebarPaneController
    implements Observer<Pair<Dice, Dice>> {

  private final AttackPaneView view;

  /**
   * Constructor for the AttackPaneController. Initializes the view and binds actions to the
   * corresponding events.
   *
   * @param risk The Risk game instance.
   */
  public AttackPaneController(Risk risk) {
    super(risk);
    this.view = new AttackPaneView();
    risk.registerObserver(this);

    // Set current player in the view
    view.getCurrentUserLabel().setText("Current Player: " + risk.getCurrentPlayer().getName());

    // Get the ComboBox elements for selecting attack countries
    ComboBox<Country> attackFromComboBox = view.getAttackFromComboBox();
    ComboBox<Country> attackTargetComboBox = view.getAttackTargetComboBox();

    // Action for the "Perform Attack Once" button
    view.getPerformAttackOnceButton()
        .setOnAction(
            event -> {
              Country from = attackFromComboBox.getValue();
              Country to = attackTargetComboBox.getValue();
              if (from != null && to != null) {
                risk.attackOnce(from.getName(), to.getName());
                updateView();
              } else {
                PopUp.showError(
                    "Must select a country",
                    "Please select a country to attack from and a country to attack to.");
              }
            });

    // Action for the "Perform Attack Until Result" button
    view.getPerformAttackUntilResultButton()
        .setOnAction(
            event -> {
              Country from = attackFromComboBox.getValue();
              Country to = attackTargetComboBox.getValue();
              if (from != null && to != null) {
                risk.attackUntilResult(from.getName(), to.getName());
                updateView();
              } else {
                PopUp.showError(
                    "Must select a country",
                    "Please select a country to attack from and a country to attack to.");
              }
            });

    // Update the available targets when the "Attack From" country changes
    view.getAttackFromComboBox()
        .valueProperty()
        .addListener(
            (obs, oldFrom, newFrom) -> {
              boolean isFromDefined = newFrom != null;
              view.getAttackTargetComboBox().setDisable(newFrom == null);
              if (isFromDefined) {
                List<Country> attackTargetOptions =
                    risk.getCountriesCurrentPlayerCanAttackFromCountry(newFrom);
                Collections.sort(
                    attackTargetOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));
                attackTargetComboBox.setItems(
                    FXCollections.observableArrayList(attackTargetOptions));
              } else {
                attackTargetComboBox.getItems().clear();
              }
            });

    // Enable or disable attack buttons based on selections
    view.getAttackTargetComboBox()
        .valueProperty()
        .addListener(
            (obs, oldFrom, newFrom) -> {
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

    // Action for the "OK" button to proceed to the next sidebar pane
    view.getOk()
        .setOnAction(
            event -> {
              notifyObservers(this.getNextSidebarPane());
            });

    // Update the view when the attack pane is expanded
    view.expandedProperty()
        .addListener(
            (obs, wasExpanded, isNowExpanded) -> {
              if (isNowExpanded) {
                updateView();
              }
            });

    updateView();
  }

  /**
   * Updates the view based on the current state of the Risk game. This includes setting the
   * available attack options and resetting the selections.
   */
  private void updateView() {

    // Disable the attack buttons until both countries are selected
    view.getPerformAttackUntilResultButton().setDisable(true);
    view.getPerformAttackOnceButton().setDisable(true);
    view.getCurrentUserLabel().setText("Current Player: " + risk.getCurrentPlayer().getName());

    // Get the available countries for the current player to attack from
    List<Country> attackFromOptions =
        new ArrayList<>(risk.getCountriesCurrentPlayerCanAttackFrom());
    Collections.sort(attackFromOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));

    ComboBox<Country> attackFromComboBox = view.getAttackFromComboBox();
    ComboBox<Country> attackTargetComboBox = view.getAttackTargetComboBox();

    Country selectedFrom = attackFromComboBox.getValue();
    Country selectedTo = attackTargetComboBox.getValue();

    // Set the ComboBox options for "Attack From"
    attackFromComboBox.setItems(FXCollections.observableArrayList(attackFromOptions));
    attackTargetComboBox.getItems().clear();

    // If the old selections are still valid, set them back to the ComboBoxes
    if (!attackFromOptions.contains(selectedFrom)){
      attackTargetComboBox.setValue(null);
      attackTargetComboBox.setDisable(true);
    } else if (!risk.getCountriesCurrentPlayerCanAttackFromCountry(selectedFrom).contains(selectedTo)){
      attackTargetComboBox.setValue(null);
      attackTargetComboBox.setItems(
        FXCollections.observableArrayList(
            risk.getCountriesCurrentPlayerCanAttackFromCountry(selectedFrom)));
    } else {
      attackFromComboBox.setValue(selectedFrom);
      attackTargetComboBox.setItems(
          FXCollections.observableArrayList(
              risk.getCountriesCurrentPlayerCanAttackFromCountry(selectedFrom)));
      attackTargetComboBox.setValue(selectedTo);

    }
  }

  /**
   * Updates the dice view when dice are rolled during the attack phase.
   *
   * @param dicePair The pair of Dice objects (attack and defense dice).
   */
  public void update(Pair<Dice, Dice> dicePair) {
    FlowPane dieBox = view.getDieBox();
    dieBox.getChildren().clear();

    // Display attack dice
    for (Die die : dicePair.getKey().getDice()) { // attack
      if (die.getWasRolledPreviousRound()) {
        DieView newDieView = new DieView(40, Color.BLACK, Color.WHITE);
        newDieView.update(die.getPreviousDieRoll());
        dieBox.getChildren().add(newDieView);
      }
    }

    // Display defense dice
    for (Die die : dicePair.getValue().getDice()) { // defense
      if (die.getWasRolledPreviousRound()) {
        DieView newDieView = new DieView(40, Color.RED, Color.WHITE);
        newDieView.update(die.getPreviousDieRoll());
        dieBox.getChildren().add(newDieView);
      }
    }
  }

  /**
   * Returns the AttackPaneView associated with this controller.
   *
   * @return The AttackPaneView.
   */
  public AttackPaneView getView() {
    return view;
  }
}
