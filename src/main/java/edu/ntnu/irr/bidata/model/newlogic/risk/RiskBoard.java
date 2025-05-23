package edu.ntnu.irr.bidata.model.newlogic.risk;

import edu.ntnu.irr.bidata.model.newlogic.Player;
import edu.ntnu.irr.bidata.model.newlogic.PlayerManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
    from.getArmy().removeTroops(amount);
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

  public Country getCountry(String name) {
    return countries.get(name);
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

  /**
   * Sets up the initial game board by dividing countries among players and placing starting troops.
   *
   * @param playerManager a list of players participating in the game
   * @throws IllegalArgumentException if the number of players is not between 2 and 6
   */
  public void setUpBoard(PlayerManager playerManager) throws IllegalArgumentException{
    if (playerManager.getPlayers().size() < 2 || playerManager.getPlayers().size() > 6) {
      throw new IllegalArgumentException("Number of players must be between 2 and 6.");
    }
    this.divideCountries(playerManager);
    this.placeStartingTroops(playerManager);
  }

  /**
   * Allocates remaining starting troops randomly across countries each player controls.
   *
   * @param playerManager the players
   */
  private void placeStartingTroops(PlayerManager playerManager) {
    for (Player player : playerManager.getPlayers()) {
      ArrayList<Country> countriesControlledByPlayer = getCountriesOwnedByPlayer(player);
      int troops = 50 - playerManager.getPlayers().size() * 5 - countriesControlledByPlayer.size();
      for (int i = 0; i < troops; i++) {
        Country randomCountryControlledByPlayer = countriesControlledByPlayer.get((int) (Math.random() * countriesControlledByPlayer.size()));
        randomCountryControlledByPlayer.getArmy().addAmountOfTroops(1);
      }
    }
  }

  /**
   * Randomly assigns countries to players. If only 2 players are present, a temporary bot is added
   * to balance the distribution. Each player is assigned one army per country, while the bot gets
   * two.
   *
   * @param playerManager the players
   */
  private void divideCountries(PlayerManager playerManager) {
    List<Country> countryList = new ArrayList<>(countries.values());
    Collections.shuffle(countryList); // Randomize country distribution

    int i = 0;
    for (Country country : countryList) {
      country.setArmy(
          new Army(
              playerManager.getPlayers().get(i)
          )
      );
      country.getArmy().setTroopCount(1);
      i++;
      if (i >= playerManager.getPlayers().size()) {
        i = 0;
      }
    }
  }



  public ArrayList<Country> getCountriesPlayerCanMoveFrom(Player player) {
    ArrayList<Country> countriesPlayerCanMoveFrom = new ArrayList<>();
    for (Country country : countries.values()) {
      if (country.getArmy().getOwner().equals(player) && country.getArmy().getTroopCount() > 1) {
        countriesPlayerCanMoveFrom.add(country);
      }
    }
    return countriesPlayerCanMoveFrom;
  }

  public ArrayList<Country> getCountriesPlayerCanAttackFrom(Player player) {
    HashMap<String, Country> attackOptions = new HashMap<>();
    for (Country country : getCountriesOwnedByPlayer(player)) {
      if (country.getArmy().getTroopCount() >= 2 && !getAttackOptionsOfCountry(country).isEmpty()) {
        attackOptions.put(country.getName(), country);
      }
    }
    return new ArrayList<>(attackOptions.values());
  }

  public ArrayList<Country> getAttackOptionsOfCountry(Country country) {
    ArrayList<Country> attackOptions = new ArrayList<>();
    for (Country attackOption : country.getNeighbours()) {
      if (country.getArmy().getTroopCount() >= 2 && !attackOption.getArmy().getOwner().equals(country.getArmy().getOwner())) {
        attackOptions.add(attackOption);
      }
    }
    return attackOptions;
  }
}
