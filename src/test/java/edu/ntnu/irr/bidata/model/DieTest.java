package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.*;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DieTest {
  @Test
  @DisplayName("The average of rolling the dice should follow an uniform distribution")
  void testRoll() {
    Die myDie = new Die(6);
    int sum = 0;
    int amountOfSteps = 10_000;
    for(int i = 0; i < amountOfSteps; i++) {
      sum += myDie.roll();
    }
    double average = (double) sum / (double) amountOfSteps;
    assertTrue( average < 4 && 3 < average, "Average should most likely be around 3.5");
  }

  @Test
  @DisplayName("Ensure we get the previous die roll")
  void testGetPreviousDieRoll() {
    Die myDie = new Die(10000);
    int previousRoll = myDie.roll();
    assertEquals(previousRoll, myDie.getPreviousDieRoll(), "Previous die roll should match previous returned roll");
  }
}
