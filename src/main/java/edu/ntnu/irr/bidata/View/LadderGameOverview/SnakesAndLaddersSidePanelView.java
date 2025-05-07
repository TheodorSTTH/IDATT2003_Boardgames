package edu.ntnu.irr.bidata.View.LadderGameOverview;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SnakesAndLaddersSidePanelView extends VBox {
  private final Label usernameLabel;
  Button rollButton = new Button("Roll");
  Button saveButton = new Button("Save current game");
  FlowPane diceBox = new FlowPane();
  Button exitGameButton = new Button("Exit game");

  public SnakesAndLaddersSidePanelView() {
    this.usernameLabel = new Label();
    this.usernameLabel.getStyleClass().addAll("fantasy", "title-3");
    this.setStyle(
        "-fx-padding: 20px;"
            + "-fx-background-radius: 20px;"
            + "-fx-border-width: 3;"
            + "-fx-border-radius: 8;"
            + "-fx-background-color: linear-gradient(to bottom, rgba(0, 0, 0, 0.44), rgba(0, 0, 0, 0.66));"
            + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 5, 0.5, 0, 2);");

    setSpacing(10);
    rollButton.getStyleClass().addAll("fantasy-button");
    saveButton.getStyleClass().addAll("fantasy-button");
    diceBox.setHgap(5);
    diceBox.setVgap(5);

    exitGameButton.getStyleClass().addAll("fantasy-button");

    Region splitterRegion = new Region();
    VBox.setVgrow(splitterRegion, Priority.ALWAYS);

    this.getChildren().addAll(
        usernameLabel,
        rollButton,
        diceBox,
        splitterRegion,
        exitGameButton,
        saveButton
    );
  }

  public Label getUsernameLabel() {
    return usernameLabel;
  }
  public Button getRollButton() {
    return rollButton;
  }
  public Button getSaveButton() {
    return saveButton;
  }
  public FlowPane getDiceBox() {
    return diceBox;
  }
  public Button getExitGameButton() {
    return exitGameButton;
  }
}
