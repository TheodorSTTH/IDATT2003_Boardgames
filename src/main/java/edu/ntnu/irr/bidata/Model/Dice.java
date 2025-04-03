package edu.ntnu.irr.bidata.Model;
import java.util.ArrayList;

public class Dice {
  ArrayList<Die> dices = new ArrayList<Die>();

  public Dice(int amountOfDices, int amountOfFaces) {
    for (int i = 0; i < amountOfDices; i++) {
      dices.add(new Die(amountOfFaces));
    }
  }

  public int roll() {
    int result = 0;
    for (Die die : dices) {
      result += die.roll();
    }
    return result;
  }

  public ArrayList<Integer> rollSet () {
    ArrayList<Integer> result = new ArrayList<Integer>();
    for (Die die : dices) {
      result.add(die.roll());
    }
    return result;
  }  
}
