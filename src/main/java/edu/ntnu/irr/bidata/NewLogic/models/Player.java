package edu.ntnu.irr.bidata.NewLogic.models;

/**
 * Represents a ladder game player. Is responsible for managing its own name
 * and its current position inside the board.
 * */
public class Player {
  private final String name;
  private Tile currentTile;
  private final BoardGame game;

  public Player(String name, BoardGame game, Tile currentTile) {
    this.name = name;
    this.game = game;
    this.currentTile = currentTile;
  }

  public void placeOnTile(Tile tile) {
    currentTile=tile;
  }
  public void placeOnTile(int tileId) {
    placeOnTile(game.getBoard().getTile(tileId));
  }

  public Tile getCurrentTile() {
    return currentTile;
  }

  public void move(int steps) {
    Tile tileToMoveTo = currentTile;
    for (int i = 0; i < steps; i++) {
      boolean nextTileExists = tileToMoveTo.getNextTile() != null;
      if (nextTileExists) tileToMoveTo = tileToMoveTo.getNextTile();
    }
    placeOnTile(tileToMoveTo);
    tileToMoveTo.landPlayer(this);
  }

  public String getName() {
    return name;
  }
}
