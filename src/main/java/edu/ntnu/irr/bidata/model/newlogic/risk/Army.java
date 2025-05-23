package edu.ntnu.irr.bidata.model.newlogic.risk;

import edu.ntnu.irr.bidata.model.newlogic.Figure;
import edu.ntnu.irr.bidata.model.newlogic.Player;

public class Army extends Figure {
  private int troopCount;

  public Army(Player player) {
    super(player);
  }
  
  public int getTroopCount() {
    return troopCount;
  }

  public void setTroopCount(int troopCount) {
    this.troopCount = troopCount;
  }

  public void removeTroops(int amountOfTroopsToRemove) {
    this.troopCount -= amountOfTroopsToRemove;
  }

  public void addAmountOfTroops(int amountOfTroopsToAdd) {
    this.troopCount += amountOfTroopsToAdd;
  }
}
