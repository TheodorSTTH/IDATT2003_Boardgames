package edu.ntnu.irr.bidata.Model;
import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Wiew.AlertInterface;

import java.util.ArrayList;


public abstract class Game {
    protected ArrayList<Player> players = new ArrayList<Player>();
    protected Player currentPlayer;
    private int amountOfPlayers = 0;
    String gameName;


    public Game(int amountOfPlayers, String gameName) {
        this.gameName = gameName;
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
        UI.endGame(winner.getName());
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public abstract void takeAction();

    public Player getNextPlayer() {
        int index = players.indexOf(currentPlayer);
        if (index == players.size() - 1) {
            return players.get(0);
        } else {
            return players.get(index + 1);
        }
    }

    public String getGameName() {
        return gameName;
    }

    public void addPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public abstract String getGameType();
}
