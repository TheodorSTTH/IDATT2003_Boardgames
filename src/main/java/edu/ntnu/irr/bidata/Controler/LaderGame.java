package edu.ntnu.irr.bidata.Controler;

import edu.ntnu.irr.bidata.Controler.Game;
import edu.ntnu.irr.bidata.Model.BoardLaderGame;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.Die;
import java.util.ArrayList;


public class LaderGame extends Game {
    private final BoardLaderGame board;

    public LaderGame(BoardLaderGame board) {
        this.board = board;
    }
    public LaderGame() {
        board = new BoardLaderGame();
    }

    public LaderGame(int amountOfPlayers) {
        super(amountOfPlayers);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void takeTurn(Player player) {
        player.setCurrentTile(board.landOnTile(player.getCurrentTile() + Die.rollDies(6, 2)));
    }

    public BoardLaderGame getBoard() {
        return board;
    }
}
