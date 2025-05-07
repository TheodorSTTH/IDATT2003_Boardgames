package edu.ntnu.irr.bidata.model.snakesandladders;

import edu.ntnu.irr.bidata.controler.NavigationManager;
import edu.ntnu.irr.bidata.model.Dice;
import edu.ntnu.irr.bidata.model.Game;
import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.interfaces.observer.Subject;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.snakesandladders.SnakesAndLaddersPage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The SnakesAndLadders class extends the Game class and represents the game of Snakes and Ladders.
 * It includes functionality for managing the game board, player actions, and game state. This class
 * also implements the Subject interface for observer pattern implementation.
 */
public class SnakesAndLadders extends Game implements Subject<SnakesAndLadders> {

  private BoardSnakesAndLadders board;
  private final Dice dice = new Dice(2, 6);
  private final ArrayList<Observer<SnakesAndLadders>> allObservers;

  /**
   * Registers an observer for this SnakesAndLadders game.
   *
   * @param o the observer to register
   */
  @Override
  public void registerObserver(Observer<SnakesAndLadders> o) {
    allObservers.add(o);
  }

  /**
   * Removes an observer from this SnakesAndLadders game.
   *
   * @param o the observer to remove
   */
  @Override
  public void removeObserver(Observer<SnakesAndLadders> o) {
    allObservers.remove(o);
  }

  /**
   * Notifies all observers about changes in the game.
   *
   * @param game the SnakesAndLadders game instance
   */
  @Override
  public void notifyObservers(SnakesAndLadders game) {
    for (Observer<SnakesAndLadders> observer : allObservers) {
      observer.update(game);
    }
  }

  /**
   * Returns the Dice used in this game.
   *
   * @return the Dice object
   */
  public Dice getDice() {
    return dice;
  }

  /**
   * Constructs a new SnakesAndLadders game with the specified number of players, game name, and
   * board type.
   *
   * @param amountOfPlayers the number of players
   * @param gameName the name of the game
   * @param boardType the type of the board
   */
  public SnakesAndLadders(int amountOfPlayers, String gameName, String boardType) {
    super(amountOfPlayers, gameName);
    this.board = new BoardSnakesAndLadders(boardType);
    this.allObservers = new ArrayList<>();
  }

  /**
   * Constructs a new SnakesAndLadders game with the specified parameters.
   *
   * @param amountOfPlayers the number of players
   * @param gameName the name of the game
   * @param players the players in the game
   * @param board the game board
   * @param currentPlayer the current player
   */
  public SnakesAndLadders(
      int amountOfPlayers,
      String gameName,
      ArrayList<Player> players,
      BoardSnakesAndLadders board,
      Player currentPlayer) {
    super(amountOfPlayers, gameName, players, currentPlayer);
    this.board = board;
    this.allObservers = new ArrayList<>();
  }

  /**
   * Initializes the game by setting the players, navigating to the SnakesAndLadders page, and
   * showing the game rules.
   */
  @Override
  protected void init() {
    super.init();
    board.setPlayers(players);
    SnakesAndLaddersPage snakesAndLaddersPage = new SnakesAndLaddersPage(this);
    NavigationManager.navigate(snakesAndLaddersPage);
    showRules();
  }

  /** Starts a saved game by navigating to the SnakesAndLadders page. */
  public void startSavedGame() {
    NavigationManager.navigate(new SnakesAndLaddersPage(this));
  }

  /**
   * Makes the current player take an action by rolling the dice and moving on the board. Then
   * notifies the observers about the game state change.
   */
  public void takeAction() {
    board.move(currentPlayer, dice.roll());
    currentPlayer = getNextPlayer();
    notifyObservers(this);
  }

  /**
   * Returns the board of the game.
   *
   * @return the game board
   */
  public BoardSnakesAndLadders getBoard() {
    return board;
  }

  /**
   * Returns the game type, which is "SnakesAndLadders".
   *
   * @return the game type
   */
  @Override
  public String getGameType() {
    return "SnakesAndLadders";
  }

  /**
   * Returns the positions of the players on the board.
   *
   * @return a map of players and their positions on the board
   */
  public HashMap<Player, Integer> getPlayerPositions() {
    HashMap<Player, Integer> playerPositions = new HashMap<Player, Integer>();
    for (String playerName : board.getPlayerPositions().keySet()) {
      for (Player player : players) {
        if (player.getName().equals(playerName)) {
          playerPositions.put(player, board.getPlayerPositions().get(playerName));
        }
      }
    }
    return playerPositions;
  }

  /** Displays the game rules in a pop-up window. */
  public void showRules() {
    PopUp.showScrollablePopup(
        "Rules",
        "The rules of the game are as follows:\n"
            + "1. Players take turns rolling two dice.\n"
            + "2. The player moves their token forward the number of spaces shown on the dice.\n"
            + "3. If a player lands on a snake, they must slide down to the tail of the snake.\n"
            + "4. If a player lands on a ladder, they can climb up to the top of the ladder.\n"
            + "5. If a player lands on a quiz tile, they must answer a if they answer correctly"
            + " they move 3 forward, if they get wrong they move 3 back.\n"
            + "6. The first player to reach the end of the board wins.");
  }
}
