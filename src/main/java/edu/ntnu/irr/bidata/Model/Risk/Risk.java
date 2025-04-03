package edu.ntnu.irr.bidata.Model.Risk;

import edu.ntnu.irr.bidata.Controler.UIRisk;
import edu.ntnu.irr.bidata.Model.Game;

public class Risk extends Game {
    private final BoardRisk board;

    public Risk(int amountOfPlayers, String gameName) {
        super(amountOfPlayers, gameName);
        this.board = new BoardRisk();
    }

    @Override
    protected void init() {
        super.init();
        UIRisk.setRisk(this);
        UIRisk.toRiskGamePage();
    }

    public void startSavedGame() {
    }

    public boolean placeTropes(String Conteris, int tropesPlased) {
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
