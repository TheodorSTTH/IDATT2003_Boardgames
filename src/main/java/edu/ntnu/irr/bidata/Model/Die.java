package edu.ntnu.irr.bidata.Model;

import java.util.Random;
import java.util.ArrayList;

/**
 * Represents a single die with a given amount of faces.
 * */
public class Die {
  private final Random RANDOM = new Random();
  private final int amountOfFaces;

  public Die(int amountOfFaces) {
    this.amountOfFaces = amountOfFaces;
  }

  /**
   * Rolls the die and returns the result.
   *
   * @return random number between 1 and the amount of faces given
   * */
  public int roll() {
    return RANDOM.nextInt(1, amountOfFaces+1);
  }
}
