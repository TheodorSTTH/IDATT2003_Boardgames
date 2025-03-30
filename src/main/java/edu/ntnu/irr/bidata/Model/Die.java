package edu.ntnu.irr.bidata.Model;

import java.util.Random;
import java.util.ArrayList;

/**
 * Represents a single die with a given amount of faces.
 * */
public class Die {
  private static final Random RANDOM = new Random();

  /**
   * Rolls the die and returns the result.
   *
   * @return random number between 1 and the amount of faces given
   * */
  public static int roll() {
    return RANDOM.nextInt(1, 6);
  }

  public static ArrayList<Integer> rollDies(int amount) {
    ArrayList<Integer> results = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      results.add(RANDOM.nextInt(1, 6));
    }
    return results;
  }


}
