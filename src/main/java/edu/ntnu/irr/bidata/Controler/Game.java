package edu.ntnu.irr.bidata.Controler;

import java.util.ArrayList;

import edu.ntnu.irr.bidata.Model.Player;


public abstract class Game {
    private ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayer = 0;
    private Player winner = null;

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public int getAmountOfPlayers() {
        return players.size();
    }

    public void init() {
    }

    public void runGame() {
        while (winner == null) {
            takeTurn(players.get(currentPlayer));
            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }

    public abstract void takeTurn(Player player);

    public void endGame() {
        // UI.PresentWinner(winner);
    }

    public void startGame() {
        init();
        runGame();
        endGame();
    }

}
