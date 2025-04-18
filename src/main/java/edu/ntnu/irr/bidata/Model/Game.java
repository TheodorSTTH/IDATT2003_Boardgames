package edu.ntnu.irr.bidata.Model;
import edu.ntnu.irr.bidata.Controler.UI;

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

    public Game(int amountOfPlayers, String gameName, ArrayList<Player> players, Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.gameName = gameName;
        this.amountOfPlayers = amountOfPlayers;
        this.players = players;
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

    public abstract void startSavedGame();

    protected void endGame(Player winner) {
        FileHandeler.deleteGame(gameName, getGameType());
        UI.endGame(winner.getName());
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

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

    public void saveGame() {
        FileHandeler.saveGame(this);
    }
}
