package edu.ntnu.irr.bidata.model.newlogic;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class PlayerManager {
  private final ArrayList<Player> players;
  public int currentPlayerIndex;
  private final int maxAmountOfPlayers = 5;
  private ArrayList<String> availableColors = new ArrayList<>(List.of("red", "orange", "green", "yellow", "white"));
  private final int selectedAmountOfPlayers;

  public PlayerManager(ArrayList<Player> players, int selectedAmountOfPlayers) {
    this.players = players;
    this.selectedAmountOfPlayers = selectedAmountOfPlayers;
  }

  public Player getCurrentPlayer() {
    return players.get(currentPlayerIndex);
  }

  public void giveTurnToNextPlayer() {
    if (currentPlayerIndex < players.size() - 1) {
      currentPlayerIndex++;
    } else {
      currentPlayerIndex = 0;
    }
  }

  private List<String> getPlayerNames() {
    List<String> names = new ArrayList<>();
    for (Player player : players) {
      names.add(player.getName());
    }
    return names;
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

    if (players.size() >= maxAmountOfPlayers) {
      throw new IllegalArgumentException(
          "You cannot add more players to the game. Max amount of players: " + maxAmountOfPlayers);
    }

    if (!availableColors.contains(color)) {
      throw new IllegalArgumentException("Figure color does not exist");
    }

    // Add the new player to the game
    players.add(new Player(name, age, color));

    // Remove the chosen color from the list of available colors
    availableColors.remove(color);

    // If the number of players reaches the limit, initialize the game
    sortPlayersByAge();
    return true;
  }

  /** Sorts the players by age in ascending order. */
  public void sortPlayersByAge() {
    players.sort((a, b) -> Integer.compare(a.getAge(), b.getAge()));
  }

  public ArrayList<String> getAvailableColors() {
    return availableColors;
  }

  public ArrayList<Player> getPlayers() {
    return players;
  }

  public int getSelectedAmountOfPlayers() {
    return selectedAmountOfPlayers;
  }
}
