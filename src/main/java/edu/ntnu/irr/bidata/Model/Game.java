package edu.ntnu.irr.bidata.Model;
import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.View.PopUp;

import java.util.ArrayList;
import java.util.List;


public abstract class Game {
    protected ArrayList<Player> players = new ArrayList<Player>();
    protected Player currentPlayer;
    private int amountOfPlayers = 0;
    private List<String> avalibleColores = new ArrayList<>(List.of("Red", "Blue", "Green", "Yellow", "Black"));
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

    public boolean addPlayer(String name, String color) {
        if (getPlayerNames().contains(name)) {
            PopUp.showWarning("Player already exists", "Player with this name already exists");
            return false;
        }
        players.add(new Player(name, color));
        avalibleColores.remove(color);
        if (players.size() == amountOfPlayers) {
            init();
        }
        return true;
    }

    protected void init() {
        currentPlayer = players.get(0);
    }

    public abstract void startSavedGame();

    protected void endGame(Player winner) {
        FileHandler.deleteGame(gameName);
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
        FileHandler.saveGame(this);
    }

    protected List<String> getPlayerNames() {
        List<String> names = new ArrayList<>();
        for (Player player : players) {
            names.add(player.getName());
        }
        return names;
    }

    public List<String> getAvalibleColores() {
        return avalibleColores;
    }
}
