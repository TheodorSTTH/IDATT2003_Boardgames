package edu.ntnu.irr.bidata.model.newlogic.snakesandladders;

import edu.ntnu.irr.bidata.model.newlogic.Action;
import edu.ntnu.irr.bidata.model.newlogic.Player;
import java.util.ArrayList;
import java.util.HashMap;

public class SnakesAndLaddersBoard {
  private Tile firstTile;
  private Tile lastTile;
  private int length;

  public SnakesAndLaddersBoard() {
    this.length = 0;
  }

  public void placeCreatureOnStartingTile(ArrayList<Creature> creatures) {
    firstTile.addCreatures(creatures);
  }

  private Tile getTilePlayerIsOn(Player player) {
    Tile tile = firstTile;
    while (tile.getNext() != null && !tile.doesSpaceHaveCreature(player)) {
      tile = tile.getNext();
    }
    return tile;
  }

  public void movePlayer(Player player, int amountOfSteps) {
    Tile currentTile = getTilePlayerIsOn(player);

    // Move
    for (int i = 0; i < amountOfSteps; i++) {
      currentTile = currentTile.getNext();
    }

    // Perform actions
    Creature creatureOwnedByPlayer = currentTile.getCreatureOwnedByPlayer(player);
    for (Action action : currentTile.getActionsOnSpace()) {
      action.perform(creatureOwnedByPlayer);
    }
  }

  public void addTile(Tile tile) {
    if (firstTile == null) {
      firstTile = tile;
    } else {
      lastTile.setNext(tile);
    }
    lastTile = tile;
    length++;
  }

  public Tile getFirstTile() {
    return firstTile;
  }

  public Tile getLastTile() {
    return lastTile;
  }

  /**
   * Returns the positions of the players on the board.
   *
   * @return a map of players and their positions on the board
   */
  public HashMap<Player, Integer> getPlayerPositions() {
    HashMap<Player, Integer> playerPositions = new HashMap<>();
    Tile currentTile = firstTile;
    while (currentTile.getNext() != null) {
      for (Creature creature : currentTile.getCreaturesOnSpace()) {
        playerPositions.put(creature.getOwner(), currentTile.getName());
      }
      currentTile = currentTile.getNext();
    }
    return playerPositions;
  }
}
