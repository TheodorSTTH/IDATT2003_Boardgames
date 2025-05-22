package edu.ntnu.irr.bidata.model.newlogic.risk;

import edu.ntnu.irr.bidata.model.newlogic.Figure;
import edu.ntnu.irr.bidata.model.newlogic.Player;
import javafx.scene.paint.Color;

public class Army extends Figure {
  private int amountOfTroops;

  public Army(Player player, Color color) {
    super(player, color);
  }
  
  public int getTroopCount() {
    return amountOfTroops;
  }
  public void setAmountOfTroops(int amountOfTroops) {
    this.amountOfTroops = amountOfTroops;
  }

  public void removeAmountOfTroops(int amountOfTroopsToRemove) {
    this.amountOfTroops -= amountOfTroopsToRemove;
  }

  public void addAmountOfTroops(int amountOfTroopsToAdd) {
    this.amountOfTroops += amountOfTroopsToAdd;
  }
}
