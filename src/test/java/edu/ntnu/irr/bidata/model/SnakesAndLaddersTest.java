package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link SnakesAndLadders} class. This class tests game creation, loading,
 * saving, and deletion functionality.
 */
public class SnakesAndLaddersTest {

  /**
   * Tests the constructor of the {@link SnakesAndLadders} class to ensure all attributes are
   * initialized correctly based on the provided parameters.
   */
  @Test
  @DisplayName("Test snakes & ladders constructor works as expected")
  void testSnakesAndLaddersConstructor() {
    // Arrange
    String gameName = "TestClassicCreateDelete";
    String gameType = "SnakesAndLadders";
    String boardType = "classic";
    int amountOfPlayers = 2;

    // Act
    SnakesAndLadders snakesAndLadders = new SnakesAndLadders(amountOfPlayers, gameName, boardType);

    // Assert
    assertEquals(gameName, snakesAndLadders.getGameName(), "Game name should have been set");
    assertEquals(gameType, snakesAndLadders.getGameType(), "Game type should be set");
    assertEquals(
        amountOfPlayers, snakesAndLadders.getAmountOfPlayers(), "Amount of players should be set");
  }
}
