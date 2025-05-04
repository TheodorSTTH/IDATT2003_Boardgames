package edu.ntnu.irr.bidata.Model.Risk;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ntnu.irr.bidata.Model.Player;

public class BoardRisk {
  private HashMap<String, Country> countries = new HashMap<String, Country>();
  private HashMap<String, List<String>> continens = new HashMap<String, List<String>>();
  private HashMap<String, Integer> continentBonus = new HashMap<String, Integer>();


  public BoardRisk() {
      this.setUpClasicRisk();
  }

  public int NewTropes(Player player) {
      int newTropes = tropBonus(getAmountOfCountrysControldByPlayer(player));
      newTropes += getContientBonus(player);
      return newTropes;
  }
  
  public void placeTropes(String countryName, int tropes) {
      Country country = countries.get(countryName);
      if (country != null) {
          country.placeTropes(tropes);
      } else {
          throw new IllegalArgumentException("Country not found: " + countryName);
      }
  }
  
  public HashMap<String, Country> getCountries() {
      return countries;
  }

  public void setUpBoard(List<Player> players) {
      if (players.size() < 2 || players.size() > 6) {
          throw new IllegalArgumentException("Number of players must be between 2 and 6.");
      }
      this.devideCountrys(players);
      this.placeStartingTropes(players);
  }

  private void devideCountrys(List<Player> players) {
        List<Country> countryList = new ArrayList<>(countries.values());
        Collections.shuffle(countryList); // Shuffle countries randomly
        int i = 0;
      for (Country country : countryList) {
          country.setOwner(players.get(i));
          country.setArmies(1);
          i++;
          if (i >= players.size()) {
              i = 0;
          }
      }
  }

  private void placeStartingTropes(List<Player> players) {
        for (Player player : players) {
            List<String> countries = this.getCountrysControldByPlayerAsStrings(player);
            int tropes = 50 - players.size() * 5 - countries.size();
            for (int i = 0; i < tropes; i++) {
                int randomIndex = (int) (Math.random() * countries.size());
                this.countries.get(countries.get(randomIndex)).placeTropes(1);
            }
        }
      
      

      

  }
  
  private int getAmountOfCountrysControldByPlayer(Player player) {
      int amount = 0;
      for (Country country : countries.values()) {
          if (country.getOwner().equals(player.getName())) {
              amount++;
          }
      }
      return amount;
  }
    
  private int getContientBonus(Player player) {
      int bonus = 0;
      for (String continent : continens.keySet()) {
          if (doesPlayerControlContinent(continent, player)) {
              bonus += continentBonus.get(continent);
          }
      }
      return bonus;
  }
  
  private int tropBonus(int conteris) {
      if (conteris < 12) {
          return 3;
      } else {
          return ((conteris - (conteris % 3)) / 3);
        }
  }
    
  private boolean doesPlayerControlContinent(String continent, Player player) {
      List<String> countriesOwned = this.getCountrysControldByPlayerAsStrings(player);
      for (String countryName : continens.get(continent)) {
          if (!countriesOwned.contains(countryName)) {
              return false;
          }
      }
      return (true);
  }

  private List<String> getCountrysControldByPlayerAsStrings(Player player) {
      List<String> countriesControlled = new ArrayList<String>();
      for (Country country : countries.values()) {
          if (country.getOwner().equals(player.getName())) {
              countriesControlled.add(country.getName());
          }
      }
      return countriesControlled;
  }

  public List<Country> getCountriesControlledByPlayer(Player player) {
      List<Country> countriesControlled = new ArrayList<Country>();
      for (Country country : countries.values()) {
          if (country.getOwner().equals(player.getName())) {
              countriesControlled.add(country);
          }
      }
      return countriesControlled;
  }
  
  public HashMap<Country, List<Country>> getAttackOptions(Player player) {
      HashMap<Country, List<Country>> attackOptions = new HashMap<Country, List<Country>>();
      for (Country country : countries.values()) {
          if (country.getOwner().equals(player.getName()) && country.getArmies() > 1) {
              List<Country> neighbors = new ArrayList<Country>();
              for (String neighborName : country.getNeighbors()) {
                  Country neighbor = countries.get(neighborName);
                  if (!neighbor.getOwner().equals(player.getName())) {
                      neighbors.add(neighbor);
                  }
              }
              if (!neighbors.isEmpty()) {
                  attackOptions.put(country, neighbors);
              }
          }
      }
      return attackOptions;
  }
  
  public boolean hasWon(Player player) {
      for (Country country : countries.values()) {
          if (!country.getOwner().equals(player.getName())) {
              return false;
          }
      }
      return true;
  }
  
  public boolean hasLost(Player player) {
      for (Country country : countries.values()) {
          if (country.getOwner().equals(player.getName())) {
              return false;
          }
      }
      return true;
  }

  public void takeControlOfCountry(String countryName, Player player) {
      Country country = countries.get(countryName);
      if (country != null) {
          country.setOwner(player);
      } else {
          throw new IllegalArgumentException("Country not found: " + countryName);
      }
  }

  public void removeTroops(String countryName, int troops) {
      Country country = countries.get(countryName);
      if (country != null) {
          country.loseTroops(troops);
      } else {
          throw new IllegalArgumentException("Country not found: " + countryName);
      }
  }

  public void addTroops(String countryName, int troops) {
      Country country = countries.get(countryName);
      if (country != null) {
          country.setArmies(country.getArmies() + troops);
      } else {
          throw new IllegalArgumentException("Country not found: " + countryName);
      }
  }
    
  public int getUnits(String countryName) {
      Country country = countries.get(countryName);
      if (country != null) {
          return country.getArmies();
      } else {
          throw new IllegalArgumentException("Country not found: " + countryName);
      }
  }
  
  public void transferTroops(String fromCountry, String toCountry, int troops) {
      Country from = countries.get(fromCountry);
      Country to = countries.get(toCountry);
      if (from != null && to != null) {
          if (from.getArmies() > troops) {
              from.setArmies(from.getArmies() - troops);
              to.setArmies(to.getArmies() + troops);
          } else {
              throw new IllegalArgumentException("Not enough troops in " + fromCountry);
          }
      } else {
          throw new IllegalArgumentException("Country not found: " + fromCountry + " or " + toCountry);
      }
  }



    public void saveBoard(String gameName) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File("src/main/resources/files/"+gameName+".board.json"), this);
            System.out.println("Board saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BoardRisk loadBoard(String gameName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/files/"+gameName + ".board.json"), BoardRisk.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean controldBySamePlayer(String country1 , String country2) {
        Country c1 = countries.get(country1);
        Country c2 = countries.get(country2);
        if (c1 != null && c2 != null) {
            return c1.getOwner().equals(c2.getOwner());
        } else {
            throw new IllegalArgumentException("Country not found: " + country1 + " or " + country2);
        }
    }
  

  private void setUpClasicRisk() {
    countries.put("Alaska",
        new Country("Alaska", List.of("Northwest Territory", "Alberta", "Kamchatka"), 0.07, 0.16));
    countries.put("Northwest Territory",
        new Country("Northwest Territory", List.of("Alaska", "Alberta", "Ontario", "Greenland"), 0.16, 0.14));
    countries.put("Alberta",
        new Country("Alberta", List.of("Alaska", "Northwest Territory", "Ontario", "Western United States"), 0.13, 0.23));
    countries.put("Ontario",
        new Country("Ontario", List.of("Northwest Territory", "Alberta", "Greenland", "Quebec", "Eastern United States", "Western United States"), 0.23, 0.24));
    countries.put("Greenland",
        new Country("Greenland", List.of("Northwest Territory", "Ontario", "Quebec", "Iceland"), 0.32, 0.09));
    countries.put("Quebec",
        new Country("Quebec", List.of("Greenland", "Ontario", "Eastern United States"), 0.29, 0.26));
    countries.put("Western United States",
        new Country("Western United States", List.of("Alberta", "Ontario", "Eastern United States", "Central America"), 0.14, 0.34));
    countries.put("Eastern United States",
        new Country("Eastern United States", List.of("Ontario", "Quebec", "Western United States", "Central America"), 0.23, 0.34));
    countries.put("Central America",
        new Country("Central America", List.of("Western United States", "Eastern United States", "Venezuela"), 0.14, 0.42));

    countries.put("Venezuela",
        new Country("Venezuela", List.of("Central America", "Peru", "Brazil"), 0.23, 0.53));
    countries.put("Peru",
        new Country("Peru", List.of("Venezuela", "Brazil", "Argentina"), 0.25, 0.65));
    countries.put("Brazil",
        new Country("Brazil", List.of("Venezuela", "Peru", "Argentina", "North Africa"), 0.31, 0.64));
    countries.put("Argentina",
        new Country("Argentina", List.of("Peru", "Brazil"), 0.24, 0.78));

    countries.put("Iceland",
        new Country("Iceland", List.of("Greenland", "Scandinavia", "Great Britain"), 0.39, 0.20));
    countries.put("Scandinavia",
        new Country("Scandinavia", List.of("Iceland", "Great Britain", "Northern Europe", "Ukraine"), 0.47, 0.20));
    countries.put("Great Britain",
        new Country("Great Britain", List.of("Iceland", "Scandinavia", "Northern Europe", "Western Europe"), 0.39, 0.28));
    countries.put("Northern Europe",
        new Country("Northern Europe", List.of("Scandinavia", "Great Britain", "Western Europe", "Southern Europe", "Ukraine"), 0.48, 0.30));
    countries.put("Western Europe",
        new Country("Western Europe", List.of("Great Britain", "Northern Europe", "Southern Europe", "North Africa"), 0.41, 0.45));
    countries.put("Southern Europe",
        new Country("Southern Europe", List.of("Northern Europe", "Western Europe", "Ukraine", "Middle East", "Egypt", "North Africa"), 0.50, 0.42));
    countries.put("Ukraine",
        new Country("Ukraine", List.of("Scandinavia", "Northern Europe", "Southern Europe", "Ural", "Afghanistan", "Middle East"), 0.57, 0.28));

    countries.put("North Africa",
        new Country("North Africa", List.of("Brazil", "Western Europe", "Southern Europe", "Egypt", "East Africa", "Congo"), 0.42, 0.57));
    countries.put("Egypt",
        new Country("Egypt", List.of("Southern Europe", "North Africa", "East Africa", "Middle East"), 0.53, 0.53));
    countries.put("East Africa",
        new Country("East Africa", List.of("North Africa", "Egypt", "Congo", "South Africa", "Madagascar", "Middle East"), 0.55, 0.64));
    countries.put("Congo",
        new Country("Congo", List.of("North Africa", "East Africa", "South Africa"), 0.53, 0.71));
    countries.put("South Africa",
        new Country("South Africa", List.of("Congo", "East Africa", "Madagascar"), 0.52, 0.82));
    countries.put("Madagascar",
        new Country("Madagascar", List.of("South Africa", "East Africa"), 0.61, 0.815));

    countries.put("Ural",
        new Country("Ural", List.of("Ukraine", "Siberia", "China", "Afghanistan"), 0.67, 0.25));
    countries.put("Siberia",
        new Country("Siberia", List.of("Ural", "China", "Mongolia", "Irkutsk", "Yakutsk"), 0.71, 0.12));
    countries.put("Yakutsk",
        new Country("Yakutsk", List.of("Siberia", "Irkutsk", "Kamchatka"), 0.79, 0.10));
    countries.put("Irkutsk",
        new Country("Irkutsk", List.of("Siberia", "Yakutsk", "Kamchatka", "Mongolia"), 0.79, 0.25));
    countries.put("Kamchatka",
        new Country("Kamchatka", List.of("Alaska", "Japan", "Mongolia", "Irkutsk", "Yakutsk"), 0.88, 0.14));

    countries.put("Mongolia",
        new Country("Mongolia", List.of("Siberia", "China", "Irkutsk", "Japan", "Kamchatka"), 0.79, 0.33));
    countries.put("China",
        new Country("China", List.of("Ural", "Siberia", "Mongolia", "Afghanistan", "India", "Siam"), 0.77, 0.41));
    countries.put("Afghanistan",
        new Country("Afghanistan", List.of("Ukraine", "Ural", "China", "India", "Middle East"), 0.63, 0.37));
    countries.put("Middle East",
        new Country("Middle East", List.of("Southern Europe", "Ukraine", "Afghanistan", "India", "East Africa", "Egypt"), 0.59, 0.47));
    countries.put("India",
        new Country("India", List.of("China", "Afghanistan", "Middle East", "Siam"), 0.71, 0.50));
    countries.put("Siam",
        new Country("Siam", List.of("China", "India", "Indonesia"), 0.79, 0.56));
    countries.put("Japan",
        new Country("Japan", List.of("Mongolia", "Kamchatka"), 0.89, 0.37));

    countries.put("Indonesia",
        new Country("Indonesia", List.of("Siam", "New Guinea", "Western Australia"), 0.80, 0.72));
    countries.put("New Guinea",
        new Country("New Guinea", List.of("Indonesia", "Eastern Australia", "Western Australia"), 0.88, 0.65));
    countries.put("Western Australia",
        new Country("Western Australia", List.of("Indonesia", "New Guinea", "Eastern Australia"), 0.83, 0.81));
    countries.put("Eastern Australia",
            new Country("Eastern Australia", List.of("New Guinea", "Western Australia"), 0.93, 0.85));
        
    continens.put("North America", List.of("Alaska", "Northwest Territory", "Alberta", "Ontario", "Greenland", "Quebec",
            "Eastern United States", "Western United States", "Central America"));
    continens.put("South America", List.of("Venezuela", "Peru", "Brazil", "Argentina"));
    continens.put("Europe", List.of("Iceland", "Scandinavia", "Great Britain", "Northern Europe", "Western Europe",
            "Southern Europe", "Ukraine"));
    continens.put("Africa", List.of("North Africa", "Egypt", "East Africa", "Congo", "South Africa", "Madagascar"));
    continens.put("Asia", List.of("Ural", "Siberia", "China", "Afghanistan", "Middle East", "India", "Siam", "Mongolia",
            "Irkutsk", "Yakutsk", "Kamchatka", "Japan"));
    continens.put("Australia", List.of("Indonesia", "New Guinea", "Western Australia", "Eastern Australia"));

    continentBonus.put("North America", 5);
    continentBonus.put("South America", 2);
    continentBonus.put("Europe", 5);
    continentBonus.put("Africa", 3);
    continentBonus.put("Asia", 7);
    continentBonus.put("Australia", 2);
  }
}
