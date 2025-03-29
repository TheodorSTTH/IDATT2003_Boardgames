package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.Die;

import edu.ntnu.irr.bidata.Model.Player;
import java.util.ArrayList;


public abstract class Game {
    protected ArrayList<Player> players = new ArrayList<Player>();
    protected Player currentPlayer;
    private int amountOfPlayers = 0;


    public Game(int amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
        if (players.size() == amountOfPlayers) {
            init();
        }
    }

    protected void init() {
        currentPlayer = players.get(0);

    }

    protected void endGame(Player winner) {
        System.out.println("Game over! " + winner.getName() + " wins!");
        
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
