package edu.ntnu.irr.bidata.Controler;


import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.View.LadderGameOverview.OverviewPage;

public class UILaderGame extends UI {
  private static LaderGame ladderGame;
  protected static final OverviewPage overview = new OverviewPage();

  public static void setLadderGame(LaderGame ladderGame) {
    UILaderGame.ladderGame = ladderGame;
  }

  public static void triggerNewRound() {
    ladderGame.takeAction();
    overview.getLayout().getBoardCard().updateBoard(ladderGame.getPlayerPositions());
  }
}
