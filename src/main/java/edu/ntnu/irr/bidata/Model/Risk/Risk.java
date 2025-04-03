package edu.ntnu.irr.bidata.Model.Risk;

import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.Risk.BoardRisk;

public class Risk extends Game {
    private final BoardRisk board;
    private int gameStep = 0;

    public Risk(int amountOfPlayers, String gameName) {
        super(amountOfPlayers, gameName);
        this.board = new BoardRisk();
    }

    @Override
    protected void init() {
        super.init();
        //UI.toRiskGamePage();
    }

    public void startSavedGame() {
    }

    public BoardRisk getBoard() {
        return board;
    }

    @Override
    public String getGameType() {
        return "Risk";
    }

    public void setGameStep(int gameStep) {
        this.gameStep = gameStep;
    }
}
