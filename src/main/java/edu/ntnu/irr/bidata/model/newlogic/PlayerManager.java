package edu.ntnu.irr.bidata.model.newlogic;

import java.util.ArrayList;

public class PlayerManager {
  private final ArrayList<Player> players;
  public int currentPlayerIndex;

  public PlayerManager(ArrayList<Player> players) {
    this.players = players;
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

  public void addPlayer(Player player) {
    players.add(player);
  }
}
