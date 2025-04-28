package edu.ntnu.irr.bidata.Model.LadderGame.Event;
import edu.ntnu.irr.bidata.Wiew.PopUp;


public class QizzEvent extends Event {
  private String question;
  private String answer;
  private int tileNumber;

  public QizzEvent() {
      // For Json Saving and Loading
  }

  public QizzEvent(String question, String answer, int tileNumber) {
      this.question = question;
      this.answer = answer;
      this.tileNumber = tileNumber;
  }

  @Override
  public int Action() {
      if (PopUp.askQuestion(question, answer)) {
          return tileNumber + 3;
      } else {
          return tileNumber - 3;
      }
  }

  public String getQuestion() {
      return question;
  }

  public void setQuestion(String question) {
      this.question = question;
  }

  public String getAnswer() {
      return answer;
  }

  public void setAnswer(String answer) {
      this.answer = answer;
  }

  public int getTileNumber() {
      return tileNumber;
  }

  public void setTileNumber(int tileNumber) {
      this.tileNumber = tileNumber;
  }
}
