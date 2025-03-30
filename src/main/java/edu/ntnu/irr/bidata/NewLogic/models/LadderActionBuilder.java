package edu.ntnu.irr.bidata.NewLogic.models;

/**
 * Builder to create ladder actions in the ladder game.
 * */
public class LadderActionBuilder {
  private int fromTileId;
  private int toTileId;
  private final Board board;

  public LadderActionBuilder(Board board) {
    this.board = board;
  }

  public LadderActionBuilder from(int fromTileId) {
    this.fromTileId = fromTileId;
    return this;
  }

  public LadderActionBuilder to(int toTileId) {
    this.toTileId = toTileId;
    return this;
  }

  public void build() {
    Tile tile = board.getTile(fromTileId);
    if (tile != null) {
      tile.setLandAction(new LadderAction(toTileId));
    } else {
      throw new IllegalArgumentException("Tile " + fromTileId + " does not exist");
    }
  }
}
