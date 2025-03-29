package edu.ntnu.irr.bidata.Controler;

import edu.ntnu.irr.bidata.Controler.Game;
import edu.ntnu.irr.bidata.Model.BoardLaderGame;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.Die;
import java.util.ArrayList;



public class LaderGame extends Game {
    private final BoardLaderGame board;

    public LaderGame(int amountOfPlayers) {
        super(amountOfPlayers);
        this.board = new BoardLaderGame();
    }

    @Override
    protected void init() {
        super.init();
    }

    public void takeTurn() {
        currentPlayer.setCurrentTile(board.landOnTile(currentPlayer.getCurrentTile() + Die.roll(6)));
        if (board.isOnOreAftherEndTile(currentPlayer.getCurrentTile())) {
            endGame(currentPlayer);
        }
        currentPlayer = getNextPlayer(currentPlayer);
        if (board.isOnOreAftherEndTile(currentPlayer.getCurrentTile())) {
            endGame(currentPlayer);
        }
    }

    public BoardLaderGame getBoard() {
        return board;
    }

}

