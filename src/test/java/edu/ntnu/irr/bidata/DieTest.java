package edu.ntnu.irr.bidata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DieTest {

  @Test
  @DisplayName("Max and min values are set correctly")
  void testMaxAndMinValuesOfRoll() {
    Die myDie = new Die(6);
    boolean areAllRollsInRange = true;
    for (int i = 0; i < 1000; i++) {
      int roll = myDie.roll();
      if (roll > 6 || roll < 1) {
        areAllRollsInRange = false;
      }
    }
    assertTrue(areAllRollsInRange, "All rolls are in a valid range between 1 and 6");
  }
}
