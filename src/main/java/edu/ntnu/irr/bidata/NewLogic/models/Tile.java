package edu.ntnu.irr.bidata.NewLogic.models;

/**
 * Represents a specific tile on the Board. Connects to the next Tile as
 * a linked list. If there isn't a next tile the player on the tile has won.
 * Tile has actions which are executed when a user lands on it.
 * */
public class Tile extends AbstractSpace<LadderPlayer, LadderPlayer> {
  private Tile nextTile;

  public Tile(int id) {
    super(id);
  }
  public Tile(int id, IAction<LadderPlayer> action) {
    super(id, action);
  }

  /**
   * Sets the next tile.
   * */
  public void setNextTile(Tile nextTile) {
    this.nextTile = nextTile;
  }

  /**
   * Gets the next tile.
   * */
  public Tile getNextTile() {
    return nextTile;
  }
}
