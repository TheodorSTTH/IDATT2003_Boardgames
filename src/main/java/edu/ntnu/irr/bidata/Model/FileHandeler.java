package edu.ntnu.irr.bidata.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.LadderGame.Event.Question;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import javafx.concurrent.Task;
import edu.ntnu.irr.bidata.Model.LadderGame.BoardLaderGame;


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

  public static void deleteGame(String name, String type) {
    if (type.equals("LaderGame")) {
      deleteLaderGame(name);
    } else if (type.equals("Risk")) {
      deleteRiskGame(name);
    } else {
      throw new IllegalArgumentException("Unknown game type");
    }
  }

  public static HashMap<String, String> getSavedGames() {
    HashMap<String, String> savedGames = new HashMap<>();
    try {
      Scanner scanner = new Scanner(new File("savedGames.txt"));
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        savedGames.put(data[0], data[1]);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    return savedGames;
  }

  private static void addGameToSavedGames(Game game) {
    if (game.getGameName() == null || game.getGameName().isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    try (FileWriter fileWriter = new FileWriter("savedGames.txt", true);
      PrintWriter writer = new PrintWriter(fileWriter)) {
      writer.println(game.getGameName() + "," + game.getGameType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void removeGameFromSavedGames(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    try {
      Scanner scanner = new Scanner(new File("savedGames.txt"));
      ArrayList<String> lines = new ArrayList<>();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!line.startsWith(name)) {
          lines.add(line);
        }
      }
      scanner.close();
      PrintWriter writer = new PrintWriter("savedGames.txt");
      for (String line : lines) {
        writer.println(line);
      }
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void deleteLaderGame(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    File file = new File(name + ".players.txt");
    file.delete();
    file = new File(name + ".currentPlayer.txt");
    file.delete();
    file = new File(name + ".boardLaderGame.json");
    file.delete();
    removeGameFromSavedGames(name);

  }

  private static void deleteRiskGame(String name){}
  
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
        players.add(new Player(data[0]));
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

  private static Player loadCurrentPlayer(String gameName, ArrayList<Player> players) {
    if (gameName == null || gameName.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    Player player = null;
    try {
      Scanner scanner = new Scanner(new File(gameName + ".currentPlayer" + ".txt"));
      String[] data = scanner.nextLine().split(",");
      for (Player p : players) {
        if (p.getName().equals(data[0])) {
          player = p;
          break;
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return player;
  }

  private static void saveLaderGame(LaderGame game) {
    savePlyers(game);
    saveCurrentPlayer(game);
    saveBoardLadderGame(game);
    addGameToSavedGames(game);
  }

  private static LaderGame loadLaderGame(String name) {
    ArrayList<Player> players = loadPlyers(name);
    return new LaderGame(players.size(), name, players, loadBoardLadderGame(name), loadCurrentPlayer(name, players));
  }
  
  private static BoardLaderGame loadBoardLadderGame(String name) {
    return BoardLaderGame.loadBoardLadergame(name);
  }

  private static void saveBoardLadderGame(LaderGame game) {
    game.getBoard().saveBoardLadergame(game.getGameName());
  }

  private static void saveRiskGame(Risk game) {
    // Implement the logic to save the Risk object to a file
  }

  private static Risk loadRiskGame(String name) {
    // Implement the logic to load a Risk object from a file
    return null;
  }


}
