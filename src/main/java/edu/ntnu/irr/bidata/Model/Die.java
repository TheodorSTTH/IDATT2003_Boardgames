package edu.ntnu.irr.bidata.model;

import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.interfaces.observer.Subject;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a single die with a specified number of faces. Implements the Observer pattern,
 * notifying observers when the die is rolled.
 */
public class Die implements Subject<Integer> {
  private final Random random = new Random(); // Random number generator
  private final int amountOfFaces; // Total number of faces on the die
  private int previousRoll; // Stores result of the last roll
  private boolean wasRolledPreviousRound; // Tracks if the die was rolled in the last round
  private final ArrayList<Observer<Integer>> allObservers; // List of observers

  /**
   * Constructs a Die with a specified number of faces.
   *
   * @param amountOfFaces number of sides on the die (e.g., 6 for a regular die)
   */
  public Die(int amountOfFaces) {
    this.amountOfFaces = amountOfFaces;
    this.allObservers = new ArrayList<>();
    this.wasRolledPreviousRound = false;
  }

  /**
   * Rolls the die and notifies observers of the result.
   *
   * @return a random number between 1 and amountOfFaces (inclusive)
   */
  public int roll() {
    int rollResult = random.nextInt(1, amountOfFaces + 1); // Random result
    this.previousRoll = rollResult;
    notifyObservers(rollResult); // Notify all observers
    setWasRolledPreviousRound(true);
    return rollResult;
  }

  /**
   * Gets the result of the previous die roll.
   *
   * @return the result of the last roll
   */
  public int getPreviousDieRoll() {
    return previousRoll;
  }

  /**
   * Sets whether the die was rolled in the previous round.
   *
   * @param wasRolled true if the die was rolled last round
   */
  public void setWasRolledPreviousRound(boolean wasRolled) {
    this.wasRolledPreviousRound = wasRolled;
  }

  /**
   * Checks if the die was rolled in the previous round.
   *
   * @return true if rolled last round, false otherwise
   */
  public boolean getWasRolledPreviousRound() {
    return wasRolledPreviousRound;
  }

  /**
   * Registers an observer to receive roll updates.
   *
   * @param o the observer to add
   */
  @Override
  public void registerObserver(Observer<Integer> o) {
    allObservers.add(o);
  }

  /**
   * Removes an observer.
   *
   * @param o the observer to remove
   */
  @Override
  public void removeObserver(Observer<Integer> o) {
    allObservers.remove(o);
  }

  /**
   * Notifies all registered observers of the roll result.
   *
   * @param amountOfDots the result of the die roll
   */
  @Override
  public void notifyObservers(Integer amountOfDots) {
    for (Observer<Integer> observer : allObservers) {
      observer.update(amountOfDots);
    }
  }
}
