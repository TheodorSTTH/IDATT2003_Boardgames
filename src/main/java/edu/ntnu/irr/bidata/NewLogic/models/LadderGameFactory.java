package edu.ntnu.irr.bidata.NewLogic.models;

import java.util.ArrayList;

public class LadderGameFactory {
  private final int numberOfDice;

  public LadderGameFactory(int numberOfDice) {
    this.numberOfDice = numberOfDice;
  }

  /**
   * Creates initial ladders from the default ladder game configuration.
   * */
  private void createDefaultLadders(Board<Integer, Tile> board) {
    LadderActionBuilder ladderBuilder = new LadderActionBuilder(board);
    ladderBuilder.from(1).to(40).build();
    ladderBuilder.from(8).to(10).build();
    ladderBuilder.from(24).to(5).build();
    ladderBuilder.from(33).to(3).build();
    ladderBuilder.from(36).to(52).build();
    ladderBuilder.from(42).to(30).build();
    ladderBuilder.from(56).to(37).build();
    ladderBuilder.from(64).to(27).build();
    ladderBuilder.from(65).to(82).build();
    ladderBuilder.from(68).to(85).build();
    ladderBuilder.from(74).to(12).build();
    ladderBuilder.from(87).to(70).build();
  }

  private void populateBoardWithTiles(Board<Integer, Tile> board) {
    int boardRowsAmount = 10;
    int boardColumnAmount = 9;
    for (int i = 0; i < boardRowsAmount * boardColumnAmount; i++) {
      Tile tileAtIndex = new Tile(i);
      board.setSpace(i, tileAtIndex);
      if (i != 0) board.getSpace(i-1).setNextTile(tileAtIndex);
    }
  }

  public LadderGame createGame() {
    Board<Integer, Tile> board = new Board<>();
    populateBoardWithTiles(board);
    createDefaultLadders(board);
    Dice dice = new Dice(numberOfDice);
    ArrayList<LadderPlayer> players = new ArrayList<>();
    Tile startTile = board.getSpace(0);
    players.add(new LadderPlayer("Arne", startTile));
    players.add(new LadderPlayer("Ivar", startTile));
    players.add(new LadderPlayer("Majid", startTile));
    players.add(new LadderPlayer("Atle", startTile));
    return new LadderGame(players, board, dice);
  }
}
