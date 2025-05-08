package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Die} class. Verifies die rolling behavior and that previous roll values
 * are properly tracked.
 */
public class DieTest {

  /**
   * Tests that the average of many die rolls approximates the expected mean of a uniform
   * distribution. This helps verify randomness and fairness of the die.
   */
  @Test
  @DisplayName("The average of rolling the dice should follow a uniform distribution")
  void testRoll() {
    Die myDie = new Die(6);
    int sum = 0;
    int amountOfSteps = 10_000;
    for (int i = 0; i < amountOfSteps; i++) {
      sum += myDie.roll();
    }
    double average = (double) sum / (double) amountOfSteps;
    assertTrue(average < 4 && average > 3, "Average should most likely be around 3.5");
  }

  /**
   * Tests that the result of {@code getPreviousDieRoll()} matches the most recent roll. Ensures
   * state is stored correctly after a roll.
   */
  @Test
  @DisplayName("Ensure we get the previous die roll")
  void testGetPreviousDieRoll() {
    Die myDie = new Die(10000);
    int previousRoll = myDie.roll();
    assertEquals(
        previousRoll,
        myDie.getPreviousDieRoll(),
        "Previous die roll should match previous returned roll");
  }
}
