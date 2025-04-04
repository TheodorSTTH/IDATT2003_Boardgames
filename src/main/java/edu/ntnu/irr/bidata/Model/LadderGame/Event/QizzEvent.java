package edu.ntnu.irr.bidata.Model.LadderGame.Event;

public class QizzEvent extends Event {
  private Question question;

  public QizzEvent(Question question) {
    this.question = question;
  }

  @Override
  public int Action() {
    return 0;
  }




  
}
