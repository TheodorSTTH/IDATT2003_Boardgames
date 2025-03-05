package edu.ntnu.irr.bidata.GeneralClassesBackend;
import java.util.Random;

/**
 * Represents a single die with a given amount of faces.
 * */
public class Die {
  private static final Random RANDOM = new Random();
  private final int faces;

  /**
   * @param faces Amount of faces on die
   * */
  public Die(int faces) {
    this.faces = faces;
  }

  /**
   * Rolls the die and returns the result.
   *
   * @return random number between 1 and the amount of faces given
   * */
  public int roll() {
    return RANDOM.nextInt(1, faces + 1);
  }
}
