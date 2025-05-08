package edu.ntnu.irr.bidata.model;

import java.util.ArrayList;

/**
 * Represents a collection of dice that can be rolled together. Useful for games that use multiple
 * dice.
 */
public class Dice {
  private ArrayList<Die> dice = new ArrayList<>(); // List of individual dice

  /**
   * Constructs a Dice object with the specified number of dice and faces per die.
   *
   * @param numberOfDice number of dice to include
   * @param numberOfFaces number of faces on each die
   */
  public Dice(int numberOfDice, int numberOfFaces) {
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die(numberOfFaces)); // Add each die with given face count
    }
  }

  /**
   * Rolls all dice and returns the total sum.
   *
   * @return the sum of the results of all dice rolls
   */
  public int roll() {
    int result = 0;
    for (Die die : dice) {
      result += die.roll(); // Sum up results
    }
    return result;
  }

  /**
   * Rolls a specified number of dice. Dice not rolled will have their "wasRolledPreviousRound" flag
   * set to false.
   *
   * @param numberOfDice number of dice to roll
   * @return list of results from rolled dice
   */
  public ArrayList<Integer> rollSetOfDice(int numberOfDice) {
    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 0; i < dice.size(); i++) {
      Die die = dice.get(i);
      if (i < numberOfDice) {
        result.add(die.roll()); // Roll this die
      } else {
        die.setWasRolledPreviousRound(false); // Mark it as not used this round
      }
    }
    return result;
  }

  /**
   * Returns the list of Die objects.
   *
   * @return list of dice
   */
  public ArrayList<Die> getDice() {
    return dice;
  }
}
