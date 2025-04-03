package edu.ntnu.irr.bidata.NewLogic.models;

import edu.ntnu.irr.bidata.Controler.Game;
import java.util.ArrayList;
import java.util.List;

/**
 * Set up Board, Dice and Players.
 * Iterate over game loop.
 * */
public class LadderGame extends AbstractGame<LadderPlayer> {
  /**
   * Returns the player which won.
   * */
  @Override
  public LadderPlayer getWinner() {
    LadderPlayer winner = null;
    for (LadderPlayer player : players) {
      if (player.getCurrentSpace().getNextTile() == null) {
        winner = player;
      }
    }
    return winner;
  }

  public LadderGame(ArrayList<LadderPlayer> players, Board<Integer, Tile> board, Dice dice) {
    super(players, board, dice);
    currentPlayer = players.getFirst();
  }

  /**
   * Plays a turn for the current player.
   * */
  @Override
  public void playTurn() {
    int diceRollResult = dice.rollAll();
    currentPlayer.move(diceRollResult);
    currentPlayer = getNextPlayer();
  }
}
