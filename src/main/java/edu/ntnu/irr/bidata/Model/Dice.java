package edu.ntnu.irr.bidata.Model;
import java.util.ArrayList;

public class Dice {
  ArrayList<Die> dice = new ArrayList<Die>();

  public Dice(int amountOfDices, int amountOfFaces) {
    for (int i = 0; i < amountOfDices; i++) {
      dice.add(new Die(amountOfFaces));
    }
  }

  public int roll() {
    int result = 0;
    for (Die die : dice) {
      result += die.roll();
    }
    return result;
  }

  public ArrayList<Integer> rollSet() {
    return rollSet(dice.size());
  }

  public ArrayList<Integer> rollSet(int amountOfDies) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    for (int i = 0; i < this.dice.size(); i++) {
      Die die = dice.get(i);
      if (i < amountOfDies) {
        result.add(die.roll());
      } else {
        die.setWasRolledPreviousRound(false);
      }
    }
    return result;
  }

  public ArrayList<Die> getDice() {
    return dice;
  }
}
