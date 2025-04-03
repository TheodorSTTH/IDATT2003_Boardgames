package edu.ntnu.irr.bidata.Controler;


import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;

public class UILaderGame extends UI {
  private static LaderGame ladderGame;

  public static void setLadderGame(LaderGame ladderGame) {
    UILaderGame.ladderGame = ladderGame;
  }

  public static void triggerNewRound() {
    ladderGame.takeAction();
    overview.getLayout().getBoardCard().updateBoard(ladderGame.getPlayers());
  }

    public static void toLaderGamePage() {
    System.out.println("Switching to OverviewPage...");
    MyWindow.getPrimaryStage().hide();
    MyWindow.getPrimaryStage().setMaximized(false);
    MyWindow.getPrimaryStage().setScene(overview);
    MyWindow.getPrimaryStage().setMaximized(true);
    MyWindow.getPrimaryStage().show();
    UI.overview.getLayout().getBoardCard().updateBoard(ladderGame.getPlayers());
  } 


   
}
