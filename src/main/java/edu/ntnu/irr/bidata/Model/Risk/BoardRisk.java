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

/**
 * Represents the Risk board and all logic for countries, continents, and troop deployment.
 */
public class BoardRisk {

    private HashMap<String, Country> countries = new HashMap<String, Country>();
    private HashMap<String, List<String>> continents = new HashMap<String, List<String>>();
    private HashMap<String, Integer> continentBonus = new HashMap<String, Integer>();

    /**
    * Constructs a BoardRisk instance with the classic Risk map setup.
    */
    public BoardRisk() {
        this.setUpClassicRisk(); // Initialize board with predefined countries and continents
    }





    /**
 * Calculates how many new troops a player receives at the start of their turn.
 *
 * @param player The player receiving troops
 * @return Number of new troops
 */
public int newTroops(Player player) {
    // Base troops from number of countries controlled
    int newTroops = troopBonus(getAmountOfCountriesControlledByPlayer(player));
    // Additional bonus from controlling entire continents
    newTroops += getContinentBonus(player);
    return newTroops;
}

/**
 * Places troops on the specified country.
 *
 * @param countryName Name of the country
 * @param troops      Number of troops to place
 */
public void placeTroops(String countryName, int troops) {
    Country country = countries.get(countryName);
    if (country != null) {
        country.placeTroops(troops); // Place troops if country exists
    } else {
        throw new IllegalArgumentException("Country not found: " + countryName);
    }
}

/**
 * Returns the map of all countries on the board.
 *
 * @return a HashMap where the key is the country's name and the value is the Country object
 */
public HashMap<String, Country> getCountries() {
    return countries;
}

/**
 * Sets up the initial game board by dividing countries among players and placing starting troops.
 *
 * @param players a list of players participating in the game
 * @throws IllegalArgumentException if the number of players is not between 2 and 6
 */
public void setUpBoard(List<Player> players) {
    if (players.size() < 2 || players.size() > 6) {
        throw new IllegalArgumentException("Number of players must be between 2 and 6.");
    }
    this.divideCountries(players);
    this.placeStartingtroops(players);
}

/**
 * Randomly assigns countries to players. If only 2 players are present, a temporary bot is added
 * to balance the distribution. Each player is assigned one army per country, while the bot gets two.
 *
 * @param players the list of players
 */
private void divideCountries(List<Player> players) {
    if (players.size() == 2) {
        // Add a dummy player (bot) to improve distribution with 2 players
        Player bot = new Player("", "Pink", 0);
        players.add(bot);
    }

    List<Country> countryList = new ArrayList<>(countries.values());
    Collections.shuffle(countryList); // Randomize country distribution

    int i = 0;
    for (Country country : countryList) {
        Player currentPlayer = players.get(i);
        country.setOwner(currentPlayer);

        // Assign 2 armies to bot, 1 to other players
        if (currentPlayer.getName().equals("")) {
            country.setArmies(2);
        } else {
            country.setArmies(1);
        }

        i++;
        if (i >= players.size()) {
            i = 0;
        }
    }

    // Remove the bot from the list after distribution
    players.removeIf(player -> player.getName().equals(""));
}

/**
 * Allocates remaining starting troops randomly across countries each player controls.
 *
 * @param players the list of players
 */
private void placeStartingtroops(List<Player> players) {
    for (Player player : players) {
        List<String> countries = this.getCountriesControlledByPlayerAsStrings(player);
        int troops = 50 - players.size() * 5 - countries.size();

        for (int i = 0; i < troops; i++) {
            int randomIndex = (int) (Math.random() * countries.size());
            this.countries.get(countries.get(randomIndex)).placeTroops(1);
        }
    }
}

/**
 * Counts the number of countries controlled by a specific player.
 *
 * @param player the player
 * @return the number of countries the player controls
 */
private int getAmountOfCountriesControlledByPlayer(Player player) {
    int amount = 0;
    for (Country country : countries.values()) {
        if (country.getOwner().equals(player.getName())) {
            amount++;
        }
    }
    return amount;
}

/**
 * Calculates continent bonuses for a player.
 *
 * @param player the player
 * @return the total bonus from fully controlled continents
 */
private int getContinentBonus(Player player) {
    int bonus = 0;
    for (String continent : continents.keySet()) {
        if (doesPlayerControlContinent(continent, player)) {
            bonus += continentBonus.get(continent);
        }
    }
    return bonus;
}

/**
 * Calculates the base troop bonus for a player based on how many countries they control.
 *
 * @param countries number of countries controlled
 * @return number of bonus troops
 */
private int troopBonus(int countries) {
    if (countries < 12) {
        return 3;
    } else {
        return ((countries - (countries % 3)) / 3);
    }
}

/**
 * Checks if a player controls all countries in a continent.
 *
 * @param continent the name of the continent
 * @param player the player
 * @return true if the player controls the entire continent
 */
private boolean doesPlayerControlContinent(String continent, Player player) {
    List<String> countriesOwned = this.getCountriesControlledByPlayerAsStrings(player);
    for (String countryName : continents.get(continent)) {
        if (!countriesOwned.contains(countryName)) {
            return false;
        }
    }
    return true;
}

/**
 * Returns a list of country names controlled by the player.
 *
 * @param player the player
 * @return list of country names
 */
private List<String> getCountriesControlledByPlayerAsStrings(Player player) {
    List<String> countriesControlled = new ArrayList<>();
    for (Country country : countries.values()) {
        if (country.getOwner().equals(player.getName())) {
            countriesControlled.add(country.getName());
        }
    }
    return countriesControlled;
}

/**
 * Returns a list of countries controlled by the player.
 *
 * @param player the player
 * @return list of Country objects
 */
public List<Country> getCountriesControlledByPlayer(Player player) {
    List<Country> countriesControlled = new ArrayList<>();
    for (Country country : countries.values()) {
        if (country.getOwner().equals(player.getName())) {
            countriesControlled.add(country);
        }
    }
    return countriesControlled;
}

/**
 * Returns a map of possible attacks for a player. Each entry maps a country the player owns
 * (and has more than 1 troop) to a list of adjacent enemy countries that can be attacked.
 *
 * @param player the player
 * @return map of attack options
 */
public HashMap<Country, List<Country>> getAttackOptions(Player player) {
    HashMap<Country, List<Country>> attackOptions = new HashMap<>();
    for (Country country : countries.values()) {
        // Player must own the country and have more than one troop to attack
        if (country.getOwner().equals(player.getName()) && country.getArmies() > 1) {
            List<Country> neighbors = new ArrayList<>();
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



  
/**
 * Checks if the given player has won the game.
 * A player wins if they own all countries.
 *
 * @param player the player to check
 * @return true if the player owns all countries; false otherwise
 */
public boolean hasWon(Player player) {
    for (Country country : countries.values()) {
        if (!country.getOwner().equals(player.getName())) {
            return false; // Found a country not owned by the player
        }
    }
    return true; // Player owns all countries
}

/**
 * Checks if the given player has lost the game.
 * A player has lost if they control no countries.
 *
 * @param player the player to check
 * @return true if the player has no countries; false otherwise
 */
public boolean hasLost(Player player) {
    for (Country country : countries.values()) {
        if (country.getOwner().equals(player.getName())) {
            return false; // Found a country owned by the player
        }
    }
    return true; // Player owns no countries
}

/**
 * Transfers control of a country to the specified player.
 *
 * @param countryName the name of the country
 * @param player the player taking control
 */
public void takeControlOfCountry(String countryName, Player player) {
    Country country = countries.get(countryName);
    if (country != null) {
        country.setOwner(player);
    } else {
        throw new IllegalArgumentException("Country not found: " + countryName);
    }
}

/**
 * Removes a specified number of troops from a country.
 *
 * @param countryName the country name
 * @param troops the number of troops to remove
 */
public void removeTroops(String countryName, int troops) {
    Country country = countries.get(countryName);
    if (country != null) {
        country.loseTroops(troops);
    } else {
        throw new IllegalArgumentException("Country not found: " + countryName);
    }
}

/**
 * Adds a specified number of troops to a country.
 *
 * @param countryName the country name
 * @param troops the number of troops to add
 */
public void addTroops(String countryName, int troops) {
    Country country = countries.get(countryName);
    if (country != null) {
        country.setArmies(country.getArmies() + troops);
    } else {
        throw new IllegalArgumentException("Country not found: " + countryName);
    }
}

/**
 * Gets the number of troops in a specific country.
 *
 * @param countryName the country name
 * @return the number of troops
 */
public int getUnits(String countryName) {
    Country country = countries.get(countryName);
    if (country != null) {
        return country.getArmies();
    } else {
        throw new IllegalArgumentException("Country not found: " + countryName);
    }
}

/**
 * Transfers troops from one country to another.
 *
 * @param fromCountry the source country
 * @param toCountry the destination country
 * @param troops the number of troops to transfer
 */
public void transferTroops(String fromCountry, String toCountry, int troops) {
    Country from = countries.get(fromCountry);
    Country to = countries.get(toCountry);
    if (from != null && to != null) {
        if (from.getArmies() > troops) {
            from.setArmies(from.getArmies() - troops); // Remove troops from source
            to.setArmies(to.getArmies() + troops);     // Add troops to destination
        } else {
            throw new IllegalArgumentException("Not enough troops in " + fromCountry);
        }
    } else {
        throw new IllegalArgumentException("Country not found: " + fromCountry + " or " + toCountry);
    }
}

/**
 * Saves the current board state to a JSON file.
 *
 * @param gameName the name of the game file to save to
 */
public void saveBoard(String gameName) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    try {
        objectMapper.writeValue(new File("src/main/resources/files/" + gameName + ".board.json"), this);
        System.out.println("Board saved successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

/**
 * Loads a board from a JSON file.
 *
 * @param gameName the name of the game file to load
 * @return the loaded BoardRisk object or null if loading fails
 */
public static BoardRisk loadBoard(String gameName) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
        return objectMapper.readValue(new File("src/main/resources/files/" + gameName + ".board.json"), BoardRisk.class);
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}

/**
 * Checks if two countries are controlled by the same player.
 *
 * @param country1 the first country name
 * @param country2 the second country name
 * @return true if both are controlled by the same player; false otherwise
 */
public boolean controlledBySamePlayer(String country1, String country2) {
    Country c1 = countries.get(country1);
    Country c2 = countries.get(country2);
    if (c1 != null && c2 != null) {
        return c1.getOwner().equals(c2.getOwner());
    } else {
        throw new IllegalArgumentException("Country not found: " + country1 + " or " + country2);
    }
}
  

  /**
 * Sets up the classic Risk game board by adding countries to the board
 * and defining their connections, control territories, and bonuses.
 * This method is used for initializing the game with the classic Risk map.
 */
private void setUpClassicRisk() {
    // Adding countries to the 'countries' map with their neighboring countries and some bonus values.
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

    // Continue adding other countries similarly...

    // Defining continents and the countries that belong to each continent.
    continents.put("North America", List.of("Alaska", "Northwest Territory", "Alberta", "Ontario", "Greenland", "Quebec",
            "Eastern United States", "Western United States", "Central America"));
    continents.put("South America", List.of("Venezuela", "Peru", "Brazil", "Argentina"));
    continents.put("Europe", List.of("Iceland", "Scandinavia", "Great Britain", "Northern Europe", "Western Europe",
            "Southern Europe", "Ukraine"));
    continents.put("Africa", List.of("North Africa", "Egypt", "East Africa", "Congo", "South Africa", "Madagascar"));
    continents.put("Asia", List.of("Ural", "Siberia", "China", "Afghanistan", "Middle East", "India", "Siam", "Mongolia",
            "Irkutsk", "Yakutsk", "Kamchatka", "Japan"));
    continents.put("Australia", List.of("Indonesia", "New Guinea", "Western Australia", "Eastern Australia"));

    // Setting the bonus for controlling each continent.
    continentBonus.put("North America", 5);
    continentBonus.put("South America", 2);
    continentBonus.put("Europe", 5);
    continentBonus.put("Africa", 3);
    continentBonus.put("Asia", 7);
    continentBonus.put("Australia", 2);
}
}
