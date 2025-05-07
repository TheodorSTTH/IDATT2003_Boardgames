package edu.ntnu.irr.bidata.Model.SnakesAndLadders.Event;

import edu.ntnu.irr.bidata.Model.FileHandler;

import java.util.List;

public class EventMaker {
  
  public static Event newLadder(int destination) {
    return new LadderEvent(destination);
  }

  public static Event newQizzTile(int tileNumber) {
    List<String> questionList = FileHandler.getRandomQizzQestion();
    String question = questionList.get(0);
    String answer = questionList.get(1);
    return new QuizEvent(question, answer, tileNumber);
  }
}
