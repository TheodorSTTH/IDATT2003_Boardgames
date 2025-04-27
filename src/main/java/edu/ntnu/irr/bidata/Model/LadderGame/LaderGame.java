package edu.ntnu.irr.bidata.Model.LadderGame;

import java.util.ArrayList;
import java.util.HashMap;

import edu.ntnu.irr.bidata.Controler.UILaderGame;
import edu.ntnu.irr.bidata.Model.Dice;
import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.Player;



public class LaderGame extends Game {
    private final BoardLaderGame board;
    private final Dice dice = new Dice(2, 6);

    public LaderGame(int amountOfPlayers, String gameName) {
        super(amountOfPlayers, gameName);
        this.board = new BoardLaderGame();
    }

    public LaderGame(int amountOfPlayers, String gameName, ArrayList<Player> players, BoardLaderGame board, Player currentPlayer) {
        super(amountOfPlayers, gameName, players, currentPlayer);
        this.board = board;
    }


    @Override
    public void init() {
        super.init();
        board.setPlayers(players);
        UILaderGame.setLadderGame(this);
        UILaderGame.toLaderGamePage();
    }

    @Override
    public void startSavedGame() {
        UILaderGame.setLadderGame(this);
        UILaderGame.toLaderGamePage();
    }

    public void takeAction() {
        board.move(currentPlayer, dice.roll());
        if (board.hasWone(currentPlayer)) {
            endGame(currentPlayer);
        }
        currentPlayer = getNextPlayer();
    }

    public BoardLaderGame getBoard() {
        return board;
    }

    @Override
    public String getGameType() {
        return "LaderGame";
    }

    public HashMap<String, Integer> getPlayerPositions() {
        return board.getPlayerPositions();
    }



}

