package edu.ntnu.irr.bidata.GeneralClassesBackend;

import java.util.ArrayList;


public abstract class Game {
    private ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayer = 0;
    private Player winner = null;

    public void init() {
        //for (int i = 0; i < UI.askForAmountOfPlayers(); i++) {
        //    players.add(new Player(UI.askForPlayerName()));
        //}
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
