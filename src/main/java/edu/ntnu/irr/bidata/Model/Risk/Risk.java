package edu.ntnu.irr.bidata.model.risk;

import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.View.RiskGame.RiskPage;
import edu.ntnu.irr.bidata.model.Dice;
import edu.ntnu.irr.bidata.model.Game;
import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.interfaces.observer.Subject;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 * The Risk class extends the Game class and represents the Risk game. It manages the board, dice
 * rolls, player actions, and game state. This class also implements the Subject interface to notify
 * observers about changes in the game.
 */
public class Risk extends Game implements Subject<Pair<Dice, Dice>> {
  private final BoardRisk board;
  private int troopesAvailable = 0;
  private final Dice attackDice = new Dice(3, 6);
  private final Dice defenceDice = new Dice(2, 6);
  private final ArrayList<Observer<Pair<Dice, Dice>>> allObservers;

  /**
   * Registers an observer for this Risk game.
   *
   * @param o the observer to register
   */
  @Override
  public void registerObserver(Observer<Pair<Dice, Dice>> o) {
    allObservers.add(o);
  }

  /**
   * Removes an observer from this Risk game.
   *
   * @param o the observer to remove
   */
  @Override
  public void removeObserver(Observer<Pair<Dice, Dice>> o) {
    allObservers.remove(o);
  }

  /**
   * Notifies all observers about changes in the game state.
   *
   * @param dice the pair of dice to notify the observers with
   */
  @Override
  public void notifyObservers(Pair<Dice, Dice> dice) {
    for (Observer<Pair<Dice, Dice>> observer : allObservers) {
      observer.update(dice);
    }
  }

  /**
   * Constructs a new Risk game with the specified number of players and game name.
   *
   * @param amountOfPlayers the number of players
   * @param gameName the name of the game
   */
  public Risk(int amountOfPlayers, String gameName) {
    super(amountOfPlayers, gameName);
    this.board = new BoardRisk();
    this.allObservers = new ArrayList<>();
  }

  /**
   * Returns the attack dice used in this game.
   *
   * @return the attack dice
   */
  public Dice getAttackDice() {
    return attackDice;
  }

  /**
   * Returns the defence dice used in this game.
   *
   * @return the defence dice
   */
  public Dice getDefenceDice() {
    return defenceDice;
  }

  /**
   * Constructs a new Risk game with the specified parameters.
   *
   * @param amountOfPlayers the number of players
   * @param gameName the name of the game
   * @param players the list of players
   * @param boardRisk the game board
   * @param currentPlayer the current player
   * @param troopesAvailable the number of troops available
   */
  public Risk(
      int amountOfPlayers,
      String gameName,
      ArrayList<Player> players,
      BoardRisk boardRisk,
      Player currentPlayer,
      int troopesAvailable) {
    super(amountOfPlayers, gameName, players, currentPlayer);
    this.board = boardRisk;
    this.troopesAvailable = troopesAvailable;
    this.allObservers = new ArrayList<>();
  }

  /** Initializes the game by setting up the board, starting the turn, and showing the rules. */
  @Override
  protected void init() {
    super.init();
    board.setUpBoard(this.players);
    startTurn();
    RiskPage riskPage = new RiskPage(this);
    NavigationManager.navigate(riskPage);
    showRules();
  }

  /** Starts a saved game by navigating to the Risk page. */
  public void startSavedGame() {
    RiskPage riskPage = new RiskPage(this);
    NavigationManager.navigate(riskPage);
  }

  /** Starts the player's turn by checking if they have lost and updating available troops. */
  private void startTurn() {
    while (board.hasLost(currentPlayer)) {
      currentPlayer = getNextPlayer();
    }
    troopesAvailable = board.newTroops(currentPlayer);
    // * Open place troops menu after this
  }

  /** Ends the current player's turn and starts the next player's turn. */
  public void endTurn() {
    currentPlayer = getNextPlayer();
    startTurn();
  }

  /**
   * Places troops for the current player in a specified country.
   *
   * @param country the country to place troops in
   * @param troopsPlaced the number of troops to place
   * @return true if troops were placed successfully, false otherwise
   */
  public boolean placeTroops(String country, int troopsPlaced) {
    if (troopsPlaced <= troopesAvailable) {
      board.placeTroops(country, troopsPlaced);
      troopesAvailable -= troopsPlaced;
      if (troopesAvailable == 0) {
        // * Open attack menu
        return true;
      }
      return false;
    }
    PopUp.showInfo("Too many troops", "You can not place more troopss than you have available");
    return false;
  }

  /**
   * Returns the countries that the current player can move troops from.
   *
   * @return a list of countries the current player can move from
   */
  public List<Country> getCountriesCurrentPlayerCanMoveFrom() {
    return board.getCountriesControlledByPlayer(getCurrentPlayer()).stream()
        .filter(country -> country.getArmies() > 1)
        .toList();
  }

  /**
   * Returns the countries that the current player can attack from.
   *
   * @return a list of countries the current player can attack from
   */
  public List<Country> getCountriesCurrentPlayerCanAttackFrom() {
    return board.getAttackOptions(getCurrentPlayer()).keySet().stream().toList();
  }

  /**
   * Returns the countries that the current player can attack from a given country.
   *
   * @param attackingCountry the country from which to attack
   * @return a list of countries the current player can attack from the given country
   */
  public List<Country> getCountriesCurrentPlayerCanAttackFromCountry(Country attackingCountry) {
    return board.getAttackOptions(getCurrentPlayer()).get(attackingCountry);
  }

  /**
   * Performs a single attack action from the attacker to the defender and updates the attack menu.
   * This method wraps the basic attack method and allows for one attack attempt.
   *
   * @param attacker The country initiating the attack
   * @param defender The country being attacked
   */
  public void attackOnce(String attacker, String defender) {
    attack(attacker, defender);
    // * Update attack menu
  }

  /**
   * Attack until you don't have any units to attack with from your given country, or you won the
   * attack.
   *
   * @param attacker The attacking country
   * @param defender The defending country
   */
  public void attackUntilResult(String attacker, String defender) {
    while ((board.getUnits(attacker) >= 2) && (!board.controlledBySamePlayer(attacker, defender))) {
      attack(attacker, defender);
    }
  }

  /**
   * Performs an attack between two countries.
   *
   * @param attacker the attacking country
   * @param defender the defending country
   */
  private void attack(String attacker, String defender) {
    if (board.controlledBySamePlayer(attacker, defender)) {
      PopUp.showInfo("You can not attack your own country", "You can not attack your own country");
      return;
    }

    List<Integer> attackRolls;
    List<Integer> defendRolls;

    if (board.getUnits(attacker) > 3) {
      attackRolls = attackDice.rollSetOfDice(3);
    } else if (board.getUnits(attacker) == 3) {
      attackRolls = attackDice.rollSetOfDice(2);
    } else if (board.getUnits(attacker) == 2) {
      attackRolls = attackDice.rollSetOfDice(1);
    } else {
      PopUp.showInfo("To few troops", "You can not attack from a country with only one troops");
      return;
    }

    if (board.getUnits(defender) > 1) {
      defendRolls = defenceDice.rollSetOfDice(2);
    } else if (board.getUnits(defender) == 1) {
      defendRolls = defenceDice.rollSetOfDice(1);
    } else {
      PopUp.showInfo("To few troops", "You can not attack a country with no troops");
      return;
    }

    attackRolls.sort((a, b) -> b - a);
    defendRolls.sort((a, b) -> b - a);

    int comparisons = Math.min(attackRolls.size(), defendRolls.size());

    for (int i = 0; i < comparisons; i++) {
      if (attackRolls.get(i) > defendRolls.get(i)) {
        board.removeTroops(defender, 1);
      } else {
        board.removeTroops(attacker, 1);
      }
    }

    if (board.getUnits(defender) == 0) {
      board.takeControlOfCountry(defender, currentPlayer);
      board.transferTroops(
          attacker,
          defender,
          PopUp.promptForNumberInRange(
              "Transfer troops",
              "How many trops do you want\n to transfer to " + defender,
              board.getUnits(attacker) - 1));
    }

    notifyObservers(new Pair<>(attackDice, defenceDice));
  }

  public BoardRisk getBoard() {
    return board;
  }

  @Override
  public String getGameType() {
    return "Risk";
  }

  /**
   * Returns a list of countries controlled by the active player.
   *
   * @return List of countries controlled by the current player
   */
  public List<Country> getCountriesControlledByActivePlayer() {
    return board.getCountriesControlledByPlayer(currentPlayer);
  }

  /**
   * Transfers a specified number of troops from one country to another.
   *
   * @param from The country to transfer troops from
   * @param to The country to transfer troops to
   * @param amount The number of troops to transfer
   * @return True if the transfer was successful, false otherwise
   */
  public boolean transferTroops(String from, String to, int amount) {
    if (board.getUnits(from) > amount) {
      board.transferTroops(from, to, amount);
      return true;
    } else {
      PopUp.showInfo("Too few troops", "You cannot transfer more troops than you have available");
      return false;
    }
  }

  /**
   * Returns the number of available troops for the current player.
   *
   * @return The number of available troops
   */
  public int getTroopsAvailable() {
    return troopesAvailable;
  }

  /** Shows the rules of the game in a popup. */
  public void showRules() {
    PopUp.showScrollablePopup(
        "Rules",
        "The rules of the game are as follows:\n"
            + "1. Players take turns in clockwise order.\n"
            + "2. You gain reinforcements each turn based on the number of territories you own, "
            + "continent control, and other bonuses like controlling a continent.\n"
            + "3. On your turn, you can reinforce your own countries.\n"
            + "4. You can attack other players' territories.\n"
            + "5. You must have at least one troop in each country.\n"
            + "6. Battles are resolved by rolling dice; the attacker can roll up to 3 dice, and the"
            + " defender up to 2. You roll one die for each troop you control.\n"
            + "7. The highest two dice are compared; ties go to the defender. Losers remove troops."
            + " They can lose one each.\n"
            + "8. At the end of your turn, you may fortify by moving troops between two "
            + "territories.\n"
            + "9. The goal is to conquer the entire world by eliminating all other players.");
  }
}
