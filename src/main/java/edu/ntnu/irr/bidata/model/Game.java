package edu.ntnu.irr.bidata.model;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class represents a Game that includes functionality for managing players and game
 * settings like the number of players, their colors, and starting player. It provides methods to
 * add players, sort them by age, and switch to the next player.
 */
public abstract class Game {
  protected ArrayList<Player> players = new ArrayList<Player>();
  protected Player currentPlayer;
  private int amountOfPlayers = 0;
  private final List<String> availableColors =
      new ArrayList<>(List.of("Red", "Orange", "Green", "Yellow", "White"));
  private final String gameName;

  /**
   * Constructs a Game with the specified number of players and game name.
   *
   * @param amountOfPlayers the total number of players for this game
   * @param gameName the name of the game
   */
  public Game(int amountOfPlayers, String gameName) {
    this.gameName = gameName;
    this.amountOfPlayers = amountOfPlayers;
  }

  /**
   * Constructs a Game with the specified number of players, game name, list of players, and the
   * current player.
   *
   * @param amountOfPlayers the total number of players for this game
   * @param gameName the name of the game
   * @param players the list of players in the game
   * @param currentPlayer the player currently taking their turn
   */
  public Game(
      int amountOfPlayers, String gameName, ArrayList<Player> players, Player currentPlayer) {
    this.currentPlayer = currentPlayer;
    this.gameName = gameName;
    this.amountOfPlayers = amountOfPlayers;
    this.players = players;
  }

  /**
   * Adds a new player to the game.
   *
   * @param name the name of the player
   * @param color the color the player chooses
   * @param age the age of the player
   * @return true if the player was added successfully, false if there was an error
   */
  public boolean addPlayer(String name, String color, int age) throws IllegalArgumentException {
    // Check if the player already exists in the game by name
    if (getPlayerNames().contains(name)) {
      throw new IllegalArgumentException("Player named " + name + "\nalready exists");
    }

    // Check if the name contains a comma, which is not allowed
    if (name.contains(",")) {
      throw new IllegalArgumentException("Name contains invalid characters. Cannot have comma (,)");
    }

    if (players.size() >= amountOfPlayers) {
      throw new IllegalArgumentException(
          "You cannot add more players to the game. Max amount of players: " + amountOfPlayers);
    }

    if (!availableColors.contains(color)) {
      throw new IllegalArgumentException("Figure color does not exist");
    }

    // Add the new player to the game
    players.add(new Player(name, color, age));

    // Remove the chosen color from the list of available colors
    availableColors.remove(color);

    // If the number of players reaches the limit, initialize the game
    if (players.size() == amountOfPlayers) {
      init();
    }
    return true;
  }

  /** Sorts the players by age in ascending order. */
  public void sortPlayersByAge() {
    players.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
  }

  /** Initializes the game by sorting players and selecting the first player. */
  protected void init() {
    sortPlayersByAge();
    currentPlayer = players.get(0);
  }

  /**
   * Returns the list of players in the game.
   *
   * @return the list of players
   */
  public ArrayList<Player> getPlayers() {
    return players;
  }

  /**
   * Sets the list of players in the game.
   *
   * @param players the list of players to set
   */
  public void setPlayers(ArrayList<Player> players) {
    this.players = players;
  }

  /**
   * Returns the total number of players in the game.
   *
   * @return the number of players
   */
  public int getAmountOfPlayers() {
    return amountOfPlayers;
  }

  /**
   * Returns the color of the player identified by the given name.
   *
   * @param name the name of the player
   * @return the color of the player, or null if the player is not found
   */
  public String getPlayerColor(String name) {
    for (Player player : players) {
      if (player.getName().equals(name)) {
        return player.getColor();
      }
    }
    return null;
  }

  /**
   * Returns the next player in line after the current player.
   *
   * @return the next player
   */
  public Player getNextPlayer() {
    int index = players.indexOf(currentPlayer);
    if (index == players.size() - 1) {
      return players.get(0);
    } else {
      return players.get(index + 1);
    }
  }

  /**
   * Returns the name of the game.
   *
   * @return the name of the game
   */
  public String getGameName() {
    return gameName;
  }

  /**
   * Adds a list of players to the game.
   *
   * @param players the list of players to add
   */
  public void addPlayers(ArrayList<Player> players) {
    this.players = players;
  }

  /**
   * Returns the player who is currently taking their turn.
   *
   * @return the current player
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * Sets the player who will be taking their turn next.
   *
   * @param currentPlayer the player to set as the current player
   */
  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  /**
   * Returns the type of the game. This method should be implemented in a subclass.
   *
   * @return the type of the game
   */
  public abstract String getGameType();

  /** Saves the current game state. */
  public void saveGame() throws UncheckedIOException {
    FileHandler.saveGame(this);
  }

  /**
   * Returns a list of all player names in the game.
   *
   * @return the list of player names
   */
  protected List<String> getPlayerNames() {
    List<String> names = new ArrayList<>();
    for (Player player : players) {
      names.add(player.getName());
    }
    return names;
  }

  /**
   * Returns the list of available colors for players to choose from.
   *
   * @return the list of available colors
   */
  public List<String> getAvailableColors() {
    return availableColors;
  }

  public abstract String getRules();
}
