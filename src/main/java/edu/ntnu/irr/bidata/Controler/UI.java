package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Wiew.AlertInterface;
import edu.ntnu.irr.bidata.Wiew.CreatePlayer.CreatePlayerPage;
import edu.ntnu.irr.bidata.Wiew.StartPage.StartPagePage;

public class UI {
  private static final CreatePlayerPage createPlayer = new CreatePlayerPage();
  private static final StartPagePage StartPage = new StartPagePage();
  private static Game game;



  public static void AmountOfPlayersAndGameChoosen(int plyers, String gameName) {
    if (gameName.equals("Lader Game")) {
      game = new LaderGame(plyers);
    } else if (gameName.equals("Risk")) {
      game = new Risk(plyers);
    }
    toCreatePlayerPage();
  }

  public static void newPlayer(String name) {
    game.addPlayer(name);
    AlertInterface.showInfo("Player Added", name + " has been added to the game.");
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
  
}
