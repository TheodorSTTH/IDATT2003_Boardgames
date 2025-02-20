package edu.ntnu.irr.bidata;

public class LaderGame extends Game {
    private BoardLaderGame board;

    @Override
    public void init() {
        super.init();
        board = new BoardLaderGame();
        board.setUp();
    }
    
}
