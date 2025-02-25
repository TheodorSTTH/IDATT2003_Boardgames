package edu.ntnu.irr.bidata;

import java.util.ArrayList;


public abstract class Game {
    private ArrayList<Player> players;
    private int currentPlayer = 0;
    private Player winner = null;

    public Game() {
        
    }

    private void addPlayer(Player player) {
        players.add(player);
    }

    public void init() {
        
        // add players
        // determand turn order
    }

    public void runGame() {
        while (winner == null) {
            takeTurn(players.get(currentPlayer));
            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }

    public abstract void takeTurn(Player player);

    public void endGame() {
        // end game
    }

    public void startGame() {
        init();
        runGame();
        endGame();
    }


    
}
