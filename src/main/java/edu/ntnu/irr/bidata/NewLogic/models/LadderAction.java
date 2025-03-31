package edu.ntnu.irr.bidata.NewLogic.models;

public class LadderAction implements IAction<LadderPlayer> {
  private final Tile destination;

  public LadderAction(Tile destination) {
    this.destination = destination;
  }
  public void perform(LadderPlayer ladderPlayer) {
    ladderPlayer.setCurrentSpace(destination);
  }
}
