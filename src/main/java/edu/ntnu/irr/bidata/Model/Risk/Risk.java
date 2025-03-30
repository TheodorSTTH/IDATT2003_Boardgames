package edu.ntnu.irr.bidata.Model.Risk;

import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.Risk.BoardRisk;

public class Risk extends Game {
    private final BoardRisk board;

    public Risk(int amountOfPlayers, String gameName) {
        super(amountOfPlayers, gameName);
        this.board = new BoardRisk();
    }

    @Override
    protected void init() {
        super.init();
        //UI.toRiskGamePage();
    }

    @Override
    public void takeAction() {
    }

    public BoardRisk getBoard() {
        return board;
    }
}
