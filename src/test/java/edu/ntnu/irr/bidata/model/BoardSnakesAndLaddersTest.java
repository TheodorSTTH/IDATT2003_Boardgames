package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.irr.bidata.model.snakesandladders.BoardSnakesAndLadders;
import edu.ntnu.irr.bidata.model.snakesandladders.event.EventMaker;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardSnakesAndLaddersTest {
  BoardSnakesAndLadders board;
  Player Johan;
  Player Birgitte;

  @BeforeEach
  void setUp() {
    board = new BoardSnakesAndLadders();
    Johan = new Player("Johan", "White", 10);
    Birgitte = new Player("Birgitte", "Green", 20);
    ArrayList<Player> players = new ArrayList<>();
    players.add(Birgitte);
    players.add(Johan);
    board.addEvent(10, EventMaker.newLadder(30));
    board.setPlayers(players);
  }

  @Test
  @DisplayName("Test that initial posision is zero")
  void startPosision() {
    assertEquals(0, board.getPlayerPosition(Johan));
    assertEquals(0, board.getPlayerPosition(Birgitte));
  }

  @Test
  @DisplayName("Test move player")
  void MoveTest() {
    board.move(Johan, 5);
    int position1 = board.getPlayerPosition(Johan);
    board.move(Johan, 5);
    int position2 = board.getPlayerPosition(Johan);
    board.move(Johan, 5);
    int position3 = board.getPlayerPosition(Johan);

    assertEquals(5, position1);
    assertEquals(30, position2);
    assertEquals(35, position3);
  }

  @Test
  @DisplayName("Has Won")
  void HasWonTest() {
    boolean hasWon1 = board.hasWon(Johan);
    board.move(Johan, 90);
    boolean hasWon2 = board.hasWon(Johan);
    
    assertTrue(!hasWon1);
    assertTrue(hasWon2);
  }
}
