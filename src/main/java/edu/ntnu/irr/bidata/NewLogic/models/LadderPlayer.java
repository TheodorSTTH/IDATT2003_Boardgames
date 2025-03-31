package edu.ntnu.irr.bidata.NewLogic.models;

/**
 * Represents a ladder game player. Is responsible for managing its own name
 * and its current position inside the board.
 * */
public class LadderPlayer extends AbstractPlayer<Tile> {
  public LadderPlayer(String name, Tile currentTile) {
    super(name, currentTile);
  }

  public void move(int steps) {
    Tile tileToMoveTo = currentSpace;
    for (int i = 0; i < steps; i++) {
      boolean nextTileExists = tileToMoveTo.getNextTile() != null;
      if (nextTileExists) tileToMoveTo = tileToMoveTo.getNextTile();
    }
    setCurrentSpace(tileToMoveTo);
    tileToMoveTo.landPlayer(this);
  }
}
