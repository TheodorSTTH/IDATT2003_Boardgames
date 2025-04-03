package edu.ntnu.irr.bidata.NewLogic.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of multiple die. Is responsible for managing dice
 * values and methods.
 * */
public class Dice {
  private List<Die> dice;

  public Dice(int numberOfDice) {
    dice = new ArrayList<>();
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die());
    }
  }

  public int rollAll() {
    int summedRolls = 0;
    for (Die die : dice) {
      summedRolls += die.roll();
    }
    return summedRolls;
  }
}
