package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.BoardLaderGame;
import edu.ntnu.irr.bidata.Model.Die;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.Die;


public class LaderGame extends Game {
    private BoardLaderGame board;

    public LaderGame(int amountOfPlayers) {
        super(amountOfPlayers);
    }

    @Override
    protected void init() {
        super.init();
        board = new BoardLaderGame();
    }

    public void takeTurn() {
        currentPlayer.setCurrentTile(board.landOnTile(currentPlayer.getCurrentTile() + Die.roll(6)));
        if (board.isOnOreAftherEndTile(currentPlayer.getCurrentTile())) {
            endGame(currentPlayer);
        }
        currentPlayer = getNextPlayer(currentPlayer);
    }
    }