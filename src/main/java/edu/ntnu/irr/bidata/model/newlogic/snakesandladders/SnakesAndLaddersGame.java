package edu.ntnu.irr.bidata.model.newlogic.snakesandladders;

import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.interfaces.observer.Subject;
import edu.ntnu.irr.bidata.model.newlogic.Player;
import edu.ntnu.irr.bidata.model.newlogic.Dice;
import edu.ntnu.irr.bidata.model.newlogic.Game;
import edu.ntnu.irr.bidata.model.newlogic.PlayerManager;
import java.util.ArrayList;

public class SnakesAndLaddersGame extends Game implements Subject<SnakesAndLaddersGame>  {
  private final Dice dice;
  private final SnakesAndLaddersBoard board;
  private final ArrayList<Observer<SnakesAndLaddersGame>> allObservers;

  public SnakesAndLaddersGame(PlayerManager playerManager, SnakesAndLaddersBoard board) {
    super("Snakes and Ladders", playerManager);
    this.dice = new Dice(2, 6);
    this.board = board;
    this.allObservers = new ArrayList<>();
  }

  public boolean hasWon(Player player) {
    if (this.board.getPlayerTile(player) == null) return false; // Game hasn't started
    return this.board.getPlayerTile(player).getNext() == null;
  }

  public void takeTurn() {
    playerManager.getCurrentPlayer();
    board.movePlayer(playerManager.getCurrentPlayer(), dice.roll());
    playerManager.giveTurnToNextPlayer();
    notifyObservers(this);
  }

  public Dice getDice() {
    return dice;
  }

  /**
   * Registers an observer for this SnakesAndLadders game.
   *
   * @param o the observer to register
   */
  @Override
  public void registerObserver(Observer<SnakesAndLaddersGame> o) {
    allObservers.add(o);
  }

  /**
   * Removes an observer from this SnakesAndLadders game.
   *
   * @param o the observer to remove
   */
  @Override
  public void removeObserver(Observer<SnakesAndLaddersGame> o) {
    allObservers.remove(o);
  }

  /**
   * Notifies all observers about changes in the game.
   *
   * @param game the SnakesAndLadders game instance
   */
  @Override
  public void notifyObservers(SnakesAndLaddersGame game) {
    for (Observer<SnakesAndLaddersGame> observer : allObservers) {
      observer.update(game);
    }
  }


  /**
   * Returns the positions of the players on the board.
   *
   * @return a map of players and their positions on the board
   */
  public ArrayList<Tile> getTilesWithPlayers() {
    return board.getTilesWithPlayers();
  }

  /**
   * Starts the game when nothing has been done. Plays two turns and places players on start tile
   * */
  public void start() {
    for (Player player : playerManager.getPlayers()) {
      this.getBoard().placeCreatureOnStartingTile(new Creature(player));
    }
    takeTurn();
    takeTurn();
  }

  /**
   * Returns the board
   * */
  public SnakesAndLaddersBoard getBoard() {
    return board;
  }

  /**
   * Returns a string containing the rules of the game.
   *
   * <p>The rules describe the gameplay mechanics, including:
   *
   * <ul>
   *   <li>Taking turns and rolling dice
   *   <li>Moving tokens based on dice rolls
   *   <li>Special actions when landing on snakes, ladders, or quiz tiles
   *   <li>Winning condition
   * </ul>
   *
   * @return a formatted string that outlines the rules of the game.
   */
  public String getRules() {
    return "The rules of the game are as follows:\n"
        + "1. Players take turns rolling two dice.\n"
        + "2. The player moves their token forward the number of spaces shown on the dice.\n"
        + "3. If a player lands on a snake, they must slide down to the tail of the snake.\n"
        + "4. If a player lands on a ladder, they can climb up to the top of the ladder.\n"
        + "5. If a player lands on a quiz tile, they must answer a if they answer correctly"
        + " they move 3 forward, if they get wrong they move 3 back.\n"
        + "6. The first player to reach the end of the board wins.";
  }
}
