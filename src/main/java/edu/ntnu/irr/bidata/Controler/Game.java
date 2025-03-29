package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.Die;

import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Wiew.AlertInterface;

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
            endGame(currentPlayer);
        }
    }

    protected void init() {
        currentPlayer = players.get(0);
    }

    protected void endGame(Player winner) {
        MyWindow.getPrimaryStage().hide();
        MyWindow.getPrimaryStage().setMaximized(false);
        AlertInterface.showInfo("Game Over", "Winner: " + winner.getName());
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getAmountOfPlayers() {
        return players.size();
    }

    public Player getNextPlayer() {
        int index = players.indexOf(currentPlayer);
        if (index == players.size() - 1) {
            return players.get(0);
        } else {
            return players.get(index + 1);
        }
    }
}
