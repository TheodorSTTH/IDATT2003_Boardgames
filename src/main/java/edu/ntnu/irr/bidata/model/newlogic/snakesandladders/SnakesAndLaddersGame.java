package edu.ntnu.irr.bidata.model.newlogic.snakesandladders;

import edu.ntnu.irr.bidata.model.newlogic.Player;
import edu.ntnu.irr.bidata.model.newlogic.Dice;
import edu.ntnu.irr.bidata.model.newlogic.Game;
import edu.ntnu.irr.bidata.model.newlogic.PlayerManager;
import java.util.HashMap;

public class SnakesAndLaddersGame extends Game {
  private final Dice dice;
  private final SnakesAndLaddersBoard board;

  public SnakesAndLaddersGame(PlayerManager playerManager, SnakesAndLaddersBoard board) {
    super("Snakes and Ladders", playerManager);
    this.dice = new Dice(2, 6);
    this.board = board;
  }

  public void takeTurn() {
    playerManager.getCurrentPlayer();
    board.movePlayer(playerManager.getCurrentPlayer(), dice.roll());
    playerManager.giveTurnToNextPlayer();
  }

  public Dice getDice() {
    return dice;
  }

  /**
   * Returns the positions of the players on the board.
   *
   * @return a map of players and their positions on the board
   */
  public HashMap<Player, Integer> getPlayerPositions() {
    return board.getPlayerPositions();
  }

  /**
   * Returns the board
   * */
  public SnakesAndLaddersBoard getBoard() {
    return board;
  }
}
