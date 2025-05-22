package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Player} class.
 * 
 * <p>Note: this class is made by ChatGPT</p>
 */
public class PlayerTest {

  @Test
  @DisplayName("Constructor and Getter Test")
  void testConstructorAndGetters() {
    Player player = new Player("Alice", "Red", 25);

    assertEquals("Alice", player.getName(), "Player name should be Alice");
    assertEquals("Red", player.getColor(), "Player color should be Red");
    assertEquals(25, player.getAge(), "Player age should be 25");
  }

  @Test
  @DisplayName("getSaveFormat returns correct CSV format")
  void testGetSaveFormat() {
    Player player = new Player("Bob", "Blue", 30);

    String expected = "Bob,Blue,30";
    assertEquals(expected, player.getSaveFormat(), "Save format should match CSV structure");
  }
}