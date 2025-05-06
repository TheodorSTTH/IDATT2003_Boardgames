package edu.ntnu.irr.bidata.Model;

import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISimpleObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISimpleSubject;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISubject;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a single die with a given amount of faces.
 * */
public class Die implements ISubject<Integer> {
  private final Random RANDOM = new Random();
  private final int amountOfFaces;
  private int previousRoll;
  private boolean wasRolledPreviousRound;

  private final ArrayList<IObserver<Integer>> allObservers;

  @Override
  public void registerObserver(IObserver<Integer> o) {
    allObservers.add(o);
  }

  @Override
  public void removeObserver(IObserver<Integer> o) {
    allObservers.remove(o);
  }

  @Override
  public void notifyObservers(Integer amountOfDots) {
    for (IObserver<Integer> observer : allObservers) {
      observer.update(amountOfDots);
    }
  }

  public Die(int amountOfFaces) {
    this.amountOfFaces = amountOfFaces;
    this.allObservers = new ArrayList<>();
    this.wasRolledPreviousRound = false;
  }

  /**
   * Rolls the die and returns the result.
   *
   * @return random number between 1 and the amount of faces given
   * */
  public int roll() {
    int rollResult = RANDOM.nextInt(1, amountOfFaces + 1);
    this.previousRoll = rollResult;
    notifyObservers(rollResult);
    setWasRolledPreviousRound(true);
    return rollResult;
  }

  public int getPreviousDieRoll() {
    return previousRoll;
  }

  public void setWasRolledPreviousRound(boolean wasRolled) {
    this.wasRolledPreviousRound = wasRolled;
  }

  public boolean getWasRolledPreviousRound() {
    return wasRolledPreviousRound;
  }
}
