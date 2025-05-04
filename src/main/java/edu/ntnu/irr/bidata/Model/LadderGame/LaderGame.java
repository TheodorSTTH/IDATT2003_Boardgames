package edu.ntnu.irr.bidata.Model.LadderGame;

import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISubject;
import edu.ntnu.irr.bidata.View.LadderGameOverview.SnakesAndLaddersPage;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ntnu.irr.bidata.Model.Dice;
import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.Player;


public class LaderGame extends Game implements ISubject<LaderGame> {
    private BoardLaderGame board;
    private final Dice dice = new Dice(2, 6);
    private final ArrayList<IObserver<LaderGame>> allObservers;

    @Override
    public void registerObserver(IObserver<LaderGame> o) {
        allObservers.add(o);
    }

    @Override
    public void removeObserver(IObserver<LaderGame> o) {
        allObservers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver<LaderGame> observer : allObservers) {
            observer.update(this);
        }
    }

    public LaderGame(int amountOfPlayers, String gameName, String boardType) {
        super(amountOfPlayers, gameName);
        this.board = new BoardLaderGame(boardType);
        this.allObservers = new ArrayList<>();
    }

    public LaderGame(int amountOfPlayers, String gameName, ArrayList<Player> players, BoardLaderGame board,
            Player currentPlayer) {
        super(amountOfPlayers, gameName, players, currentPlayer);
        this.board = board;
        this.allObservers = new ArrayList<>();
    }

    @Override
    protected void init() {
        super.init();
        board.setPlayers(players);
        SnakesAndLaddersPage snakesAndLaddersPage = new SnakesAndLaddersPage(this);
        NavigationManager.switchScene(snakesAndLaddersPage); // TODO: Find way around doing this here
    }

    public void startSavedGame() {
        NavigationManager.switchScene(new SnakesAndLaddersPage(this));
    }

    public void takeAction() {
        board.move(currentPlayer, dice.roll());
        if (board.hasWon(currentPlayer)) {
            endGame(currentPlayer);
        }
        currentPlayer = getNextPlayer();
        notifyObservers();
    }

    public BoardLaderGame getBoard() {
        return board;
    }

    @Override
    public String getGameType() {
        return "LaderGame";
    }

    public HashMap<Player, Integer> getPlayerPositions() {
        HashMap<Player, Integer> playerPositions = new HashMap<Player, Integer>();
        for (String playerName : board.getPlayerPositions().keySet()) {
            for (Player player : players) {
                if (player.getName().equals(playerName)) {
                    playerPositions.put(player, board.getPlayerPositions().get(playerName));
                }
            }
        }
        return playerPositions;
    }

}


