package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.View.WinPage.WinningPage;
import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.model.Risk.Country;
import edu.ntnu.irr.bidata.model.Risk.Risk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;

public class MoveTroopsPane extends AbstractSidebarPane {
  private final ComboBox<Country> moveFromComboBox;
  private final ComboBox<Country> moveTargetComboBox;
  private final Label moveToLabel;
  private final Spinner<Integer> amountOfTroopsSpinner;
  private final Button ok;
  private final Button dontMoveTroops;
  private Label currentUserLabel;


  public MoveTroopsPane(Risk risk) {
    super(risk);
    getContainer().getStyleClass().add("move-troops-pane");


    currentUserLabel = new Label("Current Player: " + risk.getCurrentPlayer().getName());
    this.currentUserLabel.getStyleClass().add("fantasy-title-sidbar");
    VBox.setMargin(currentUserLabel, new javafx.geometry.Insets(0, 0, 0, 10));


    Label moveFromLabel = new Label("Move from:");
    moveFromLabel.getStyleClass().add("fantasy-text-sidbar");
    VBox.setMargin(moveFromLabel, new javafx.geometry.Insets(0, 0, 0, 10));


    this.moveFromComboBox = new ComboBox<>();
    this.moveFromComboBox.getStyleClass().add("fantasy-combo-box-sidbar");
    moveFromComboBox.setPromptText("Select country to move from");
    VBox.setMargin(moveFromComboBox, new javafx.geometry.Insets(0, 0, 0, 10));


    moveToLabel = new Label("Move to:");
    moveToLabel.getStyleClass().add("fantasy-text-sidbar");
    VBox.setMargin(moveToLabel, new javafx.geometry.Insets(0, 0, 0, 10));


    this.moveTargetComboBox = new ComboBox<>();
    this.moveTargetComboBox.getStyleClass().add("fantasy-combo-box-sidbar");
    moveTargetComboBox.setPromptText("Select country to move to");
    VBox.setMargin(moveTargetComboBox, new javafx.geometry.Insets(0, 0, 10, 10));


    this.amountOfTroopsSpinner = new Spinner<>(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1)
    );
    this.amountOfTroopsSpinner.getStyleClass().add("fantasy-spinner-sidbar");
    SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory = (SpinnerValueFactory.IntegerSpinnerValueFactory) amountOfTroopsSpinner.getValueFactory();
    amountOfTroopsSpinner.setEditable(true);
    VBox.setMargin(amountOfTroopsSpinner, new javafx.geometry.Insets(0, 0, 10, 10));
    
    
    this.ok = new Button("Move & finish round");
    this.ok.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(ok, new javafx.geometry.Insets(0, 0, 10, 10));


    ok.setOnAction(event -> {
      Country from = moveFromComboBox.getValue();
      Country to = moveTargetComboBox.getValue();
      int amount = amountOfTroopsSpinner.getValue();
      if (from != null && to != null) {
        risk.transferTroops(from.getName(), to.getName(), amount);
        risk.endTurn();
        Player currentPlayer = risk.getCurrentPlayer();
        if (risk.getBoard().hasWon(currentPlayer)) { // TODO: Move code out of a controller somehow
          FileHandler.deleteGame(risk.getGameName());
          NavigationManager.navigate(new WinningPage(currentPlayer.getName(), "snakes-and-ladders-win-page"));
        }
        notifyObservers(this.getNextSidebarPane());
      } else {
        PopUp.showError("Invalid Selections","Please select a country to move from and a country to move to.");
      }
    });


    this.dontMoveTroops = new Button("Don't move troops");
    this.setText("Move");
    this.dontMoveTroops.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(dontMoveTroops, new javafx.geometry.Insets(0, 0, 10, 10));
    
    
    this.setLineSpacing(10);


    moveFromComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      boolean isFromDefined = newFrom != null;
      if (isFromDefined) {
        amountOfTroopsSpinner.getValueFactory().setValue(1);
        spinnerValueFactory.setMax(newFrom.getArmies()-1);
      }
    });
  

    dontMoveTroops.setOnAction(event -> {
      Player currentPlayer = risk.getCurrentPlayer();
      risk.endTurn();
      if (risk.getBoard().hasWon(currentPlayer)) { // TODO: Move code out of a controller somehow
        FileHandler.deleteGame(risk.getGameName());
        NavigationManager.navigate(new WinningPage(currentPlayer.getName(), "risk-win-page"));
      }
      notifyObservers(this.getNextSidebarPane());
    });

    getContainer().getChildren().addAll(
        currentUserLabel,
        moveFromLabel,
        moveFromComboBox,
        moveToLabel,
        moveTargetComboBox,
        amountOfTroopsSpinner,
        ok
        , dontMoveTroops
    );

    this.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
      if (isNowExpanded) update();
    });

    moveFromComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      if (newFrom != null && moveTargetComboBox.getValue() != null) {
        ok.setDisable(false);
      } else {
        ok.setDisable(true);
      }
    });

    moveTargetComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      if (newFrom != null && moveFromComboBox.getValue() != null) {
        ok.setDisable(false);
      } else {
        ok.setDisable(true);
      }
    });
  
    update();
  }

  private void update() {
    List<Country> moveFromOptions = new ArrayList<>(risk.getCountriesCurrentPlayerCanMoveFrom());
    Collections.sort(moveFromOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));
    ok.setDisable(true);
    moveFromComboBox.setItems(FXCollections.observableArrayList(moveFromOptions));
    List<Country> moveToOptions = risk.getCountriesControlledByActivePlayer();
    Collections.sort(moveToOptions, (c1, c2) -> c1.getName().compareTo(c2.getName()));
    moveTargetComboBox.setItems(FXCollections.observableArrayList(moveToOptions));
    moveTargetComboBox.setValue(null);
    this.currentUserLabel.setText("Current Player: " + risk.getCurrentPlayer().getName());
  }
}
