package edu.ntnu.irr.bidata.Model.LadderGame;

import java.util.ArrayList;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Controler.UILaderGame;
import edu.ntnu.irr.bidata.Model.Die;
import edu.ntnu.irr.bidata.Model.Game;



public class LaderGame extends Game {
    private final BoardLaderGame board;
    private final Die die = new Die(6);

    public LaderGame(int amountOfPlayers, String gameName) {
        super(amountOfPlayers, gameName);
        this.board = new BoardLaderGame();
    }


    @Override
    public void init() {
        super.init();
        UILaderGame.setLadderGame(this);
        UI.toLaderGamePage();
    }

    @Override
    public void startSavedGame() {
        UILaderGame.setLadderGame(this);
        UI.toLaderGamePage();
    }

    public void takeAction() {
        currentPlayer.setCurrentTile(board.landOnTile(currentPlayer.getCurrentTile() + die.roll()));
        if (board.hasWone(currentPlayer.getCurrentTile())) {
            endGame(currentPlayer);
        }
        currentPlayer = getNextPlayer();
    }

    public BoardLaderGame getBoard() {
        return board;
    }

    @Override
    public String getGameType() {
        return "LaderGame";
    }
}

