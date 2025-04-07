package edu.ntnu.irr.bidata.Model.Risk;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Controler.UIRisk;
import edu.ntnu.irr.bidata.Model.Game;

public class Risk extends Game {
    private final BoardRisk board;
    private int tropesAvailable = 0;

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
    }

    public void startSavedGame() {
    }

    private void startTurn() {
        tropesAvailable = board.NewTropes(currentPlayer.getName());
        UIRisk.openPlaceTropesMenu(tropesAvailable);

    }

    public boolean placeTropes(String Conteris, int tropesPlased) {
        if (tropesPlased <= tropesAvailable) {
            board.placeTropes(Conteris, tropesPlased);
            tropesAvailable -= tropesPlased;
            if (tropesAvailable == 0) {
                UIRisk.openAttackMenu();
                return true;
            }
            return true;
        }
        return false;
    }

    public BoardRisk getBoard() {
        return board;
    }

    @Override
    public String getGameType() {
        return "Risk";
    }
}
