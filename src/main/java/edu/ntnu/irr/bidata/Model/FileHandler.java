package edu.ntnu.irr.bidata.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Model.SnakesAndLadders.BoardSnakesAndLadders;
import edu.ntnu.irr.bidata.Model.SnakesAndLadders.SnakesAndLadders;
import edu.ntnu.irr.bidata.Model.SnakesAndLadders.Event.Event;
import edu.ntnu.irr.bidata.Model.SnakesAndLadders.Event.EventMaker;
import edu.ntnu.irr.bidata.Model.Risk.BoardRisk;

public class FileHandler {

  // Specify the folder where files are stored
  private static final String FILE_DIRECTORY = "src/main/resources/files/";

  // Utility method to get the full path for a file in the folder
  private static String getFilePath(String fileName) {
    return FILE_DIRECTORY + fileName;
  }

  public static void saveGame(Game game) {
    if (game instanceof SnakesAndLadders) {
      saveSnakesAndLadders(game);
    } else if (game instanceof Risk) {
      saveRiskGame(game);
    } else {
      throw new IllegalArgumentException("Unknown game type");
    }
  }

  public static Game loadGame(String name, String type) {
    if (type.equals("SnakesAndLadders")) {
      return loadSnakesAndLadders(name);
    } else if (type.equals("Risk")) {
      return loadRiskGame(name);
    } else {
      throw new IllegalArgumentException("Unknown game type");
    }
  }

  public static HashMap<String, String> getSavedGames() {
    HashMap<String, String> savedGames = new HashMap<>();
    try {
      Scanner scanner = new Scanner(new File(getFilePath("SavedGames.csv")));
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
    try (FileWriter fileWriter = new FileWriter(getFilePath("SavedGames.csv"), true);
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
      Scanner scanner = new Scanner(new File(getFilePath("SavedGames.csv")));
      ArrayList<String> lines = new ArrayList<>();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!line.startsWith(name)) {
          lines.add(line);
        }
      }
      scanner.close();
      PrintWriter writer = new PrintWriter(getFilePath("SavedGames.csv"));
      for (String line : lines) {
        writer.println(line);
      }
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void deleteGame(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    File file = new File(getFilePath(name + ".players.csv"));
    file.delete();
    file = new File(getFilePath(name + ".gameState.csv"));
    file.delete();
    file = new File(getFilePath(name + ".board.json"));
    file.delete();
    removeGameFromSavedGames(name);
  }

  private static void savePlyers(Game game) {
    if (game.getGameName() == null || game.getGameName().isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    try {
      PrintWriter writer = new PrintWriter(getFilePath(game.getGameName() + ".players.csv"));
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
      Scanner scanner = new Scanner(new File(getFilePath(gameName + ".players.csv")));
      while (scanner.hasNextLine()) {
        String[] data = scanner.nextLine().split(",");
        if (data.length != 3) {
          throw new IllegalArgumentException("Invalid player data format");
        }
        try {
          Integer.parseInt(data[2]);
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Invalid player age format");
        }
        players.add(new Player(data[0], data[1], Integer.parseInt(data[2])));
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return players;
  }

  private static void saveGameState(Game game) {
    if (game.getGameName() == null || game.getGameName().isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    try {
      PrintWriter writer = new PrintWriter(getFilePath(game.getGameName() + ".gameState.csv"));
      writer.print(game.getCurrentPlayer().getName());
      if (game instanceof Risk) {
        writer.print("," + ((Risk) game).getTroopsAvailable());
      }
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
      Scanner scanner = new Scanner(new File(getFilePath(gameName + ".gameState.csv")));
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

  private static int loadAvalibleTroops(String gameName) {
    if (gameName == null || gameName.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }
    int troops = 0;
    try {
      Scanner scanner = new Scanner(new File(getFilePath(gameName + ".gameState.csv")));
      String[] data = scanner.nextLine().split(",");
      troops = Integer.parseInt(data[1]);
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return troops;
  }



  private static void saveSnakesAndLadders(Game game) {
    savePlyers(game);
    saveGameState(game);
    saveBoardLadderGame((SnakesAndLadders) game);
    addGameToSavedGames(game);
  }

  private static void saveRiskGame(Game game) {
    savePlyers(game);
    saveGameState(game);
    saveBoardRisk((Risk) game);
    addGameToSavedGames(game);
  }

  private static SnakesAndLadders loadSnakesAndLadders(String name) {
    ArrayList<Player> players = loadPlyers(name);
    return new SnakesAndLadders(players.size(), name, players, loadBoardLadderGame(name), loadCurrentPlayer(name, players));
  }

  private static Risk loadRiskGame(String name) {
    ArrayList<Player> players = loadPlyers(name);
    return new Risk(players.size(), name, players, loadBoardRisk(name), loadCurrentPlayer(name, players), loadAvalibleTroops(name));
  }

  private static BoardSnakesAndLadders loadBoardLadderGame(String name) {
    return BoardSnakesAndLadders.loadBoard(name);
  }

  private static BoardRisk loadBoardRisk(String name) {
    return BoardRisk.loadBoard(name);
  }

  private static void saveBoardLadderGame(SnakesAndLadders game) {
    game.getBoard().saveBoard(game.getGameName());
  }

  private static void saveBoardRisk(Risk game) {
    game.getBoard().saveBoard(game.getGameName());
  }

  public static List<String> getRandomQuizQuestion() {
    List<String> questionAndAnswer = new ArrayList<>();
    List<String> allLines = new ArrayList<>();
    try {
      Scanner scanner = new Scanner(new File(getFilePath("QuizQuestion.csv")));
      // Read all lines into a list
      while (scanner.hasNextLine()) {
        allLines.add(scanner.nextLine());
      }
      scanner.close();

      // Pick a random line if the file is not empty
      if (!allLines.isEmpty()) {
        Random random = new Random();
        String randomLine = allLines.get(random.nextInt(allLines.size()));

        // Split the line into question and answer
        // Assuming the format is like: "Question,Answer"
        String[] parts = randomLine.split(",", 2);

        if (parts.length == 2) {
          questionAndAnswer.add(parts[0].trim()); // index 0: question
          questionAndAnswer.add(parts[1].trim()); // index 1: answer
        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return questionAndAnswer;
  }

  public static HashMap<Integer, Event> loadSnakesAndLaddersEvents(String boardType) {
    HashMap<Integer, Event> events = new HashMap<>();

    try {
      Scanner scanner = new Scanner(new File(getFilePath("EventSetup" + boardType + ".csv")));
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        String eventType = data[0];
        int tile = Integer.parseInt(data[1]);
        if (eventType.equals("ladder")) {
          int destination = Integer.parseInt(data[2]);
          events.put(tile, EventMaker.newLadder(destination));
        } else if (eventType.equals("qestion")) {
          events.put(tile, EventMaker.newQuizTile(tile));
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return events;
  }
}