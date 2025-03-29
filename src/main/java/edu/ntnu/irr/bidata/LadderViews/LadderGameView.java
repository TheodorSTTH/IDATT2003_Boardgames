package edu.ntnu.irr.bidata.LadderViews;

import edu.ntnu.irr.bidata.GeneralClassesBackend.Game;
import edu.ntnu.irr.bidata.GeneralClassesBackend.Player;
import edu.ntnu.irr.bidata.LaderGameBackend.BoardLaderGame;
import edu.ntnu.irr.bidata.LaderGameBackend.LaderGame;
import javafx.scene.control.Label;

public class LadderGameView {
  private LaderGame currentLadderGame;
  private BoardView currentBoardView;

  public LadderGameView(LaderGame currentLadderGame) {
    this.currentLadderGame = currentLadderGame;
    this.currentBoardView = new BoardView(currentLadderGame.getBoard());
    currentBoardView.renderBoard();
  }

  public void initializeUI() {
    currentBoardView.renderBoard();
    for (Player player : currentLadderGame.getPlayers()) {
      Tile playerTile = currentBoardView.getTile(player.getCurrentTile());
      playerTile.getChildren().add(new Label(player.getName()));
    }
  }
}
