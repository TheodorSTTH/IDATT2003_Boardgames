package edu.ntnu.irr.bidata.model.snakesandladders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.model.risk.Risk;
import java.io.UncheckedIOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RiskTest {
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

  @Test
  @DisplayName("Test constructor works as expected")
  void testRiskConstructor() {
    // Arrange
    String gameName = "TestClassicCreateDelete";
    String gameType = "Risk";
    int amountOfPlayers = 2;

    // Act
    Risk snakesAndLadders = new Risk(amountOfPlayers, gameName);

    // Assert
    assertEquals(gameName, snakesAndLadders.getGameName(), "Game name should have been set");
    assertEquals(gameType, snakesAndLadders.getGameType(), "Game type should be set");
    assertEquals(amountOfPlayers, snakesAndLadders.getAmountOfPlayers(), "Amount of players should be set");
  }

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
    assertNotNull(
        loadedRisk.getPlayers(),
        "Board should have been loaded"
    );
    assertTrue(
        loadedRisk.getPlayers().stream().anyMatch(player -> player.getName().equals("Kari")),
        "Player Kari should have been loaded"
    );
    assertTrue(
        loadedRisk.getPlayers().stream().anyMatch(player -> player.getName().equals("Finn")),
        "Player Finn should have been loaded"
    );
    assertNotNull(
        loadedRisk.getBoard(),
        "Board should have been loaded"
    );
    assertNotNull(
        loadedRisk.getDefenceDice(),
        "Defence dice should have been loaded"
    );
    assertEquals(
        loadedRisk.getDefenceDice().getDice().size(),2,
        "Should have exactly 2 defence dice."
    );
    assertNotNull(
        loadedRisk.getAttackDice(),
        "Attack dice should have been loaded"
    );
    assertEquals(
        loadedRisk.getAttackDice().getDice().size(),3,
        "Should have exactly 3 attack dice."
    );
    assertThrows(UncheckedIOException.class, () -> {
      FileHandler.loadGame(gameName, "SnakesAndLadders");
    }, "Should not be able to load deleted game");
  }
}
