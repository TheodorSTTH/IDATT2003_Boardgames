package edu.ntnu.irr.bidata.model.newlogic.risk;

import edu.ntnu.irr.bidata.model.newlogic.Player;
import java.util.ArrayList;

public class Continent {
  private ArrayList<Country> countries;
  private int bonus;

  public Continent(ArrayList<Country> countries, int bonus) {
    this.countries = countries;
    this.bonus = bonus;
  }

  public boolean isOwnedByPlayer(Player player) {
    return countries.stream().allMatch(country -> country.getArmy().getOwner().equals(player));
  }

  public int getBonus() {
    return bonus;
  }
}
