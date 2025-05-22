package edu.ntnu.irr.bidata.model.newlogic.snakesandladders;

import edu.ntnu.irr.bidata.model.newlogic.Action;
import edu.ntnu.irr.bidata.model.newlogic.Figure;
import edu.ntnu.irr.bidata.model.newlogic.Space;

public class MoveActionSnakesAndLadders extends Action {
  private final Tile from;
  private final Tile to;

  public MoveActionSnakesAndLadders(Tile from, Tile to) {
    this.from = from;
    this.to = to;
  }

  public void perform(Figure creature) {
    from.removeCreature((Creature) creature);
    to.addCreature((Creature) creature);
  }
}
