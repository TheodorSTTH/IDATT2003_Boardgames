package edu.ntnu.irr.bidata.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import javafx.concurrent.Task;

public class FileHandeler {
  
  public static void saveGame(Game game) {
    if (game instanceof LaderGame) {
      saveLaderGame((LaderGame) game);
    } else if (game instanceof Risk) {
      saveRiskGame((Risk) game);
    } else {
      throw new IllegalArgumentException("Unknown game type");
    }
  }

  public static Game loadGame(String name, String type) {
    if (type.equals("LaderGame")) {
      return loadLaderGame(name);
    } else if (type.equals("Risk")) {
      return loadRiskGame(name);
    } else {
      throw new IllegalArgumentException("Unknown game type");
    }
  }

  public static void deleteGame(String name) {

  }

  public static ArrayList<String> getSavedGames() {
    return new ArrayList<>();
  }
  
  private static void savePlyers(Game game) {
    if (game.getGameName() == null || game.getGameName().isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    try {
      PrintWriter writer = new PrintWriter(game.getGameName() + ".players" + ".txt");
      for (Player player : game.getPlayers()) {
        writer.println(player.getSaveFormat());
      }
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static ArrayList<Player> loadPlyers(String gameName) {
    if (gameName == null || gameName.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    ArrayList<Player> players = new ArrayList<>();
    try {
      Scanner scanner = new Scanner(new File(gameName + ".players" + ".txt"));
      while (scanner.hasNextLine()) {
        String[] data = scanner.nextLine().split(",");
        players.add(new Player(data[0], Integer.parseInt(data[1])));
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return players;
  }

  private static void saveCurrentPlayer(Game game) {
    if (game.getGameName() == null || game.getGameName().isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    try {
      PrintWriter writer = new PrintWriter(game.getGameName() + ".currentPlayer" + ".txt");
      writer.println(game.getCurrentPlayer().getSaveFormat());
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static Player loadCurrentPlayer(String gameName) {
    if (gameName == null || gameName.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    Player player = null;
    try {
      Scanner scanner = new Scanner(new File(gameName + ".currentPlayer" + ".txt"));
      String[] data = scanner.nextLine().split(",");
      player = new Player(data[0], Integer.parseInt(data[1]));
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return player;
  }

  private static void saveLaderGame(LaderGame game) {
    savePlyers(game);
    saveCurrentPlayer(game);

  }

  private static LaderGame loadLaderGame(String name) {
    ArrayList<Player> players = loadPlyers(name); 
    LaderGame laderGame = new LaderGame(players.size(), name);
    laderGame.addPlayers(players);
    laderGame.setCurrentPlayer(loadCurrentPlayer(name));
    return laderGame;
  }

  private static void saveRiskGame(Risk game) {
    // Implement the logic to save the Risk object to a file
  }

  private static Risk loadRiskGame(String name) {
    // Implement the logic to load a Risk object from a file
    return null;
  }
}
