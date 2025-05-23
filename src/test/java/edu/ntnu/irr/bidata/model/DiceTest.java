package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Dice} class. Verifies correct construction of dice collections and
 * their rolling behavior.
 */
public class DiceTest {

  /**
   * Tests that the {@link Dice} constructor creates the correct number of dice, all with the
   * expected number of faces, and that none have been rolled initially.
   */
  @Test
  @DisplayName("Ensure we get the previous die roll")
  void testConstructor() {
    Dice myDice = new Dice(100, 6);
    assertEquals(100, myDice.getDice().size(), "100 dice should have been created");

    boolean allDiceHaveSixFaces = true;
    boolean noDiceHavePreviousRoll = true;

    for (Die die : myDice.getDice()) {
      if (die.getAmountOfFaces() != 6) {
        allDiceHaveSixFaces = false;
      }
      if (die.getPreviousDieRoll() != 0) {
        noDiceHavePreviousRoll = false;
      }
    }

    assertTrue(allDiceHaveSixFaces, "All dice should have six faces");
    assertTrue(noDiceHavePreviousRoll, "No dice should have been rolled previously");
  }

  /**
   * Tests that rolling a large number of dice produces an average close to the expected value of a
   * fair six-sided die (approximately 3.5), confirming statistical fairness.
   */
  @Test
  @DisplayName("Test rolling dice works as expected")
  void testRollingDice() {
    int amountOfSteps = 10_000;
    Dice myDice = new Dice(amountOfSteps, 6);
    int result = myDice.roll();
    double average = (double) result / (double) amountOfSteps;
    assertTrue(average < 4 && average > 3, "Average should most likely be around 3.5");
  }


  @Test
  @DisplayName("Test rolling rollSetOfDice works as expected")
  void yestrollSetOfDice() {
    Dice myDice = new Dice(4, 6);
    assertEquals(myDice.rollSetOfDice(3).size(), 3);
  }
}
