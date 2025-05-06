package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.Die;
import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.View.PopUp;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SnakesAndLaddersSidePanelView extends VBox implements IObserver<LaderGame> {
  private final Label usernameLabel;
  public SnakesAndLaddersSidePanelView(LaderGame snakesAndLadders) {
    usernameLabel = new Label();
    update(snakesAndLadders);
    this.setMinWidth(200);
    this.setStyle("-fx-background-color: #176218");
    snakesAndLadders.registerObserver(this);

    Button rollButton = new Button("Roll");
    Button saveButton = new Button("Save current game");
    VBox diceBox = new VBox();
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

    this.getChildren().addAll(
        new Label("Side panel"),
        usernameLabel,
        rollButton,
        saveButton,
        diceBox
    );
  }

  public void update(LaderGame snakesAndLadders) {
    usernameLabel.setText("Current player: " + snakesAndLadders.getCurrentPlayer().getName());
  }
}