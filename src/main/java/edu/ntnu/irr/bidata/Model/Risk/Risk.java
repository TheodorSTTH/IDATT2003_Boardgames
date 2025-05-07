package edu.ntnu.irr.bidata.Model.Risk;

import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISubject;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.Model.Dice;
import edu.ntnu.irr.bidata.View.RiskGame.RiskPage;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class Risk extends Game implements ISubject<Pair<Dice, Dice>> {
  private final BoardRisk board;
  private int tropesAvailable = 0;
  private final Dice attackDice = new Dice(3, 6);
  private final Dice defenceDice = new Dice(2, 6);
  private final ArrayList<IObserver<Pair<Dice, Dice>>> allObservers;

  @Override
  public void registerObserver(IObserver<Pair<Dice, Dice>> o) {
    allObservers.add(o);
  }

  @Override
  public void removeObserver(IObserver<Pair<Dice, Dice>> o) {
    allObservers.remove(o);
  }

  @Override
  public void notifyObservers(Pair<Dice, Dice> dice) {
    for (IObserver<Pair<Dice, Dice>> observer : allObservers) {
      observer.update(dice);
    }
  }

  public Risk(int amountOfPlayers, String gameName) {
    super(amountOfPlayers, gameName);
    this.board = new BoardRisk();
    this.allObservers = new ArrayList<>();
  }

  public Dice getAttackDice() {
    return attackDice;
  }

  public Dice getDefenceDice() {
    return defenceDice;
  }

  public Risk(int amountOfPlayers, String gameName, ArrayList<Player> players, BoardRisk boardRisk,
      Player currentPlayer, int tropesAvailable) {
    super(amountOfPlayers, gameName, players, currentPlayer);
    this.board = boardRisk;
    this.tropesAvailable = tropesAvailable;
    this.allObservers = new ArrayList<>();
  }

  @Override
  protected void init() {
    super.init();
    board.setUpBoard(this.players);
    startTurn();
    RiskPage riskPage = new RiskPage(this);
    NavigationManager.navigate(riskPage);
    showRueles();
  }

  public void startSavedGame() {
    RiskPage riskPage = new RiskPage(this);
    NavigationManager.navigate(riskPage);
  }

  private void startTurn() {
    while (board.hasLost(currentPlayer)) {
      currentPlayer = getNextPlayer();
    }
    tropesAvailable = board.newTroops(currentPlayer);
    // * Open place troops menu after this
  }

  public void endTurn() {
    currentPlayer = getNextPlayer();
    startTurn();
  }

  public boolean placeTroops(String country, int troopsPlaced) {
    if (troopsPlaced <= tropesAvailable) {
      board.placeTroops(country, troopsPlaced);
      tropesAvailable -= troopsPlaced;
      if (tropesAvailable == 0) {
        // * Open attack menu
        return true;
      }
      return false;
    }
    PopUp.showInfo("To many troops", "You can not place more tropes than you have available");
    return false;
  }

  public List<Country> getCountriesCurrentPlayerCanMoveFrom() {
    return board.getCountriesControlledByPlayer(getCurrentPlayer())
        .stream().filter(country -> country.getArmies() > 1).toList();
  }

  public List<Country> getCountriesCurrentPlayerCanAttackFrom() {
    return board.getAttackOptions(getCurrentPlayer()).keySet().stream().toList();
  }

  public List<Country> getCountriesCurrentPlayerCanAttackFromCountry(Country attackingCountry) {
    return board.getAttackOptions(getCurrentPlayer()).get(attackingCountry);
  }

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
      board.transferTroops(attacker, defender, PopUp.promptForNumberInRange("Transfer troops",
          "How many trops do you want\n to transfer to " + defender, board.getUnits(attacker) - 1));
    }

    notifyObservers(new Pair<>(attackDice, defenceDice));
  }

  public void attackOnce(String attacker, String defender) {
    attack(attacker, defender);
    // * Update attack menu
  }

  /**
   * Attack until you don't have any units to attack with from your given country,
   * or you won the attack.
   *
   * @param attacker The attacking country
   * @param defender The defending country
   */
  public void attackUntilResult(String attacker, String defender) {
    while ((board.getUnits(attacker) >= 2) && (!board.controlledBySamePlayer(attacker, defender))) {
      attack(attacker, defender);
    }
  }

  public BoardRisk getBoard() {
    return board;
  }

  @Override
  public String getGameType() {
    return "Risk";
  }

  public List<Country> getCountriesControlledByActivePlayer() {
    return board.getCountriesControlledByPlayer(currentPlayer);
  }

  public boolean transferTroops(String from, String to, int amount) {
    if (board.getUnits(from) > amount) {
      board.transferTroops(from, to, amount);
      return true;
    } else {
      PopUp.showInfo("To few troops", "You can not transfer more tropes than you have available");
      return false;
    }
  }

  public int getTroopsAvailable() {
    return tropesAvailable;
  }

  public void showRueles() {
    PopUp.showScrollablePopup("Rules", "The rules of the game are as follows:\n"
        + "1. Players take turns in clockwise order.\n"
        + "2. You gain reinforcements each turn based on the number of territories you own, continent control, and other bonuses like controling a continet.\n"
        + "3. On your turn, can reinforce your own, counteries.\n"
        + "4. You can attack other players' territories.\n"
        + "5. You must have at least one tropp in each country.\n"
        + "6. Battles are resolved by rolling dice; the attacker can roll up to 3 dice, and the defender up to 2. You roll one dice for each trope you controle\n"
        + "7. The highest two dice are compared; ties go to the defender. Losers remove troops. They can lose one each\n"
        + "8. At the end of your turn, you may fortify by moving troops between two territories.\n"
        + "9. The goal is to conquer the entire world by eliminating all other players.");
  }
}