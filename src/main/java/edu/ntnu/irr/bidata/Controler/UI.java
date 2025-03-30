package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Wiew.AlertMessage;
import edu.ntnu.irr.bidata.Wiew.CreatePlayer.CreatePlayerPage;
import edu.ntnu.irr.bidata.Wiew.LadderGameOverview.OverviewPage;
import edu.ntnu.irr.bidata.Wiew.StartPage.StartPagePage;


public class UI {
  private static final CreatePlayerPage createPlayer = new CreatePlayerPage();
  private static final StartPagePage StartPage = new StartPagePage();
  private static final OverviewPage overview = new OverviewPage();
  private static Game game;

  public static void triggerNewRound() {
    game.takeAction();
    overview.getLayout().getBoardCard().updateBoard(game.getPlayers());
  }


  public static void StartPageCreateNewGameButon(int plyers, String gameType, String gameName) {
    if (gameType.equals("Lader Game")) {
      game = new LaderGame(plyers, gameName);
    } else if (gameType.equals("Risk")) {
      game = new Risk(plyers, gameName);
    }
    toCreatePlayerPage();
  }

  public static void newPlayer(String name) {
    AlertMessage.showInfo("Player Added", name + " has been added to the game.");
    game.addPlayer(name);
  }
  

  public static void toStartPage() {
    System.out.println("Switching to AmountOfPlayersPage...");
    try {
      MyWindow.getPrimaryStage().hide();
      MyWindow.getPrimaryStage().setMaximized(false);
      MyWindow.getPrimaryStage().setScene(StartPage);
      MyWindow.getPrimaryStage().setMaximized(true);
      MyWindow.getPrimaryStage().show();
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  private static void toCreatePlayerPage() {
    System.out.println("Switching to CreatePlayerPage...");
    MyWindow.getPrimaryStage().hide();
    MyWindow.getPrimaryStage().setMaximized(false);
    MyWindow.getPrimaryStage().setScene(createPlayer);
    MyWindow.getPrimaryStage().setMaximized(true);
    MyWindow.getPrimaryStage().show();
  }

  public static void toLaderGamePage() {
    System.out.println("Switching to OverviewPage...");
    MyWindow.getPrimaryStage().hide();
    MyWindow.getPrimaryStage().setMaximized(false);
    MyWindow.getPrimaryStage().setScene(overview);
    MyWindow.getPrimaryStage().setMaximized(true);
    MyWindow.getPrimaryStage().show();
    UI.overview.getLayout().getBoardCard().updateBoard(game.getPlayers());
  }

  public static Game getGame() {
    return game;
  }

  public static void setGame(Game savedGame) {
    game = savedGame;
  }

  public static void endGame(String winner) {
    MyWindow.getPrimaryStage().hide();
    MyWindow.getPrimaryStage().setMaximized(false);
    AlertMessage.showInfo("Game Over", "Winner: " + winner);
  }
}