package edu.ntnu.irr.bidata.model.newlogic.snakesandladders;

import edu.ntnu.irr.bidata.model.newlogic.PlayerManager;

public class SnakesAndLaddersFactory {
  public static SnakesAndLaddersGame CreateSnakesAndLaddersGame(PlayerManager playerManager) {
    SnakesAndLaddersBoard board = new SnakesAndLaddersBoard();
    for (int i = 0; i < 90; i++) {
      board.addTile(new Tile(i + 1));
    }
    return new SnakesAndLaddersGame(playerManager, board);
  }
}
