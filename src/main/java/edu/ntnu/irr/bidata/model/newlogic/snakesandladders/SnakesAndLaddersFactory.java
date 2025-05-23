package edu.ntnu.irr.bidata.model.newlogic.snakesandladders;

import edu.ntnu.irr.bidata.model.newlogic.PlayerManager;
import java.util.ArrayList;

public class SnakesAndLaddersFactory {
  public static SnakesAndLaddersGame CreateSnakesAndLaddersGame(PlayerManager playerManager) {
    SnakesAndLaddersBoard board = new SnakesAndLaddersBoard();
    ArrayList<Tile> allTiles = new ArrayList<>();
    for (int i = 0; i < 90; i++) {
      Tile newTile = new Tile(i + 1);
      board.addTile(newTile);
      allTiles.add(newTile);
    }

    allTiles.get(42).addAction(new MoveActionSnakesAndLadders(allTiles.get(42), allTiles.get(80)));
    allTiles.get(19).addAction(new MoveActionSnakesAndLadders(allTiles.get(19), allTiles.get(52)));
    allTiles.get(79).addAction(new MoveActionSnakesAndLadders(allTiles.get(79), allTiles.get(10)));
    allTiles.get(15).addAction(new MoveActionSnakesAndLadders(allTiles.get(24), allTiles.get(7)));

    return new SnakesAndLaddersGame(playerManager, board);
  }
}
