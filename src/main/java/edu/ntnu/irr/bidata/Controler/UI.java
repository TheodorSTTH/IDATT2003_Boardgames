package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.View.CreatePlayer.CreatePlayerPage;
import edu.ntnu.irr.bidata.View.StartPage.StartPagePage;
import edu.ntnu.irr.bidata.Model.FileHandler;

// TODO: Separate start page logic form UI class
public class UI {
  private static Game game;

  public static void StartPageCreateNewGameButon(int plyers, String gameType, String gameName) {
    if (gameType.equals("Lader Game Classic")) {
      game = new LaderGame(plyers, gameName, "classic");
    } else if (gameType.equals("Lader Game Qizz")) {
      game = new LaderGame(plyers, gameName, "qizz");
    } 
    else if (gameType.equals("Risk")) {
      game = new Risk(plyers, gameName);
    }
    NavigationManager.switchScene(new CreatePlayerPage());
  }

  public static boolean newPlayer(String name, String color) {
    return (game.addPlayer(name, color));
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
    PopUp.showInfo("Game Over", "Winner: " + winner);
  }

  public static void saveGame() {
    game.saveGame();
    MyWindow.getPrimaryStage().hide();
    MyWindow.getPrimaryStage().setMaximized(false);
    PopUp.showInfo("Game saved", "Game has been saved as " + game.getGameName());
  }

  public static void loadGame(String gameName, String gameType) {
    game = FileHandler.loadGame(gameName, gameType);
    game.startSavedGame();
  }
}