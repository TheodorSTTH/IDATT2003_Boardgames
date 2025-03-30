package edu.ntnu.irr.bidata.NewLogic.models;

public class LadderAction implements TileAction{
  private final int destinationTileId;

  public LadderAction(int destinationTileId) {
    this.destinationTileId = destinationTileId;
  }
  public void perform(Player player) {
    player.placeOnTile(destinationTileId);
  }
}
