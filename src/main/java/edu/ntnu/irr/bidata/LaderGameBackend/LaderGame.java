package edu.ntnu.irr.bidata.LaderGameBackend;
import edu.ntnu.irr.bidata.GeneralClassesBackend.Game;
import edu.ntnu.irr.bidata.GeneralClassesBackend.Player;
import edu.ntnu.irr.bidata.GeneralClassesBackend.Die;


public class LaderGame extends Game {
    private final BoardLaderGame board;

    public LaderGame(BoardLaderGame board) {
        this.board = board;
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
