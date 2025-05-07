package edu.ntnu.irr.bidata.Model;

import java.io.*;
import java.util.*;

import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Model.SnakesAndLadders.BoardSnakesAndLadders;
import edu.ntnu.irr.bidata.Model.SnakesAndLadders.SnakesAndLadders;
import edu.ntnu.irr.bidata.Model.SnakesAndLadders.Event.Event;
import edu.ntnu.irr.bidata.Model.SnakesAndLadders.Event.EventMaker;
import edu.ntnu.irr.bidata.Model.Risk.BoardRisk;

/**
  * Handles saving and loading of game data such as players, boards, and game state.
  * Supports both SnakesAndLadders and Risk game types, as well as quiz questions.
  * The data is stored in CSV files for easy access and manipulation.
  * 
  *<p>
  * Note: When i added a file path insted of just storing the file in the project folder,
  * i used Chat GTP to go through the code and add change the file path in all the methods
  * that esisted at that point in the project.
  *<p>
  */
public class FileHandler {

  // The directory where all save files are stored.
  private static final String FILE_DIRECTORY = "src/main/resources/files/";

  /**
   * Builds the full file path from a filename relative to the base directory.
   * 
   * <p>
   * Note: this method was created by chat GTP when he changed the file path in all the methods
   * <p<
   *
   * @param fileName The name of the file (e.g., "SavedGames.csv")
   * @return The full path to the file
   */
  private static String getFilePath(String fileName) {
    return FILE_DIRECTORY + fileName;
  }

  /**
   * Saves the given game depending on its type (SnakesAndLadders or Risk).
   *
   * @param game The game object to be saved
   */
  public static void saveGame(Game game) {
    if (game instanceof SnakesAndLadders) {
      saveSnakesAndLadders(game); // Save SnakesAndLadders-specific state
    } else if (game instanceof Risk) {
      saveRiskGame(game); // Save Risk-specific state
    } else {
      throw new IllegalArgumentException("Unknown game type");
    }
  }

  /**
   * Loads a game based on its name and type.
   *
   * @param name The name of the saved game
   * @param type The type of game ("SnakesAndLadders" or "Risk")
   * @return A reconstructed Game object
   */
  public static Game loadGame(String name, String type) {
    switch (type) {
      case "SnakesAndLadders":
        return loadSnakesAndLadders(name);
      case "Risk":
        return loadRiskGame(name);
      default:
        throw new IllegalArgumentException("Unknown game type");
    }
  }

  /**
   * Loads the list of saved games from the CSV file.
   *
   * @return A map from game name to game type
   */
  public static HashMap<String, String> getSavedGames() {
    HashMap<String, String> savedGames = new HashMap<>();
    File file = new File(getFilePath("SavedGames.csv"));

    if (!file.exists())
      return savedGames; // No games saved yet

    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        if (!line.isEmpty()) {
          String[] data = line.split(",");
          if (data.length == 2) {
            savedGames.put(data[0], data[1]); // name -> type
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return savedGames;
  }

  /**
   * Adds a game to the saved games list by appending it to the CSV file.
   *
   * @param game The game to be recorded
   */
  private static void addGameToSavedGames(Game game) {
    String name = game.getGameName();
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }

    try (
        FileWriter fileWriter = new FileWriter(getFilePath("SavedGames.csv"), true);
        PrintWriter writer = new PrintWriter(fileWriter)) {
      writer.println(name + "," + game.getGameType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Removes a game from the saved games list by filtering it out and rewriting the file.
   *
   * @param name The name of the game to remove
   */
  private static void removeGameFromSavedGames(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }

    File file = new File(getFilePath("SavedGames.csv"));
    if (!file.exists())
      return;

    List<String> remainingLines = new ArrayList<>();

    // Read all lines and skip the one that matches the game name
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!line.startsWith(name + ",")) {
          remainingLines.add(line); // Keep unrelated entries
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }

    // Rewrite the file without the removed game
    try (PrintWriter writer = new PrintWriter(file)) {
      for (String line : remainingLines) {
        writer.println(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
  * Deletes all saved files related to a specific game.
  *
  * @param name The name of the game to delete
  */
  public static void deleteGame(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }

    // Delete the player data file
    File file = new File(getFilePath(name + ".players.csv"));
    file.delete();

    // Delete the game state file
    file = new File(getFilePath(name + ".gameState.csv"));
    file.delete();

    // Delete the board configuration (JSON)
    file = new File(getFilePath(name + ".board.json"));
    file.delete();

    // Remove the game from the saved games index
    removeGameFromSavedGames(name);
  }

  /**
  * Saves all players in the game to a CSV file.
  *
  * @param game The game object containing player data
  */
  private static void savePlayers(Game game) {
    if (game.getGameName() == null || game.getGameName().isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }

    try (PrintWriter writer = new PrintWriter(getFilePath(game.getGameName() + ".players.csv"))) {
      // Each player is saved in their CSV-friendly format (e.g., name, color, age)
      for (Player player : game.getPlayers()) {
        writer.println(player.getSaveFormat());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
  * Loads the list of players from the saved CSV file.
  *
  * @param gameName The name of the saved game
  * @return A list of Player objects reconstructed from file
  */
  private static ArrayList<Player> loadPlayers(String gameName) {
    if (gameName == null || gameName.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }

    ArrayList<Player> players = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(getFilePath(gameName + ".players.csv")))) {
      while (scanner.hasNextLine()) {
        String[] data = scanner.nextLine().split(",");

        // Expect exactly 3 fields: name, color, age
        if (data.length != 3) {
          throw new IllegalArgumentException("Invalid player data format");
        }

        try {
          // Validate that age is a number
          Integer.parseInt(data[2]);
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Invalid player age format");
        }

        players.add(new Player(data[0], data[1], Integer.parseInt(data[2])));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return players;
  }

  /**
  * Saves the current game state, such as the current player and available troops (if Risk).
  *
  * @param game The game object to save state from
  */
  private static void saveGameState(Game game) {
    if (game.getGameName() == null || game.getGameName().isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }

    try (PrintWriter writer = new PrintWriter(getFilePath(game.getGameName() + ".gameState.csv"))) {
      // Save current player name
      writer.print(game.getCurrentPlayer().getName());

      // Save available troops if the game is Risk
      if (game instanceof Risk) {
        writer.print("," + ((Risk) game).getTroopsAvailable());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
  * Loads the current player from the saved game state file.
  *
  * @param gameName The name of the game to load
  * @param players The list of all players in the game
  * @return The Player object representing the current player
  */
  private static Player loadCurrentPlayer(String gameName, ArrayList<Player> players) {
    if (gameName == null || gameName.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }

    Player player = null;

    try (Scanner scanner = new Scanner(new File(getFilePath(gameName + ".gameState.csv")))) {
      String[] data = scanner.nextLine().split(",");

      // Match player by name
      for (Player p : players) {
        if (p.getName().equals(data[0])) {
          player = p;
          break;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return player;
  }

  /**
   * Loads the number of available troops for a saved Risk game.
   *
   * @param gameName The name of the saved game
   * @return The number of available troops
   */
  private static int loadAvailableTroops(String gameName) {
    if (gameName == null || gameName.isEmpty()) {
      throw new IllegalArgumentException("Invalid File Name");
    }

    int troops = 0;

    try (Scanner scanner = new Scanner(new File(getFilePath(gameName + ".gameState.csv")))) {
      String[] data = scanner.nextLine().split(",");
      troops = Integer.parseInt(data[1]); // Assumes the second value is the troop count
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return troops;
  }

  /**
  * Saves a Snakes and Ladders game to the file system.
  *
  * @param game The SnakesAndLadders game instance
  */
  private static void saveSnakesAndLadders(Game game) {
    savePlayers(game);
    saveGameState(game);
    saveBoardLadderGame((SnakesAndLadders) game);
    addGameToSavedGames(game);
  }

  /**
  * Saves a Risk game to the file system.
  *
  * @param game The Risk game instance
  */
  private static void saveRiskGame(Game game) {
    savePlayers(game);
    saveGameState(game);
    saveBoardRisk((Risk) game);
    addGameToSavedGames(game);
  }

  /**
  * Loads a Snakes and Ladders game from saved files.
  *
  * @param name The name of the saved game
  * @return A SnakesAndLadders instance reconstructed from saved data
  */
  private static SnakesAndLadders loadSnakesAndLadders(String name) {
    ArrayList<Player> players = loadPlayers(name);
    return new SnakesAndLadders(
        players.size(), name, players,
        loadBoardLadderGame(name),
        loadCurrentPlayer(name, players));
  }

  /**
  * Loads a Risk game from saved files.
  *
  * @param name The name of the saved game
  * @return A Risk instance reconstructed from saved data
  */
  private static Risk loadRiskGame(String name) {
    ArrayList<Player> players = loadPlayers(name);
    return new Risk(
        players.size(), name, players,
        loadBoardRisk(name),
        loadCurrentPlayer(name, players),
        loadAvailableTroops(name));
  }

  /**
  * Loads a Snakes and Ladders board from a file.
  *
  * @param name The game name used as the board file identifier
  * @return A BoardSnakesAndLadders instance
  */
  private static BoardSnakesAndLadders loadBoardLadderGame(String name) {
    return BoardSnakesAndLadders.loadBoard(name);
  }

  /**
  * Loads a Risk board from a file.
  *
  * @param name The game name used as the board file identifier
  * @return A BoardRisk instance
  */
  private static BoardRisk loadBoardRisk(String name) {
    return BoardRisk.loadBoard(name);
  }

  /**
  * Saves the board of a Snakes and Ladders game to file.
  *
  * @param game The SnakesAndLadders instance to save
  */
  private static void saveBoardLadderGame(SnakesAndLadders game) {
    game.getBoard().saveBoard(game.getGameName());
  }

  /**
  * Saves the board of a Risk game to file.
  *
  * @param game The Risk instance to save
  */
  private static void saveBoardRisk(Risk game) {
    game.getBoard().saveBoard(game.getGameName());
  }

  /**
  * Loads a random quiz question and answer pair from a file.
  *
  * @return A list with two elements: question (index 0) and answer (index 1)
  */
  public static List<String> getRandomQuizQuestion() {
    List<String> questionAndAnswer = new ArrayList<>();
    List<String> allLines = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(getFilePath("QuizQuestion.csv")))) {
      // Read all lines from the file
      while (scanner.hasNextLine()) {
        allLines.add(scanner.nextLine());
      }

      if (!allLines.isEmpty()) {
        // Select a random line and split it into question and answer
        Random random = new Random();
        String randomLine = allLines.get(random.nextInt(allLines.size()));
        String[] parts = randomLine.split(",", 2);

        if (parts.length == 2) {
          questionAndAnswer.add(parts[0].trim());
          questionAndAnswer.add(parts[1].trim());
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return questionAndAnswer;
  }

  /**
  * Loads event data for a Snakes and Ladders board from a CSV file.
  *
  * @param boardType The type of board (used to identify the file)
  * @return A map of tile positions to their corresponding event
  */
  public static HashMap<Integer, Event> loadSnakesAndLaddersEvents(String boardType) {
    HashMap<Integer, Event> events = new HashMap<>();

    try (Scanner scanner = new Scanner(new File(getFilePath("EventSetup" + boardType + ".csv")))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");

        String eventType = data[0];
        int tile = Integer.parseInt(data[1]);

        if (eventType.equals("ladder")) {
          int destination = Integer.parseInt(data[2]);
          events.put(tile, EventMaker.newLadder(destination));
        } else if (eventType.equals("question")) {
          events.put(tile, EventMaker.newQuizTile(tile));
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return events;
  }
}