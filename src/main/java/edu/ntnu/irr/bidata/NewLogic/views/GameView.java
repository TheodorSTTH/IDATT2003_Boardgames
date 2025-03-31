package edu.ntnu.irr.bidata.NewLogic.views;

import edu.ntnu.irr.bidata.NewLogic.models.LadderGame;
import edu.ntnu.irr.bidata.NewLogic.models.LadderPlayer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameView extends BorderPane {
  private Button rollButton;
  private Label statusLabel;
  private BoardView boardView;
  private final LadderGame game;

  public GameView(LadderGame game) {
    this.game = game;
    initComponents();
  }

  private void initComponents() {
    rollButton = new Button("Roll Dice");
    statusLabel = new Label();
    boardView = new BoardView();

    // For simplicity, place the roll button at the bottom and status in the center.
    this.setTop(rollButton);
    this.setCenter(boardView);
    this.setBottom(statusLabel);

    // Initial update:
    updateStatus();
  }

  public Button getRollButton() {
    return rollButton;
  }

  public void updateStatus() {
    boardView.updateBoardUI();
    for (LadderPlayer ladderPlayer : game.getPlayers()) {
      TileView playerTile = boardView.getTile(ladderPlayer.getCurrentSpace().getId());
      playerTile.getChildren().add(new Label(ladderPlayer.getName()));
    }
    StringBuilder status = new StringBuilder();
    for (LadderPlayer ladderPlayer : game.getPlayers()) {
      status.append(ladderPlayer.getName())
          .append(" is on tile ")
          .append(ladderPlayer.getCurrentSpace().getId())
          .append("\n");
    }
    statusLabel.setText(status.toString());
  }
}
