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

  public void placeCreatureOnStartingTile(Creature creature) {
    firstTile.addCreature(creature);
  }

  private Tile getTilePlayerIsOn(Player player) {
    Tile tile = firstTile;
    while (tile.getNext() != null && !tile.doesSpaceHaveCreature(player)) {
      tile = tile.getNext();
    }
    return tile;
  }

  public void movePlayer(Player player, int amountOfSteps) {
    Tile startTile = getTilePlayerIsOn(player);
    Tile currentTile = getTilePlayerIsOn(player);

    // Move
    for (int i = 0; i < amountOfSteps; i++) {
      if (currentTile.getNext() == null) {
        break;
      } else if (currentTile == null) {
        currentTile = firstTile;
      } else {
        currentTile = currentTile.getNext();
      }
    }

    Creature playerCreature = startTile.getCreaturesOnSpace().stream().filter(creature -> creature.getOwner().equals(player)).findFirst().orElseThrow();
    currentTile.addCreature(playerCreature);
    startTile.removeCreature(playerCreature);

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

  public ArrayList<Action> getActions() {
    ArrayList<Action> actions = new ArrayList<>();
    Tile currentTile = firstTile;
    while (currentTile.getNext() != null) {
      actions.addAll(currentTile.getActionsOnSpace());
      currentTile = currentTile.getNext();
    }
    return actions;
  }

  /**
   * Returns the positions of the players on the board.
   *
   * @return a map of players and their positions on the board
   */
  public ArrayList<Tile> getTilesWithPlayers() {
    ArrayList<Tile> tilesWithPlayers = new ArrayList<>();
    Tile currentTile = firstTile;
    while (currentTile.getNext() != null) {
      if (!currentTile.getCreaturesOnSpace().isEmpty()) {
        tilesWithPlayers.add(currentTile);
        System.out.println("BALLE: " + currentTile.getName());
      }
      currentTile = currentTile.getNext();
    }
    return tilesWithPlayers;
  }

  public Tile getPlayerTile(Player player) {
    Tile playerTile = null;
    Tile currentTile = firstTile;
    while (currentTile.getNext() != null && playerTile == null) {
      for (Creature creature : currentTile.getCreaturesOnSpace()) {
        if (creature.getOwner().equals(player)) {
          playerTile = currentTile;
          break;
        }
      }
      currentTile = currentTile.getNext();
    }
    return playerTile;
  }
}
