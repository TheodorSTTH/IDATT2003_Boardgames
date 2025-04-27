package edu.ntnu.irr.bidata.Model.LadderGame.Event;

import edu.ntnu.irr.bidata.Model.FileHandeler;

import java.util.List;

public class EventMaker {
  
  public static Event newLadder(int destination) {
    return new LadderEvent(destination);
  }

  public static Event newQizzTile(int tileNumber) {
    List<String> questionList = FileHandeler.getRandomQizzQestion();
    String question = questionList.get(0);
    String answer = questionList.get(1);
    return new QizzEvent(question, answer, tileNumber);
  }
}
