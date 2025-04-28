package edu.ntnu.irr.bidata.Model.LadderGame.Event;

public class LadderEvent extends Event {
  private int destination;

  public LadderEvent() {
      // For Json Saving and Loading
  }

  public LadderEvent(int destination) {
      this.destination = destination;
  }

  @Override
  public int Action() {
      return destination;
  }

  public int getDestination() {
      return destination;
  }

  public void setDestination(int destination) {
      this.destination = destination;
  }
}
