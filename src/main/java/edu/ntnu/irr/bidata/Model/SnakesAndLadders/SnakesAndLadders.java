package edu.ntnu.irr.bidata.Model.SnakesAndLadders;

import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISubject;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.View.LadderGameOverview.SnakesAndLaddersPage;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ntnu.irr.bidata.Model.Dice;
import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.Player;


public class SnakesAndLadders extends Game implements ISubject<SnakesAndLadders> {
    private BoardSnakesAndLadders board;
    private final Dice dice = new Dice(2, 6);
    private final ArrayList<IObserver<SnakesAndLadders>> allObservers;

    @Override
    public void registerObserver(IObserver<SnakesAndLadders> o) {
        allObservers.add(o);
    }

    @Override
    public void removeObserver(IObserver<SnakesAndLadders> o) {
        allObservers.remove(o);
    }

    @Override
    public void notifyObservers(SnakesAndLadders game) {
        for (IObserver<SnakesAndLadders> observer : allObservers) {
            observer.update(game);
        }
    }

    public Dice getDice() {
        return dice;
    }

    public SnakesAndLadders(int amountOfPlayers, String gameName, String boardType) {
        super(amountOfPlayers, gameName);
        this.board = new BoardSnakesAndLadders(boardType);
        this.allObservers = new ArrayList<>();
    }

    public SnakesAndLadders(int amountOfPlayers, String gameName, ArrayList<Player> players, BoardSnakesAndLadders board,
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
        NavigationManager.navigate(snakesAndLaddersPage); // TODO: Find way around doing this here
        showRueles();
    }

    public void startSavedGame() {
        NavigationManager.navigate(new SnakesAndLaddersPage(this));
    }

    public void takeAction() {
        board.move(currentPlayer, dice.roll());
        currentPlayer = getNextPlayer();
        notifyObservers(this);
    }

    public BoardSnakesAndLadders getBoard() {
        return board;
    }

    @Override
    public String getGameType() {
        return "SnakesAndLadders";
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

    public void showRueles() {
        PopUp.showScrollablePopup("Rules", "The rules of the game are as follows:\n"
                + "1. Players take turns rolling two dice.\n"
                + "2. The player moves their token forward the number of spaces shown on the dice.\n"
                + "3. If a player lands on a snake, they must slide down to the tail of the snake.\n"
                + "4. If a player lands on a ladder, they can climb up to the top of the ladder.\n"
                + "5. If a player lands on a qizz tile, they must answer a if they answer correctly they move 3 forwerd, if they get wrong they move 3 back.\n"
                + "6. The first player to reach the end of the board wins.");
    }

}


