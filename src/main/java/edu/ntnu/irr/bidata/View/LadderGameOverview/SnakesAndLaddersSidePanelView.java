package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.View.RiskGame.AbstractSidebarPane;
import edu.ntnu.irr.bidata.View.RiskGame.AttackPane;
import edu.ntnu.irr.bidata.View.RiskGame.MoveTroopsPane;
import edu.ntnu.irr.bidata.View.RiskGame.PlaceTroopsPane;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SnakesAndLaddersSidePanelView extends VBox implements IObserver<LaderGame> {
  private Label usernameLabel;
  public SnakesAndLaddersSidePanelView(LaderGame snakesAndLadders) {
    usernameLabel = new Label();
    update(snakesAndLadders);
    this.setMinWidth(200);
    this.setStyle("-fx-background-color: #176218");
    snakesAndLadders.registerObserver(this);

    Button rollButton = new Button("Roll");
    Button saveButton = new Button("Save current game");

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
        saveButton
    );
  }

  public void update(LaderGame snakesAndLadders) {
    usernameLabel.setText(snakesAndLadders.getCurrentPlayer().getName());
  }
}