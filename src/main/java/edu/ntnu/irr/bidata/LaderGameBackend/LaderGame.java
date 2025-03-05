package edu.ntnu.irr.bidata.LaderGameBackend;

import edu.ntnu.irr.bidata.GeneralClassesBackend.Die;
import edu.ntnu.irr.bidata.GeneralClassesBackend.Game;
import edu.ntnu.irr.bidata.GeneralClassesBackend.Player;

public class LaderGame extends Game {
    private BoardLaderGame board;
    private Die die = new Die(6);

    @Override
    public void init() {
        super.init();
        board = new BoardLaderGame();
        board.setUp();
    }

    @Override
    public void takeTurn(Player player) {
        int dice1 = die.roll();
        int dice2 = die.roll();
        player.setCurrentTile(board.landOnTile(player.getCurrentTile() + dice1 + dice2));
    }  
}
