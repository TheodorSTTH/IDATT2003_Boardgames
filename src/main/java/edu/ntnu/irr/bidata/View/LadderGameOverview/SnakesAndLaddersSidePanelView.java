package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.Die;
import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.View.PopUp;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SnakesAndLaddersSidePanelView extends VBox implements IObserver<LaderGame> {
  private final Label usernameLabel;
  public SnakesAndLaddersSidePanelView(LaderGame snakesAndLadders) {
    this.usernameLabel = new Label();
    this.usernameLabel.getStyleClass().addAll("fantasy", "title-3");
    update(snakesAndLadders);
    this.setMinWidth(300);
    this.setStyle(
        "-fx-padding: 20px;"
        + "-fx-background-radius: 20px;"
        + "-fx-border-width: 3;"
        + "-fx-border-radius: 8;"
        + "-fx-background-color: linear-gradient(to bottom, rgba(0, 0, 0, 0.44), rgba(0, 0, 0, 0.66));"
        + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 5, 0.5, 0, 2);");

    snakesAndLadders.registerObserver(this);
    setSpacing(10);
    Button rollButton = new Button("Roll");
    rollButton.getStyleClass().addAll("fantasy-button");
    Button saveButton = new Button("Save current game");
    saveButton.getStyleClass().addAll("fantasy-button");
    HBox diceBox = new HBox();
    diceBox.setSpacing(5);

    for (Die die : snakesAndLadders.getDice().getDice()) {
      DieView newDieView = new DieView(40, Color.WHITE, Color.BLACK);
      newDieView.setVisible(false);
      die.registerObserver(newDieView);
      diceBox.getChildren().add(newDieView);
    }

    rollButton.setOnAction(e -> {
      snakesAndLadders.takeAction();
    });
    saveButton.setOnAction(e -> {
      snakesAndLadders.saveGame();
      PopUp.showInfo("Game saved", "Game has been saved as " + snakesAndLadders.getGameName());
    });

    Region splitterRegion = new Region();
    VBox.setVgrow(splitterRegion, Priority.ALWAYS);

    this.getChildren().addAll(
        usernameLabel,
        rollButton,
        diceBox,
        splitterRegion,
        saveButton
    );
  }

  public void update(LaderGame snakesAndLadders) {
    usernameLabel.setText(snakesAndLadders.getCurrentPlayer().getName());
  }
}