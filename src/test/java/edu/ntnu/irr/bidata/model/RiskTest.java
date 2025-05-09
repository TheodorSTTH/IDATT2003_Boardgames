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
}
