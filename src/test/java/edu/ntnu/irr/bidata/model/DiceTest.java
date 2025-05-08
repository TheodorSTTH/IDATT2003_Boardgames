package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.*;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiceTest {
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
    assertTrue(noDiceHavePreviousRoll, "No dice should have been rolled previous");
  }

  @Test
  @DisplayName("Test rolling dice works as expected")
  void testRollingDice() {
    int amountOfSteps = 10_000;
    Dice myDice = new Dice(amountOfSteps, 6);
    int result = myDice.roll();
    double average = (double) result / (double) amountOfSteps;
    assertTrue(average < 4 && 3 < average, "Average should most likely be around 3.5");
  }
}
