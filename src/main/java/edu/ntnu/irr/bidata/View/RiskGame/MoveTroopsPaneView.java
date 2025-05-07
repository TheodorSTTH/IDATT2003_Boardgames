package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.Model.FileHandler;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.View.WinPage.WinningPageController;
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

public class MoveTroopsPaneView extends AbstractSidebarPaneView {
  private final ComboBox<Country> moveFromComboBox;
  private final ComboBox<Country> moveTargetComboBox;
  private final Label moveToLabel;
  private final Spinner<Integer> amountOfTroopsSpinner;
  private final Button ok;
  private final Button dontMoveTroops;
  private final Label currentUserLabel;

  public MoveTroopsPaneView(String currentPlayerName) {
    getContainer().getStyleClass().add("move-troops-pane");

    currentUserLabel = new Label("Current Player: " + currentPlayerName);
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
    amountOfTroopsSpinner.setEditable(true);
    VBox.setMargin(amountOfTroopsSpinner, new javafx.geometry.Insets(0, 0, 10, 10));


    this.ok = new Button("Move & finish round");
    this.ok.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(ok, new javafx.geometry.Insets(0, 0, 10, 10));

    this.dontMoveTroops = new Button("Don't move troops");
    this.setText("Move");
    this.dontMoveTroops.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(dontMoveTroops, new javafx.geometry.Insets(0, 0, 10, 10));
    this.setLineSpacing(10);

    getContainer().getChildren().addAll(
        currentUserLabel,
        moveFromLabel,
        moveFromComboBox,
        moveToLabel,
        moveTargetComboBox,
        amountOfTroopsSpinner,
        ok,
        dontMoveTroops
    );
  }

  public Button getDontMoveTroops() {
    return dontMoveTroops;
  }

  public Label getMoveToLabel() {
    return moveToLabel;
  }

  public ComboBox<Country> getMoveFromComboBox() {
    return moveFromComboBox;
  }

  public ComboBox<Country> getMoveTargetComboBox() {
    return moveTargetComboBox;
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
