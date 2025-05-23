package edu.ntnu.irr.bidata.model.newlogic.risk;

import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.interfaces.observer.Subject;
import edu.ntnu.irr.bidata.model.newlogic.Dice;
import edu.ntnu.irr.bidata.model.newlogic.Game;
import edu.ntnu.irr.bidata.model.newlogic.PlayerManager;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class RiskGame extends Game implements Subject<Pair<Dice, Dice>> {
  private final ArrayList<Observer<Pair<Dice, Dice>>> allObservers;
  private final RiskBoard riskBoard;
  private final Dice attackDice = new Dice(3, 6);
  private final Dice defenceDice = new Dice(2, 6);
  private int troopesAvailable = 0;

  public RiskGame(PlayerManager playerManager, RiskBoard riskBoard) {
    super("Risk", playerManager);
    this.allObservers = new ArrayList<>();
    this.riskBoard = riskBoard;
  }

  public void transferTroops(Country from, Country to, int amount) {
    riskBoard.transferTroops(from, to, amount);
  }

  public void placeTroops(Country target, int amount) {
    if (target.getArmy().getOwner().equals(playerManager.getCurrentPlayer())) {
      target.getArmy().addAmountOfTroops(amount);
    }
  }

  public void attack(Country attacker, Country defender) throws IllegalArgumentException{
    if (riskBoard.isControlledBySamePlayer(attacker, defender)) {
      throw new IllegalArgumentException("You can not attack your own country");
    }

    List<Integer> attackRolls;
    List<Integer> defendRolls;

    int attackerTroopCount = attacker.getArmy().getTroopCount();
    if (attackerTroopCount > 3) {
      attackRolls = attackDice.rollSetOfDice(3);
    } else if (attackerTroopCount == 3) {
      attackRolls = attackDice.rollSetOfDice(2);
    } else if (attackerTroopCount == 2) {
      attackRolls = attackDice.rollSetOfDice(1);
    } else {
      throw new IllegalArgumentException("Too few troops, you cannot attack from a country with only one troops");
    }

    int defenderTroopCount = defender.getArmy().getTroopCount();
    if (defenderTroopCount > 1) {
      defendRolls = defenceDice.rollSetOfDice(2);
    } else if (defenderTroopCount == 1) {
      defendRolls = defenceDice.rollSetOfDice(1);
    } else {
      throw new IllegalArgumentException("You can not attack a country with no troops");
    }

    attackRolls.sort((a, b) -> b - a);
    defendRolls.sort((a, b) -> b - a);

    int comparisons = Math.min(attackRolls.size(), defendRolls.size());

    for (int i = 0; i < comparisons; i++) {
      if (attackRolls.get(i) > defendRolls.get(i)) {
        defender.getArmy().removeTroops(1);
      } else {
        attacker.getArmy().removeTroops(1);
      }
    }

    if (defender.getArmy().getTroopCount() == 0) {
      riskBoard.takeControlOfCountry(defender, attacker.getArmy().getOwner());
      // TODO: Prompt user to transfer amount of troops
    }

    notifyObservers(new Pair<>(attackDice, defenceDice));
  }

  /**
   * Attack until you don't have any units to attack with from your given country, or you won the
   * attack.
   *
   * @param attacker The attacking country
   * @param defender The defending country
   */
  public void attackUntilResult(Country attacker, Country defender) {
    while (attacker.getArmy().getTroopCount() >= 2
          && defender.getArmy().getTroopCount() != 0
          && !riskBoard.isControlledBySamePlayer(attacker, defender)
    ) {
      attack(attacker, defender);
    }
  }

  /**
   * Returns a list of countries controlled by the active player.
   *
   * @return List of countries controlled by the current player
   */
  public ArrayList<Country> getCountriesControlledByActivePlayer() {
    return riskBoard.getCountriesOwnedByPlayer(playerManager.getCurrentPlayer());
  }

  /**
   * Returns the number of available troops for the current player.
   *
   * @return The number of available troops
   */
  public int getTroopsAvailable() {
    return troopesAvailable;
  }

  /** Starts the player's turn by checking if they have lost and updating available troops. */
  private void startTurn() {
    while (riskBoard.hasLost(playerManager.getCurrentPlayer())) {
      playerManager.giveTurnToNextPlayer();
    }
    troopesAvailable = riskBoard.getAmountOfNewTroops(playerManager.getCurrentPlayer());
  }

  /** Ends the current player's turn and starts the next player's turn. */
  public void endTurn() {
    playerManager.giveTurnToNextPlayer();
    startTurn();
  }


  /**
   * Returns the countries that the current player can attack from.
   *
   * @return a list of countries the current player can attack from
   */
  public ArrayList<Country> getCountriesCurrentPlayerCanAttackFrom() {
    return riskBoard.getCountriesPlayerCanAttackFrom(playerManager.getCurrentPlayer());
  }

  /**
   * Returns the countries that the current player can attack from a given country.
   *
   * @param attackingCountry the country from which to attack
   * @return a list of countries the current player can attack from the given country
   */
  public List<Country> getCountriesCurrentPlayerCanAttackFromCountry(Country attackingCountry) {
    return riskBoard.getAttackOptionsOfCountry(attackingCountry);
  }

  /**
   * Registers an observer for this Risk game.
   *
   * @param o the observer to register
   */
  @Override
  public void registerObserver(Observer<Pair<Dice, Dice>> o) {
    allObservers.add(o);
  }

  public RiskBoard getBoard() {
    return this.riskBoard;
  }

  public ArrayList<Country> getCountriesCurrentPlayerCanMoveFrom() {
    return riskBoard.getCountriesPlayerCanMoveFrom(playerManager.getCurrentPlayer());
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
   * Is responsible for holding and return the rules of the game of Risk.
   *
   * @return Rules for the game of risk.
   * */
  public String getRules() {
    return "The rules of the game are as follows:\n"
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
        + "9. The goal is to conquer the entire world by eliminating all other players.";
  }
}
