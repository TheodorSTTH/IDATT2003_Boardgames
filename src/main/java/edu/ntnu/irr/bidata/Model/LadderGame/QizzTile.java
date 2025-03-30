package edu.ntnu.irr.bidata.Model.LadderGame;

public class QizzTile extends Tile {
  private Question question;

  public QizzTile(Question question) {
    this.question = question;
  }

  @Override
  public int tlleAction() {
    return 0;
  }




  
}
