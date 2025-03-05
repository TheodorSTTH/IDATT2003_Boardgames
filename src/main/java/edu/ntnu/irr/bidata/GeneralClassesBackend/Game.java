package edu.ntnu.irr.bidata.GeneralClassesBackend;

import java.util.ArrayList;


public abstract class Game {
    private ArrayList<Player> players;
    private int currentPlayer = 0;
    private Player winner = null;
    private Die die = new Die();

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

    public abstract void endGame();

    public void startGame() {
        init();
        runGame();
        endGame();
    }
}
