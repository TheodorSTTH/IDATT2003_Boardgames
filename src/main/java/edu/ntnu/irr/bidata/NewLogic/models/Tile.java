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

  public void landPlayer(Player player) {
    if (landAction != null) landAction.perform(player);
  }

  public void leavePlayer(Player player) {
    // ???
  }

  public void setNextTile(Tile nextTile) {
    this.nextTile = nextTile;
  }

  public Tile getNextTile() {
    return nextTile;
  }

  public int getTileId() {
    return tileId;
  }

  public void setLandAction(TileAction landAction) {
    this.landAction = landAction;
  }
}
