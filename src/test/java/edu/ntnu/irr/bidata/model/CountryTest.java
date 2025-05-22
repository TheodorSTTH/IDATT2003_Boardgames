package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.irr.bidata.model.interfaces.observer.SimpleObserver;
import edu.ntnu.irr.bidata.model.risk.Country;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Country} class.
 *
 * <p>Note: this class is made by ChatGPT
 */
public class CountryTest {

  private Country country;
  private List<String> neighbors;

  @BeforeEach
  void setUp() {
    neighbors = Arrays.asList("NeighborA", "NeighborB");
    country = new Country("TestLand", "Player1", "Blue", 10, neighbors, 0.5, 0.5);
  }

  @Test
  @DisplayName("Constructor sets fields correctly")
  void testConstructorSetsFields() {
    assertEquals("TestLand", country.getName());
    assertEquals("Player1", country.getOwner());
    assertEquals("Blue", country.getOwnerColor());
    assertEquals(10, country.getArmies());
    assertEquals(neighbors, country.getNeighbors());
    assertEquals(0.5, country.getRelativeX());
    assertEquals(0.5, country.getRelativeY());
  }

  @Test
  @DisplayName("Relative coordinates are clamped within bounds")
  void testClampCoordinates() {
    Country clampedCountry = new Country("ClampLand", neighbors, -1.0, 2.0);
    assertEquals(0.0, clampedCountry.getRelativeX());
    assertEquals(1.0, clampedCountry.getRelativeY());
  }

  @Test
  @DisplayName("placeTroops increases army count and notifies observer")
  void testPlaceTroops() {
    AtomicBoolean notified = new AtomicBoolean(false);
    country.registerObserver(() -> notified.set(true));

    country.placeTroops(5);
    assertEquals(15, country.getArmies());
    assertTrue(notified.get(), "Observer should be notified on troop placement");
  }

  @Test
  @DisplayName("loseTroops decreases army count and notifies observer")
  void testLoseTroops() {
    AtomicBoolean notified = new AtomicBoolean(false);
    country.registerObserver(() -> notified.set(true));

    country.loseTroops(3);
    assertEquals(7, country.getArmies());
    assertTrue(notified.get(), "Observer should be notified on troop loss");
  }

  @Test
  @DisplayName("loseTroops throws exception if removing more than available")
  void testLoseTooManyTroopsThrows() {
    assertThrows(
        IllegalArgumentException.class,
        () -> country.loseTroops(20),
        "Should throw if trying to remove more troops than available");
  }

  @Test
  @DisplayName("setArmies updates the army count and notifies observer")
  void testSetArmies() {
    AtomicBoolean notified = new AtomicBoolean(false);
    country.registerObserver(() -> notified.set(true));

    country.setArmies(50);
    assertEquals(50, country.getArmies());
    assertTrue(notified.get(), "Observer should be notified on setArmies");
  }

  @Test
  @DisplayName("setOwner updates owner name and color and notifies observer")
  void testSetOwner() {
    Player newOwner = new Player("NewPlayer", "Red", 22);
    AtomicBoolean notified = new AtomicBoolean(false);
    country.registerObserver(() -> notified.set(true));

    country.setOwner(newOwner);
    assertEquals("NewPlayer", country.getOwner());
    assertEquals("Red", country.getOwnerColor());
    assertTrue(notified.get(), "Observer should be notified on ownership change");
  }

  @Test
  @DisplayName("Observer can be removed and won't be notified")
  void testRemoveObserver() {
    AtomicBoolean notified = new AtomicBoolean(false);
    SimpleObserver observer = () -> notified.set(true);

    country.registerObserver(observer);
    country.removeObserver(observer);
    country.placeTroops(5);

    assertFalse(notified.get(), "Observer should not be notified after being removed");
  }

  @Test
  @DisplayName("toString returns the country's name")
  void testToString() {
    assertEquals("TestLand", country.toString());
  }
}
