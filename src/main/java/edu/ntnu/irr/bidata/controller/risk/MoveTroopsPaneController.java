package edu.ntnu.irr.bidata.controller.risk;

import edu.ntnu.irr.bidata.NavigationManager;
import edu.ntnu.irr.bidata.controller.WinningPageController;
import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.model.risk.Country;
import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.risk.MoveTroopsPaneView;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

/**
 * Controller responsible for managing the move troops phase in the Risk game. This controller
 * allows the player to transfer armies between their own connected territories, or to skip the
 * transfer phase and end their turn. It also handles checking win conditions and navigating to the
 * win screen if applicable.
 */
public class MoveTroopsPaneController extends AbstractSidebarPaneController {

  private MoveTroopsPaneView view;

  /**
   * Constructs a MoveTroopsPaneController and sets up all interaction logic.
   *
   * @param risk the Risk game model instance
   */
  public MoveTroopsPaneController(Risk risk) {
    super(risk);
    this.view = new MoveTroopsPaneView(risk.getCurrentPlayer().getName());

    Spinner<Integer> amountOfTroopsSpinner = view.getAmountOfTroopsSpinner();
    ComboBox<Country> moveFromComboBox = view.getMoveFromComboBox();
    ComboBox<Country> moveTargetComboBox = view.getMoveTargetComboBox();
    Button ok = view.getOk();

    // Confirm troop transfer
    view.getOk()
        .setOnAction(
            event -> {
              Country from = moveFromComboBox.getValue();
              Country to = moveTargetComboBox.getValue();
              int amount = amountOfTroopsSpinner.getValue();

              if (from != null && to != null) {
                risk.transferTroops(from.getName(), to.getName(), amount);
                risk.endTurn();

                Player currentPlayer = risk.getCurrentPlayer();
                if (risk.getBoard().hasWon(currentPlayer)) {
                  win();
                }

                notifyObservers(this.getNextSidebarPane());
              } else {
                PopUp.showError(
                    "Invalid Selections",
                    "Please select a country to move from and a country to move to.");
              }
            });

    // Enable or disable spinner based on selected "from" country
    moveFromComboBox
        .valueProperty()
        .addListener(
            (obs, oldFrom, newFrom) -> {
              if (newFrom != null) {
                amountOfTroopsSpinner.setDisable(false);
                view.getSpinnerValueFactory().setMax(newFrom.getArmies() - 1);
              } else {
                amountOfTroopsSpinner.setDisable(true);
              }
            });

    // Handle "Don't Move Troops" button
    view.getDontMoveTroops()
        .setOnAction(
            event -> {
              Player currentPlayer = risk.getCurrentPlayer();
              risk.endTurn();
              if (risk.getBoard().hasWon(currentPlayer)) {
                win();
              }
              notifyObservers(this.getNextSidebarPane());
            });

    // Update when pane is expanded
    view.expandedProperty()
        .addListener(
            (obs, wasExpanded, isNowExpanded) -> {
              if (isNowExpanded) {
                update();
              }
            });

    // Enable OK button only when both combo boxes have values
    moveFromComboBox
        .valueProperty()
        .addListener(
            (obs, oldFrom, newFrom) -> {
              ok.setDisable(newFrom == null || moveTargetComboBox.getValue() == null);
            });

    moveTargetComboBox
        .valueProperty()
        .addListener(
            (obs, oldFrom, newFrom) -> {
              ok.setDisable(newFrom == null || moveFromComboBox.getValue() == null);
            });

    update(); // Initial state setup
  }

  private void win() {
    try {
      FileHandler.deleteGame(risk.getGameName());
    } catch (UncheckedIOException e) {
      PopUp.showError("Something went wrong deleting game", e.getMessage());
    }
    NavigationManager.navigate(
        new WinningPageController(this.risk.getCurrentPlayer().getName(), "risk-win-page")
            .getView());
  }

  /**
   * Updates the move troops view with current valid options. Populates combo boxes, resets
   * selections, disables controls, and updates the player's name label.
   */
  private void update() {
    List<Country> moveFromOptions = new ArrayList<>(risk.getCountriesCurrentPlayerCanMoveFrom());
    Collections.sort(moveFromOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));
    view.getOk().setDisable(true);
    view.getMoveFromComboBox().setItems(FXCollections.observableArrayList(moveFromOptions));

    List<Country> moveToOptions = risk.getCountriesControlledByActivePlayer();
    Collections.sort(moveToOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));
    view.getMoveTargetComboBox().setItems(FXCollections.observableArrayList(moveToOptions));
    view.getMoveTargetComboBox().setValue(null);
    view.getCurrentUserLabel().setText("Current Player: " + risk.getCurrentPlayer().getName());
    view.getAmountOfTroopsSpinner().setDisable(true);
  }

  /**
   * Gets the view associated with this controller.
   *
   * @return the MoveTroopsPaneView managed by this controller
   */
  public MoveTroopsPaneView getView() {
    return view;
  }
}
