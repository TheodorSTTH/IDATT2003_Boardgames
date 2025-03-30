package edu.ntnu.irr.bidata.Model.LadderGame;

public class LadderTile extends Tile {
  int destination = 0;

  public LadderTile(int destination) {
    this.destination = destination;
  }

  @Override
  public int tlleAction() {
    return destination;
  }
}
