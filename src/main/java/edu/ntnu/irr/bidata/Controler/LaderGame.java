package edu.ntnu.irr.bidata.Controler;

import edu.ntnu.irr.bidata.Controler.Game;
import edu.ntnu.irr.bidata.Model.BoardLaderGame;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.Die;
import java.util.ArrayList;



public class LaderGame extends Game {
    private final BoardLaderGame board;

    public LaderGame(int amountOfPlayers, String gameName) {
        super(amountOfPlayers, gameName);
        this.board = new BoardLaderGame();
    }


    @Override
    protected void init() {
        super.init();
        UI.toLaderGamePage();
    }

    public void takeAction() {
        currentPlayer.setCurrentTile(board.landOnTile(currentPlayer.getCurrentTile() + Die.roll()));
        if (board.hasWone(currentPlayer.getCurrentTile())) {
            endGame(currentPlayer);
        }
        currentPlayer = getNextPlayer();
    }

    public BoardLaderGame getBoard() {
        return board;
    }

}

