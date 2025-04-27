package edu.ntnu.irr.bidata.Model.LadderGame.Event;
import edu.ntnu.irr.bidata.Wiew.PopUp;


public class QizzEvent extends Event {
  private String question;
  private String answer;
  private int tileNumber;

  public QizzEvent(String question, String answer, int tilenumber) {
    this.question = question;
    this.answer = answer;
    this.tileNumber = tilenumber;
  }

  @Override
  public int Action() {
    if (PopUp.askQuestion(question, answer)) {
      return tileNumber+3;
    } else {
      return tileNumber-3;
    }
  }
}
