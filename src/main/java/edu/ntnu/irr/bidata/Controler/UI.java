package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.View.CreatePlayer.CreatePlayerPage;
import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.model.Game;
import edu.ntnu.irr.bidata.model.Risk.Risk;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;

// TODO: Separate start page logic form UI class
public class UI {
  private static Game game;

  public static void StartPageCreateNewGameButon(int plyers, String gameType, String gameName) {
    if (gameType.equals("Snakes And Ladders Classic")) {
      game = new SnakesAndLadders(plyers, gameName, "Classic");
    } else if (gameType.equals("Snakes And Ladders Quiz")) {
      game = new SnakesAndLadders(plyers, gameName, "Quiz");
    } 
    else if (gameType.equals("Risk")) {
      game = new Risk(plyers, gameName);
    }
    NavigationManager.navigate(new CreatePlayerPage());
  }

  public static boolean newPlayer(String name, String color, int age) {
    return (game.addPlayer(name, color, age));
  }

  public static Game getGame() {
    return game;
  }

  public static void setGame(Game savedGame) {
    game = savedGame;
  }

  public static void loadGame(String gameName, String gameType) {
    game = FileHandler.loadGame(gameName, gameType);
    game.startSavedGame();
  }
}