package edu.ntnu.irr.bidata.Controler;

import java.util.ArrayList;

import edu.ntnu.irr.bidata.Model.Player;


public abstract class Game {
    private ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayer = 0;
    private Player winner = null;
    private int amountOfPlayers = 0;

    public Game(int amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
        if (players.size() == amountOfPlayers) {
            startGame();
        }
    }

    protected void init() {
    }

    private void runGame() {
        while (winner == null) {
            takeTurn(players.get(currentPlayer));
            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }

    protected abstract void takeTurn(Player player);

    private void endGame() {
    }

    private void startGame() {
        init();
        runGame();
        endGame();
    }

}
