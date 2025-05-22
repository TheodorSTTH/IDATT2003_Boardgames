package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.irr.bidata.model.snakesandladders.BoardSnakesAndLadders;
import edu.ntnu.irr.bidata.model.snakesandladders.event.EventMaker;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link BoardSnakesAndLadders}. Tests the initialization, player movement, and
 * winning conditions on the Snakes and Ladders board.
 */
public class BoardSnakesAndLaddersTest {
  BoardSnakesAndLadders board;
  Player player1;
  Player player2;

  /** Sets up the board and players before each test. Adds a ladder event from position 10 to 30. */
  @BeforeEach
  void setUp() {
    board = new BoardSnakesAndLadders();
    player1 = new Player("Johan", "White", 10);
    player2 = new Player("Birgitte", "Green", 20);
    ArrayList<Player> players = new ArrayList<>();
    players.add(player2);
    players.add(player1);
    board.addEvent(10, EventMaker.newLadder(30));
    board.setPlayers(players);
  }

  /** Tests that the initial position of players on the board is zero. */
  @Test
  @DisplayName("Test that initial position is zero")
  void startPosision() {
    assertEquals(0, board.getPlayerPosition(player1));
    assertEquals(0, board.getPlayerPosition(player2));
  }

  /**
   * Tests moving a player forward on the board, including the effect of climbing a ladder (from
   * position 10 to 30).
   */
  @Test
  @DisplayName("Test moving player with ladder event")
  void moveTest() {
    board.move(player1, 5);
    int position1 = board.getPlayerPosition(player1);
    board.move(player1, 5);
    int position2 = board.getPlayerPosition(player1);
    board.move(player1, 5);
    int position3 = board.getPlayerPosition(player1);

    assertEquals(5, position1, "After first move of 5, position should be 5");
    assertEquals(30, position2, "After second move landing on ladder, position should be 30");
    assertEquals(35, position3, "After third move of 5, position should be 35");
  }

  /**
   * Tests the winning condition for a player. Verifies the player has not won at the start and wins
   * after moving sufficiently far.
   */
  @Test
  @DisplayName("Test hasWon condition")
  void hasWonTest() {
    boolean hasWon1 = board.hasWon(player1);
    board.move(player1, 90);
    boolean hasWon2 = board.hasWon(player1);

    assertTrue(!hasWon1, "Player should not have won at the start");
    assertTrue(hasWon2, "Player should have won after moving 90 steps");
  }
}
