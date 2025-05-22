package edu.ntnu.irr.bidata.model.newlogic.risk;

import edu.ntnu.irr.bidata.model.newlogic.Player;
import java.util.ArrayList;
import java.util.HashMap;

public class RiskBoard {
  private HashMap<String, Country> countries;
  private ArrayList<Continent> continents;

  public RiskBoard() {}

  public HashMap<String, Country> getCountries() {
    return countries;
  }
  public void setCountries(HashMap<String, Country> countries) {
    this.countries = countries;
  }
  public ArrayList<Continent> getContinents() {
    return continents;
  }
  public void setContinents(ArrayList<Continent> continents) {
    this.continents = continents;
  }

  public void transferTroops(Country from, Country to, int amount) {
    from.getArmy().removeAmountOfTroops(amount);
    to.getArmy().addAmountOfTroops(amount);
  }

  public boolean hasLost(Player player) {
    return getCountriesOwnedByPlayer(player).size() == 0;
  }

  public boolean hasWon(Player player) {
    return getCountriesOwnedByPlayer(player).size() == countries.values().size();
  }

  private int troopBonus(int countries) {
    if (countries < 12) {
      return 3;
    } else {
      return ((countries - (countries % 3)) / 3);
    }
  }

  /**
   * Calculates continent bonuses for a player.
   *
   * @param player the player
   * @return the total bonus from fully controlled continents
   */
  private int getContinentBonus(Player player) {
    int bonus = 0;
    for (Continent continent : continents) {
      if (continent.isOwnedByPlayer(player)) {
        bonus += continent.getBonus();
      }
    }
    return bonus;
  }

  /**
   * Calculates how many new troops a player receives at the start of their turn.
   *
   * @param player The player receiving troops
   * @return Number of new troops
   */
  public int getAmountOfNewTroops(Player player) {
    // Base troops from number of countries controlled
    int newTroops = troopBonus(getCountriesOwnedByPlayer(player).size());
    // Additional bonus from controlling entire continents
    newTroops += getContinentBonus(player);
    return newTroops;
  }

  public boolean isControlledBySamePlayer(Country countryOne, Country countryTwo) {
    return countryOne.getArmy().getOwner().equals(countryTwo.getArmy().getOwner());
  }

  public void takeControlOfCountry(Country country, Player player) {
    country.getArmy().setOwner(player);
  }

  public ArrayList<Country> getCountriesOwnedByPlayer(Player player) {
    ArrayList<Country> countriesOwnedByPlayer = new ArrayList<>();
    for (Country country : countries.values()) {
      if (country.getArmy().getOwner().equals(player)) {
        countriesOwnedByPlayer.add(country);
      }
    }
    return countriesOwnedByPlayer;
  }

  public ArrayList<Country> getAttackOptions(Player player) {
    HashMap<String, Country> attackOptions = new HashMap<>();
    for (Country country : getCountriesOwnedByPlayer(player)) {
      for (Country attackOption : getAttackOptionsOfCountry(country)) {
        attackOptions.put(attackOption.getName(), attackOption);
      }
    }
    return new ArrayList<>(attackOptions.values());
  }

  public ArrayList<Country> getAttackOptionsOfCountry(Country country) {
    ArrayList<Country> attackOptions = new ArrayList<>();
    if (country.getArmy().getTroopCount() >= 2) {
      for (Country neighbor : country.getNeighbours()) {
        if (!neighbor.getArmy().getOwner().equals(country.getArmy().getOwner())) {
          attackOptions.add(country);
        }
      }
    }
    return attackOptions;
  }
}
