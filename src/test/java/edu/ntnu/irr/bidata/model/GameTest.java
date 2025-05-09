package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Unit tests for adding players to a {@link SnakesAndLadders} game. This class covers both valid
 * and invalid player addition scenarios such as name validation, color constraints, player limit
 * enforcement, and duplication checks.
 */
public class GameTest {
  SnakesAndLadders snakesAndLadders;

  /** Sets up a new SnakesAndLadders game before each test. */
  @BeforeEach
  void setUp() {
    String gameName = "TestClassicCreateDelete";
    String gameType = "classic";
    int amountOfPlayers = 2;

    snakesAndLadders = new SnakesAndLadders(amountOfPlayers, gameName, gameType);
  }

  /**
   * Tests that a player cannot be added if their name contains a comma. Commas are disallowed to
   * avoid conflicts in serialization or parsing.
   */
  @Test
  @DisplayName("Negative test of adding players with comma in name")
  void testAddPlayerWithCommaInName() {
    IllegalArgumentException ex =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              snakesAndLadders.addPlayer("Jo,han", "White", 10);
            },
            "Name shouldn't be able to contain comma");
    assertTrue(ex.getMessage().contains(","), "Error message should contain comma");
  }

  /** Tests that a player cannot be added with an invalid or unrecognized color. */
  @Test
  @DisplayName("Negative test of adding players with colors not selected for use")
  void testAddPlayerWithInvalidColor() {
    IllegalArgumentException ex =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              snakesAndLadders.addPlayer("Johan", "cheese-doodles", 10);
            },
            "Shouldn't be able to use whatever color");
    assertTrue(
        ex.getMessage().toLowerCase().contains("color"), "Error message should mention color");
  }

  /** Tests that adding more players than the allowed limit results in an exception. */
  @Test
  @DisplayName("Negative test of adding more players than allowed")
  void testAddPlayersAboveLimit() {
    snakesAndLadders.addPlayer("Jane", "Green", 20);
    snakesAndLadders.addPlayer("Kari", "Blue", 34);
    IllegalArgumentException ex =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              snakesAndLadders.addPlayer("Johannes", "White", 19);
            },
            "Shouldn't be able to add more than the 2 players specified");
    assertTrue(
        ex.getMessage().toLowerCase().contains("players"), "Should return correct error message");
  }

  /** Tests that adding players with the same name is not allowed. */
  @Test
  @DisplayName("Negative test of adding players with same name")
  void testAddPlayersWithSameName() {
    snakesAndLadders.addPlayer("Elinor", "Green", 20);
    IllegalArgumentException ex =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              snakesAndLadders.addPlayer("Elinor", "Blue", 34);
            },
            "Shouldn't be able to add players with the same name");
    assertTrue(
        ex.getMessage().toLowerCase().contains("player name"),
        "Should return correct error message");
  }

  /**
   * Tests that players are successfully added under valid conditions. Verifies that the player list
   * is properly initialized and populated.
   */
  @Test
  @DisplayName("Test adding players to snakes & ladders successfully")
  void testSnakesAndLaddersAddPlayersSuccessfully() {
    snakesAndLadders.addPlayer("Johan", "White", 10);
    snakesAndLadders.addPlayer("Birgitte", "Green", 20);

    assertNotNull(snakesAndLadders.getPlayers(), "Players shouldn't be null");
    assertEquals(2, snakesAndLadders.getPlayers().size(), "2 Players should have been added");
    assertNotNull(
        snakesAndLadders.getPlayers().getFirst(), "Players in players list should be defined");
    assertEquals(
        "Johan",
        snakesAndLadders.getPlayers().getFirst().getName(),
        "Player should be added with name");
  }


    /**
   * Tests that players are successfully added under valid conditions. Verifies that the player list
   * is properly initialized and populated.
   */
  @Test
  @DisplayName("Test get next player")
  void testCurrentPlayerLogic() {
    Player Johan = new Player("Johan", "White", 10);
    Player Birgitte = new Player("Birgitte", "Green", 20);
    ArrayList<Player> players = new ArrayList<>();
    players.add(Birgitte);
    players.add(Johan);
    snakesAndLadders.setPlayers(players);
    snakesAndLadders.setCurrentPlayer(Johan);
    snakesAndLadders.sortPlayersByAge();

    assertEquals(Johan, snakesAndLadders.getCurrentPlayer());
    assertEquals(Birgitte, snakesAndLadders.getNextPlayer());
    assertEquals(Johan, snakesAndLadders.getPlayers().get(0));



  }


}
