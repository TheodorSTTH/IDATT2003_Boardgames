package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.irr.bidata.model.risk.Risk;
import java.io.UncheckedIOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Risk} game class. This class verifies the creation, loading, saving,
 * and deletion of Risk game instances.
 */
public class RiskTest {

  /**
   * Tests loading a saved Risk game from storage. Verifies that all game components (board, dice,
   * game type, etc.) are loaded properly.
   */
  @Test
  @DisplayName("Test loading risk game works as expected")
  void testRiskLoad() {
    // Arrange
    String gameName = "Test";

    // Act
    Risk risk = (Risk) FileHandler.loadGame(gameName, "Risk");

    // Assert
    assertNotNull(risk, "Should have gotten risk game");
    assertNotNull(risk.getBoard(), "Should have gotten board");
    assertEquals(gameName, risk.getGameName(), "Game name should have been set");
    assertNotNull(risk.getAttackDice(), "Should have gotten attack dice");
    assertNotNull(risk.getDefenceDice(), "Should have gotten defence dice");
    assertEquals(2, risk.getDefenceDice().getDice().size(), "Should only have 2 defence dice");
    assertEquals(3, risk.getAttackDice().getDice().size(), "Should only have 3 attack dice");
    assertEquals("Risk", risk.getGameType(), "Should have gotten gameType");
  }

  /**
   * Tests the constructor of the {@link Risk} class. Ensures that the game is initialized with the
   * correct number of players and correct metadata.
   */
  @Test
  @DisplayName("Test constructor works as expected")
  void testRiskConstructor() {
    // Arrange
    String gameName = "TestClassicCreateDelete";
    String gameType = "Risk";
    int amountOfPlayers = 2;

    // Act
    Risk risk = new Risk(amountOfPlayers, gameName);

    // Assert
    assertEquals(gameName, risk.getGameName(), "Game name should have been set");
    assertEquals(gameType, risk.getGameType(), "Game type should be set");
    assertEquals(amountOfPlayers, risk.getAmountOfPlayers(), "Amount of players should be set");
  }

  /**
   * Tests saving and deleting a Risk game. Ensures that saved games can be loaded with accurate
   * state and deleted games cannot be accessed.
   */
  @Test
  @DisplayName("Test saving and deleting risk game works.")
  void testSnakesAndLaddersSaveAndDelete() {
    // Arrange
    String gameName = "TestRiskCreateDelete";
    int amountOfPlayers = 2;

    // Act
    Risk risk = new Risk(amountOfPlayers, gameName);
    risk.addPlayer("Kari", "Blue", 53);
    risk.addPlayer("Finn", "Red", 34);
    risk.saveGame();
    Risk loadedRisk = (Risk) FileHandler.loadGame(gameName, "Risk");
    FileHandler.deleteGame(gameName);

    // Assert
    assertNotNull(loadedRisk.getPlayers(), "Board should have been loaded");
    assertTrue(
        loadedRisk.getPlayers().stream().anyMatch(player -> player.getName().equals("Kari")),
        "Player Kari should have been loaded");
    assertTrue(
        loadedRisk.getPlayers().stream().anyMatch(player -> player.getName().equals("Finn")),
        "Player Finn should have been loaded");
    assertNotNull(loadedRisk.getBoard(), "Board should have been loaded");
    assertNotNull(loadedRisk.getDefenceDice(), "Defence dice should have been loaded");
    assertEquals(
        2, loadedRisk.getDefenceDice().getDice().size(), "Should have exactly 2 defence dice.");
    assertNotNull(loadedRisk.getAttackDice(), "Attack dice should have been loaded");
    assertEquals(
        3, loadedRisk.getAttackDice().getDice().size(), "Should have exactly 3 attack dice.");

    assertThrows(
        UncheckedIOException.class,
        () -> {
          FileHandler.loadGame(gameName, "SnakesAndLadders");
        },
        "Should not be able to load deleted game");
  }
}
