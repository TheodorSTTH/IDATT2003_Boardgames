package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.View.Tile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class SnakesAndLaddersBoardView extends Pane implements IObserver<LaderGame> {

  private final int boardColumnAmount = 9;
  private final int boardRowsAmount = 10;

  private final ArrayList<Tile> currentTiles = new ArrayList<>(boardColumnAmount * boardRowsAmount);

  public SnakesAndLaddersBoardView(LaderGame snakesAndLadders) {
    for (int i = 0; i < 90; i++) {
      currentTiles.add(null);
    }
    update(snakesAndLadders);
    snakesAndLadders.registerObserver(this);
  }

  public Tile getTile(int index) {
    return currentTiles.get(index);
  }

  public void renderBoard() {
    for (int row = 0; row < boardRowsAmount; row++) {
      for (int col = 1; col < boardColumnAmount + 1; col++) {
        final int currentTileNumber = row*9 + col;
        int tileWidth = 40;
        int tileHeight = 35;
        Tile currentTile = new Tile(tileWidth, tileHeight, currentTileNumber);
        currentTiles.set(currentTileNumber - 1, currentTile);
        if (row % 2 == 0) {
          currentTile.setLayoutX((col - 1)* tileWidth);
        } else {
          currentTile.setLayoutX(tileWidth * boardColumnAmount - col* tileWidth);
        }
        currentTile.setLayoutY(tileHeight * (boardRowsAmount - 1) - row* tileHeight);
        getChildren().add(currentTile);
      }
    }
  }

  private void placePlayers(LaderGame snakesAndLadders) {
    HashMap<Player, Integer> playerPositions = snakesAndLadders.getPlayerPositions();
    for (Player player : playerPositions.keySet()) {
      int playerTileIndex = playerPositions.get(player);
      Tile playerTile = getTile(playerTileIndex);
      playerTile.placePlayer(player);
    }
  }

  /**
   * Re-renders the current board and places players on the board. Effectively updating
   * the board for the user.
   *
   * @param snakesAndLadders is the current snakes and ladders game object.
   * */
  public void update(LaderGame snakesAndLadders) {
    getChildren().clear();
    renderBoard();
    placePlayers(snakesAndLadders);
  }
}
