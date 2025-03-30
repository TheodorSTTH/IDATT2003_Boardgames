package edu.ntnu.irr.bidata.NewLogic.models;

/**
 * Represents a single physical 6-headed die.
 * */
public class Die {
  private int lastRolledValue;
  public int roll() {
    int min = 1;
    int max = 6;
    int randomRoll = (int) (Math.random()*(max-min+1)+min);
    this.lastRolledValue = randomRoll;
    return randomRoll;
  }
  public int getValue() {
    return lastRolledValue;
  }
}
