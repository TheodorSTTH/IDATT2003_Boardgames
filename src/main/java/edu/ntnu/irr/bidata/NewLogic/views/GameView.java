package edu.ntnu.irr.bidata.NewLogic.views;

import edu.ntnu.irr.bidata.NewLogic.models.BoardGame;
import edu.ntnu.irr.bidata.NewLogic.models.Player;
import edu.ntnu.irr.bidata.Wiew.Tile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameView extends BorderPane {
  private Button rollButton;
  private Label statusLabel;
  private BoardView boardView;
  private BoardGame game;

  public GameView(BoardGame game) {
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
    for (Player player : game.getPlayers()) {
      TileView playerTile = boardView.getTile(player.getCurrentTile().getTileId());
      playerTile.getChildren().add(new Label(player.getName()));
    }
    StringBuilder status = new StringBuilder();
    for (Player player : game.getPlayers()) {
      status.append(player.getName())
          .append(" is on tile ")
          .append(player.getCurrentTile().getTileId())
          .append("\n");
    }
    statusLabel.setText(status.toString());
  }
}
