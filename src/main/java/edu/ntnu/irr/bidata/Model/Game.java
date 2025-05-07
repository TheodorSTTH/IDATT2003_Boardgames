package edu.ntnu.irr.bidata.Model;
import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.View.PopUp;

import java.util.ArrayList;
import java.util.List;


public abstract class Game {
    protected ArrayList<Player> players = new ArrayList<Player>();
    protected Player currentPlayer;
    private int amountOfPlayers = 0;
    private final List<String> availableColors = new ArrayList<>(List.of("Red", "Blue", "Green", "Yellow", "White"));
    private final String gameName;


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

    public boolean addPlayer(String name, String color, int age) {
        if (getPlayerNames().contains(name)) {
            PopUp.showWarning("Player already exists", "Player with this name already exists");
            return false;
        }
        if (name.contains(",")) {
            PopUp.showWarning("Invalid name", "Name cannot contain a comma(,)");
            return false;
        }
        players.add(new Player(name, color, age));
        availableColors.remove(color);
        if (players.size() == amountOfPlayers) {
            init();
        }
        return true;
    }

    public void sortPlayersByAge() {
        players.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
    }

    protected void init() {
        sortPlayersByAge();
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

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public String getPlayerColor(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player.getColor();
            }
        }
        return null;
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
        PopUp.showInfo("Game saved", "Game saved as\n" + gameName);
    }

    protected List<String> getPlayerNames() {
        List<String> names = new ArrayList<>();
        for (Player player : players) {
            names.add(player.getName());
        }
        return names;
    }

    public List<String> getAvailableColors() {
        return availableColors;
    }
}
