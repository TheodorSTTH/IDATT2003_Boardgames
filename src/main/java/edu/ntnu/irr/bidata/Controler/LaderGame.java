package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.BoardLaderGame;
import edu.ntnu.irr.bidata.Model.Die;
import edu.ntnu.irr.bidata.Model.Player;


public class LaderGame extends Game {
    private BoardLaderGame board;

    public LaderGame(int amountOfPlayers) {
        super(amountOfPlayers);
    }

    @Override
    public void init() {
        super.init();
        board = new BoardLaderGame();
    }

    @Override
    public void takeTurn(Player player) {
        player.setCurrentTile(board.landOnTile(player.getCurrentTile() + Die.rollDies(6, 1)));
    }

    }
 