package edu.ntnu.irr.bidata.Model.LadderGame.Event;

public class LadderEvent extends Event {
  int destination = 0;

  public LadderEvent(int destination) {
    this.destination = destination;
  }

  @Override
  public int Action() {
    return destination;
  }
}
