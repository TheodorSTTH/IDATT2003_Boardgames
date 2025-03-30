package edu.ntnu.irr.bidata.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ntnu.irr.bidata.Controler.Game;
import edu.ntnu.irr.bidata.Controler.LaderGame;
import edu.ntnu.irr.bidata.Controler.Risk;
import javafx.concurrent.Task;

public class FileHandeler {
  
  public static void saveGame(Game game) {
  }

  public static Game loadGame(String name) {
    return null;
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
      PrintWriter writer = new PrintWriter(game.getGameName() + ".txt");
      for (Player player : game.getPlayers()) {
        writer.println(player.getSaveFormat());
      }
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void loadPlyers(Game game) {
    if (game.getGameName() == null || game.getGameName().isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    try {
      Scanner scanner = new Scanner(new File(game.getGameName() + ".txt"));
      while (scanner.hasNextLine()) {
        String[] data = scanner.nextLine().split(",");
        game.getPlayers().add(new Player(data[0], Integer.parseInt(data[1])));
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  

  private static LaderGame loadLaderGame(String name) {
    // Implement the logic to load a LaderGame object from a file
    return null;
  }

  private static void saveRiskGame(Risk game) {
    // Implement the logic to save the Risk object to a file
  }

  private static Risk loadRiskGame(String name) {
    // Implement the logic to load a Risk object from a file
    return null;
  }
}
