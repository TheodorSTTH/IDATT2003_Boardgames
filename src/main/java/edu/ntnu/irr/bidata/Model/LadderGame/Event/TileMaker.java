package edu.ntnu.irr.bidata.Model.LadderGame.Event;

public class TileMaker {
  
  public static Event newLadder(int destination) {
    return new LadderEvent(destination);
  }

  public static Event newQizzTile() {
    return null;
  }
}
