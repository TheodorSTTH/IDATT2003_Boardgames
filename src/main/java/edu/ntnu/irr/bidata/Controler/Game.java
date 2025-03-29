package edu.ntnu.irr.bidata.Controler;

import edu.ntnu.irr.bidata.Model.Player;
import java.util.ArrayList;


public abstract class Game {
    private ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayer = 0;
    private Player winner = null;

    public void init() {
        // for i in range (UI.AskForPlayers()){
        //     players.add(new Player(UI.AskForName()));
        // }
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

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public int getAmountOfPlayers() {
        return players.size();
    }
    public void addPlayer(String name) {
        players.add(new Player(name));
    }
}
