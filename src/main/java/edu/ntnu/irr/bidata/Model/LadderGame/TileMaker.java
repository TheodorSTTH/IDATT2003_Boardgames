package edu.ntnu.irr.bidata.Model.LadderGame;

public class TileMaker {
  
  public static Tile newLadder(int destination) {
    return new LadderTile(destination);
  }
}
