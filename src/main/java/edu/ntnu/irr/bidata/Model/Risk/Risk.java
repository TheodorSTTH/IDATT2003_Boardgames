package edu.ntnu.irr.bidata.Model.Risk;

import java.lang.reflect.Array;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Controler.UIRisk;
import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Wiew.PopUp;
import edu.ntnu.irr.bidata.Model.Dice;
import java.util.ArrayList;
import java.util.List;


public class Risk extends Game {
    private final BoardRisk board;
    private int tropesAvailable = 0;
    private Dice oneDice = new Dice(6, 1);
    private Dice twoDice = new Dice(6, 2);
    private Dice treDice = new Dice(6, 3);

    public Risk(int amountOfPlayers, String gameName) {
        super(amountOfPlayers, gameName);
        this.board = new BoardRisk();
    }

    @Override
    protected void init() {
        super.init();
        board.setUpBoard(getPlayerNames());
        UIRisk.setRisk(this);
        UIRisk.toRiskGamePage(board.getCountries());
        startTurn();
    }

    public void startSavedGame() {
    }

    private void startTurn() {
        while (board.hasLost(currentPlayer.getName())) {
            currentPlayer = getNextPlayer();
        }
        tropesAvailable = board.NewTropes(currentPlayer.getName());
        UIRisk.openPlaceTropesMenu(tropesAvailable, board.getCountrysControldByPlayer(currentPlayer.getName()));

    }

    public void endTurn() {
        if (board.hasLost(currentPlayer.getName())) {
            endGame(currentPlayer);
        }
        currentPlayer = getNextPlayer();
        startTurn();
    }

    public boolean placeTropes(String Conteris, int tropesPlased) {
        if (tropesPlased <= tropesAvailable) {
            board.placeTropes(Conteris, tropesPlased);
            tropesAvailable -= tropesPlased;
            if (tropesAvailable == 0) {
                UIRisk.openAttackMenu(board.getAttackOptions(currentPlayer.getName()));
                return true;
            }
            return true;
        }
        return false;
    }

    private boolean attack(String attacker, String defender) {
        List<Integer> attackRolls;
        List<Integer> defendRolls;

        if (board.getUnits(attacker) > 3) {
            attackRolls = treDice.rollSet();
        } else if (board.getUnits(attacker) == 3) {
            attackRolls = twoDice.rollSet();
        } else {
            attackRolls = oneDice.rollSet();
        }

        if (board.getUnits(defender) > 1) {
            defendRolls = twoDice.rollSet();
        } else {
            defendRolls = oneDice.rollSet();
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
            board.takeControlOfCountry(defender, currentPlayer.getName());
            board.tranferTroops(attacker, defender, PopUp.promptForNumberInRange(board.getUnits(attacker) - 1));
            return true;

        } else {
            return false;
        }

    }
    
    public void attackOnce(String attacker, String defender) {
        attack(attacker, defender);
        UIRisk.updateAttackMenu(board.getAttackOptions(currentPlayer.getName()));

    }
    
    public void attackUntilWin(String attacker, String defender) {
        while (!attack(attacker, defender)) {
        }
        UIRisk.updateAttackMenu(board.getAttackOptions(currentPlayer.getName()));
    }

    public BoardRisk getBoard() {
        return board;
    }

    @Override
    public String getGameType() {
        return "Risk";
    }

    public List<Country> getCountriesContrldByActivePlayer() {
        return board.getCountrysControldByPlayer(currentPlayer.getName());
    }

    public boolean transferTroops(String from, String to, int amount) {
        if (board.getUnits(from) > amount) {
            board.tranferTroops(from, to, amount);
            return true;
        } else {
            PopUp.showInfo("To few tropes", "You can not transfer more tropes than you have available");
            return false;
        }
    }
}
