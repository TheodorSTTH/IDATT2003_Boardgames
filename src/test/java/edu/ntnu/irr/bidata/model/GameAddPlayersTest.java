package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameAddPlayersTest {
  SnakesAndLadders snakesAndLadders;

  @BeforeEach
  void setUp() {
    // Arrange
    String gameName = "TestClassicCreateDelete";
    String gameType = "classic";
    int amountOfPlayers = 2;

    snakesAndLadders = new SnakesAndLadders(amountOfPlayers, gameName, gameType);
  }

  @Test
  @DisplayName("Negative test of adding players with comma in name")
  void testAddPlayerWithCommaInName() {
    // Act / Assert
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
      snakesAndLadders.addPlayer("Jo,han", "White", 10);
    }, "Name shouldn't be able to contain comma");
    assertTrue(ex.getMessage().contains(","), "Error message should contain comma");
  }

  @Test
  @DisplayName("Negative test of adding players with colors not selected for use")
  void testAddPlayerWithInvalidColor() {
    // Act / Assert
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
      snakesAndLadders.addPlayer("Johan", "cheese-doodles", 10);
    }, "Shouldn't be able to use whatever color");
    assertTrue(ex.getMessage().toLowerCase().contains("color"), "Error message should contain comma");
  }

  @Test
  @DisplayName("Negative test of adding more players than allowed")
  void testAddPlayersAboveLimit() {
    snakesAndLadders.addPlayer("Jane", "Green", 20);
    snakesAndLadders.addPlayer("Kari", "Blue", 34);
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
      snakesAndLadders.addPlayer("Johannes", "White", 19);
    }, "Shouldn't be able to add more than the 2 players specified");

    assertTrue(ex.getMessage().toLowerCase().contains("players"), "Should return correct error message");
  }

  @Test
  @DisplayName("Negative test of adding players with same name")
  void testAddPlayersWithSameName() {
    snakesAndLadders.addPlayer("Elinor", "Green", 20);
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
      snakesAndLadders.addPlayer("Elinor", "Blue", 34);
    }, "Shouldn't be able to add players with the same name");

    assertTrue(ex.getMessage().toLowerCase().contains("player name"), "Should return correct error message");
  }

  @Test
  @DisplayName("Test adding players to snakes & ladders successfully")
  void testSnakesAndLaddersAddPlayersSuccessfully() {
    snakesAndLadders.addPlayer("Johan", "White", 10);
    snakesAndLadders.addPlayer("Birgitte", "Green", 20);

    assertNotNull(snakesAndLadders.getPlayers(), "Players shouldn't be null");
    assertEquals(2, snakesAndLadders.getPlayers().size(), "2 Players should have been added");
    assertNotNull(snakesAndLadders.getPlayers().getFirst(), "Players in players list should be defined");
    assertEquals("Johan", snakesAndLadders.getPlayers().getFirst().getName(), "Player should be added with name");
  }
}
