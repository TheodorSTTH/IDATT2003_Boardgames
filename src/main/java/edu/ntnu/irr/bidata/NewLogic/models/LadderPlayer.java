package edu.ntnu.irr.bidata.NewLogic.models;

/**
 * Represents a ladder game player. Is responsible for managing its own name
 * and its current position inside the board.
 * */
public class LadderPlayer extends AbstractPlayer<Tile> {
  private Tile currentTile;

  public LadderPlayer(String name, Tile currentTile) {
    super(name);
    this.currentTile = currentTile;
  }

  public void setCurrentSpace(Tile tile) {
    this.currentTile = tile;
  }
  public Tile getCurrentSpace() {
    return currentTile;
  }

  public void move(int steps) {
    Tile tileToMoveTo = currentTile;
    for (int i = 0; i < steps; i++) {
      boolean nextTileExists = tileToMoveTo.getNextTile() != null;
      if (nextTileExists) tileToMoveTo = tileToMoveTo.getNextTile();
    }
    setCurrentSpace(tileToMoveTo);
    tileToMoveTo.performAction(this);
  }

  public boolean hasWon() {
    return currentTile.getNextTile() == null;
  }
}
