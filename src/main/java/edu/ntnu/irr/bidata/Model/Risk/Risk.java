package edu.ntnu.irr.bidata.Model.Risk;

import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.Controler.UILaderGame;
import edu.ntnu.irr.bidata.Controler.UIRisk;
import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.View.LadderGameOverview.OverviewPage;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.Model.Dice;
import edu.ntnu.irr.bidata.View.RiskGame.RiskPage;
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

    public Risk(int amountOfPlayers, String gameName, ArrayList<Player> players, BoardRisk boardRisk, Player currentPlayer) {
        super(amountOfPlayers, gameName, players, currentPlayer);
        this.board = boardRisk;
    }



    @Override
    protected void init() {
        super.init();
        board.setUpBoard(getPlayerNames());
        UIRisk.setRisk(this);
        RiskPage riskPage = new RiskPage(this);
        UIRisk.setRiskPage(riskPage); // TODO: Avoid tight coupling
        NavigationManager.switchScene(riskPage);
        startTurn();
    }

    public void startSavedGame() {
        RiskPage riskPage = new RiskPage(this);
        UIRisk.setRiskPage(riskPage); // TODO: Avoid tight coupling
        UIRisk.setRisk(this);
        NavigationManager.switchScene(riskPage);
    }

    private void startTurn() {
        while (board.hasLost(currentPlayer.getName())) {
            currentPlayer = getNextPlayer();
        }
        tropesAvailable = board.NewTropes(currentPlayer.getName());
        // * Open place troops menu after this
    }

    public void endTurn() {
        if (board.hasLost(currentPlayer.getName())) {
            endGame(currentPlayer);
        }
        currentPlayer = getNextPlayer();
        startTurn();
    }

    public boolean placeTroops(String country, int troopsPlaced) {
        if (troopsPlaced <= tropesAvailable) {
            board.placeTropes(country, troopsPlaced);
            tropesAvailable -= troopsPlaced;
            if (tropesAvailable == 0) {
                // * Open attack menu
                return true;
            }
            return true;
        }
        return false;
    }

    public List<Country> getCountriesCurrentPlayerCanMoveFrom() {
        return getBoard().getCountrysControldByPlayer(getCurrentPlayer().getName())
            .stream().filter(country -> country.getArmies() > 1)
            .toList();
    }
    public List<Country> getCountriesCurrentPlayerCanAttackFrom() {
        return getBoard().getAttackOptions(getCurrentPlayer().getName()).keySet().stream().toList();
    }
    public List<Country> getCountriesCurrentPlayerCanAttackFromCountry(Country attackingCountry) {
        return getBoard().getAttackOptions(getCurrentPlayer().getName()).get(attackingCountry);
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
            board.transferTroops(attacker, defender, PopUp.promptForNumberInRange("How many trops do you want to move to the new country",board.getUnits(attacker) - 1));
            return true;

        } else {
            return false;
        }

    }
    
    public void attackOnce(String attacker, String defender) {
        attack(attacker, defender);
        // * Update attack menu
    }
    
    public void attackUntilResolt(String attacker, String defender) {
        while (!attack(attacker, defender) && board.getUnits(attacker) > 1) {
            // * Update attack menu
        }
        // Update attack menu
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
            board.transferTroops(from, to, amount);
            return true;
        } else {
            PopUp.showInfo("To few troops", "You can not transfer more tropes than you have available");
            return false;
        }
    }
}
