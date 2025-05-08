package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link SnakesAndLadders} class. This class tests game creation, loading,
 * saving, and deletion functionality.
 */
public class SnakesAndLaddersTest {

  /**
   * Tests that loading a saved "classic" snakes and ladders game works correctly. Verifies that the
   * loaded game has a board, dice, and expected properties.
   */
  @Test
  @DisplayName("Test loading snakes and ladders game works as expected")
  void testSnakesAndLaddersLoad() {
    // Arrange
    String gameName = "TestClassic";

    // Act
    SnakesAndLadders snakesAndLadders =
        (SnakesAndLadders) FileHandler.loadGame(gameName, "classic");

    // Assert
    assertNotNull(snakesAndLadders, "Should have gotten snakes and ladders game");
    assertNotNull(snakesAndLadders.getBoard(), "Should have gotten board");
    assertEquals(gameName, snakesAndLadders.getGameName(), "Game name should have been set");
    assertNotNull(snakesAndLadders.getDice(), "Should have gotten dice");
    assertEquals(
        2,
        snakesAndLadders.getDice().getDice().size(),
        "Snakes and ladders should only have 2 dice");
    assertEquals("classic", snakesAndLadders.getGameType(), "Should have gotten gameType");
  }

  /**
   * Tests the constructor of the {@link SnakesAndLadders} class to ensure all attributes are
   * initialized correctly based on the provided parameters.
   */
  @Test
  @DisplayName("Test snakes & ladders constructor works as expected")
  void testSnakesAndLaddersConstructor() {
    // Arrange
    String gameName = "TestClassicCreateDelete";
    String gameType = "classic";
    int amountOfPlayers = 2;

    // Act
    SnakesAndLadders snakesAndLadders = new SnakesAndLadders(amountOfPlayers, gameName, gameType);

    // Assert
    assertEquals(gameName, snakesAndLadders.getGameName(), "Game name should have been set");
    assertEquals(gameType, snakesAndLadders.getGameType(), "Game type should be set");
    assertEquals(
        amountOfPlayers, snakesAndLadders.getAmountOfPlayers(), "Amount of players should be set");
  }

  /**
   * Tests saving and deleting a {@link SnakesAndLadders} game. Ensures players and game data
   * persist correctly and that deleted games cannot be reloaded.
   */
  @Test
  @DisplayName("Test saving and deleting snakes and ladders works.")
  void testSnakesAndLaddersSaveAndDelete() {
    // Arrange
    String gameName = "TestClassicCreateDelete";
    int amountOfPlayers = 2;

    // Act
    SnakesAndLadders snakesAndLadders = new SnakesAndLadders(amountOfPlayers, gameName, "Classic");
    snakesAndLadders.addPlayer("Kari", "Blue", 53);
    snakesAndLadders.addPlayer("Finn", "Red", 34);
    snakesAndLadders.takeAction();
    snakesAndLadders.takeAction();
    snakesAndLadders.saveGame();
    SnakesAndLadders loadedSnakesAndLadders =
        (SnakesAndLadders) FileHandler.loadGame(gameName, "SnakesAndLadders");
    FileHandler.deleteGame(gameName);

    // Assert
    assertNotNull(loadedSnakesAndLadders.getPlayers(), "Board should have been loaded");
    assertTrue(
        loadedSnakesAndLadders.getPlayers().stream()
            .anyMatch(player -> player.getName().equals("Kari")),
        "Player Kari should have been loaded");
    assertTrue(
        loadedSnakesAndLadders.getPlayers().stream()
            .anyMatch(player -> player.getName().equals("Finn")),
        "Player Finn should have been loaded");
    assertNotNull(loadedSnakesAndLadders.getBoard(), "Board should have been loaded");
    assertNotNull(loadedSnakesAndLadders.getDice(), "Dice should have been loaded");
    assertEquals(
        loadedSnakesAndLadders.getDice().getDice().size(), 2, "Should have exactly 2 dice.");
    assertThrows(
        Exception.class,
        () -> {
          FileHandler.loadGame(gameName, "SnakesAndLadders");
        },
        "Should not be able to load deleted game");
  }
}
