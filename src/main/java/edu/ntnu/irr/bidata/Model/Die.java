package edu.ntnu.irr.bidata.Model;
import java.util.Random;

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
  public static int roll(int faces) {
    return RANDOM.nextInt(1, faces + 1);
  }

  public static int rollDies(int faces, int amount) {
    int sum = 0;
    for (int i = 0; i < amount; i++) {
      sum += RANDOM.nextInt(1, faces + 1);
    }
    return sum;
  }


}
