package edu.ntnu.irr.bidata.Controler;

import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.BoardRisk;

public class Risk extends Game {
    private final BoardRisk board;

    public Risk(int amountOfPlayers) {
        super(amountOfPlayers);
        this.board = new BoardRisk();
    }

    @Override
    protected void init() {
        super.init();
        //UI.toRiskGamePage();
    }
}
