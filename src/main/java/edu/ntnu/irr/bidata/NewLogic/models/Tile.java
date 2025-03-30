package edu.ntnu.irr.bidata.NewLogic.models;

/**
 * Represents a specific tile on the Board. Connects to the next Tile as
 * a linked list. If there isn't a next tile the player on the tile has won.
 * Tile has actions which are executed when a user lands on it.
 * */
public class Tile {
  private Tile nextTile;
  private final int tileId;
  private TileAction landAction;

  public Tile(int tileId) {
    this.tileId = tileId;
  }

  /**
   * Should be called when a player lands on a tile after moving.
   * Performs an action on the player.
   * */
  public void landPlayer(Player player) {
    if (landAction != null) landAction.perform(player);
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

  /**
   * Gets the current tile id (equals index of tile)
   * */
  public int getTileId() {
    return tileId;
  }

  /**
   * Sets action which is performed when you land on tile.
   * */
  public void setLandAction(TileAction landAction) {
    this.landAction = landAction;
  }
}
