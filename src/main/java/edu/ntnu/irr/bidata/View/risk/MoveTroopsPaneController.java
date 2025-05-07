package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.controller.NavigationManager;
import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.model.risk.Country;
import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.winningpage.WinningPageController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

public class MoveTroopsPaneController extends AbstractSidebarPaneController {
  private MoveTroopsPaneView view;

  public MoveTroopsPaneController(Risk risk) {
    super(risk);
    this.view = new MoveTroopsPaneView(risk.getCurrentPlayer().getName());

    ComboBox<Country> moveFromComboBox = view.getMoveFromComboBox();
    ComboBox<Country> moveTargetComboBox = view.getMoveTargetComboBox();
    Spinner<Integer> amountOfTroopsSpinner = view.getAmountOfTroopsSpinner();
    Button ok = view.getOk();

    view.getOk().setOnAction(event -> {
      Country from = moveFromComboBox.getValue();
      Country to = moveTargetComboBox.getValue();
      int amount = amountOfTroopsSpinner.getValue();
      if (from != null && to != null) {
        risk.transferTroops(from.getName(), to.getName(), amount);
        risk.endTurn();
        Player currentPlayer = risk.getCurrentPlayer();
        if (risk.getBoard().hasWon(currentPlayer)) {
          FileHandler.deleteGame(risk.getGameName());
          NavigationManager.navigate(new WinningPageController(currentPlayer.getName(), "snakes-and-ladders-win-page").getView());
        }
        notifyObservers(this.getNextSidebarPane());
      } else {
        PopUp.showError("Invalid Selections", "Please select a country to move from and a country to move to.");
      }
    });

    moveFromComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      boolean isFromDefined = newFrom != null;
      if (isFromDefined) {
        amountOfTroopsSpinner.getValueFactory().setValue(1);
        view.getSpinnerValueFactory().setMax(newFrom.getArmies()-1);
      }
    });

    view.getDontMoveTroops().setOnAction(event -> {
      Player currentPlayer = risk.getCurrentPlayer();
      risk.endTurn();
      if (risk.getBoard().hasWon(currentPlayer)) {
        FileHandler.deleteGame(risk.getGameName());
        NavigationManager.navigate(new WinningPageController(currentPlayer.getName(), "risk-win-page").getView());
      }
      notifyObservers(this.getNextSidebarPane());
    });

    view.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
      if (isNowExpanded) {
        update();
      }
    });

    moveFromComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      ok.setDisable(newFrom == null || moveTargetComboBox.getValue() == null);
    });

    moveTargetComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      ok.setDisable(newFrom == null || moveFromComboBox.getValue() == null);
    });

    update();
  }

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
  }

  public MoveTroopsPaneView getView() {
    return view;
  }
}
