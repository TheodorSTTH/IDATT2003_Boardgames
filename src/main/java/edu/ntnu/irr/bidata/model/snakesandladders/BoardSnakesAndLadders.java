package edu.ntnu.irr.bidata.model.snakesandladders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.model.snakesandladders.event.Event;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the board of the Snakes and Ladders game, which contains events and player positions.
 *
 * <p>The board is serialized into a JSON format to be saved and loaded for game persistence.
 */
public class BoardSnakesAndLadders {
  private static final Logger log = LoggerFactory.getLogger(BoardSnakesAndLadders.class);
  @JsonProperty
  private HashMap<Integer, Event> events =
      new HashMap<Integer, Event>(); // Map of events associated with tile numbers

  @JsonProperty private int endTile = 90; // The tile number that marks the end of the game

  @JsonProperty
  private HashMap<String, Integer> playerPositions =
      new HashMap<String, Integer>(); // Player names mapped to their positions

  /** Default constructor required for JSON serialization. */
  public BoardSnakesAndLadders() {
    // Default constructor for Json
  }

  /**
   * Constructor that initializes the board with events based on a given board type.
   *
   * @param boardType the type of board to load (e.g., Classic, Quiz)
   */
  public BoardSnakesAndLadders(String boardType) throws UncheckedIOException {
    events = FileHandler.loadSnakesAndLaddersEvents(boardType); // Load events from file
  }

  /**
   * Moves a player a given number of steps forward. If the player lands on a tile with an event,
   * the event’s action is applied, and player position is updated accordingly.
   *
   * @param player the player to move
   * @param steps the number of steps to move forward
   */
  public void move(Player player, int steps) {
    int currentPosition = playerPositions.get(player.getName());
    playerPositions.put(player.getName(), currentPosition + steps); // Move the player
    if (events.containsKey(playerPositions.get(player.getName()))) {
      // Apply the event action if there's an event at the new position
      playerPositions.put(
          player.getName(), events.get(playerPositions.get(player.getName())).action());
    }
  }

  /**
   * Checks if the player has won the game by reaching or exceeding the end tile.
   *
   * @param player the player to check
   * @return true if the player has won, false otherwise
   */
  public boolean hasWon(Player player) {
    return playerPositions.get(player.getName()) >= endTile;
  }

  /**
   * Gets the events map, where tile numbers is associated with an event.
   *
   * @return a HashMap of events with their respective tile numbers
   */
  public HashMap<Integer, Event> getEvents() {
    return events;
  }

  /**
   * Sets up the players on the board, initializing their positions to 0.
   *
   * @param players the list of players to add to the board
   */
  public void setPlayers(ArrayList<Player> players) {
    for (Player player : players) {
      playerPositions.put(player.getName(), 0); // Initialize player positions to 0
    }
  }

  /**
   * Gets the current positions of all players.
   *
   * @return a HashMap of player names and their respective positions
   */
  public HashMap<String, Integer> getPlayerPositions() {
    return playerPositions;
  }

  /**
   * Adds an event to the specified tile.
   *
   * @param tile the tile number where the event will be placed
   * @param event the event to associate with the tile
   */
  public void addEvent(int tile, Event event) {
    events.put(tile, event);
  }

  /**
   * Gets the list of player names.
   *
   * <p>This method is ignored during JSON serialization. Because the player names are stored in the
   * playerPositions map, and the dobele serialization of the player names lead to their Position
   * being serialized set to zero when loding.
   *
   * @return a list of player names
   */
  @JsonIgnore
  public ArrayList<String> getPlayers() {
    return new ArrayList<String>(playerPositions.keySet());
  }

  /**
   * Saves the current state of the board to a JSON file.
   *
   * <p>This methode is based on a simuler method suggested by ChatGPT.<\p>
   *
   * @param gameName the name to save the board file as
   */
  public void saveBoard(String gameName) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty-print the JSON
    try {
      objectMapper.writeValue(
          new File("src/main/resources/files/" + gameName + ".board.json"), this);
      log.info("Board saved successfully.");
    } catch (IOException e) {
      // Handle any IO exceptions during saving
      log.error("Something went wrong saving snakes and ladders board", e);
      throw new UncheckedIOException("Something went wrong saving snakes and ladders board", e);
    }
  }

  /**
   * Loads a saved board state from a JSON file.
   *
   * <p>This methode is based on a simuler method suggested by ChatGPT.<\p>
   *
   * @param gameName the name of the board file to load
   * @return the loaded BoardSnakesAndLadders instance, or null if loading failed
   */
  public static BoardSnakesAndLadders loadBoard(String gameName) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(
          new File("src/main/resources/files/" + gameName + ".board.json"),
          BoardSnakesAndLadders.class);
    } catch (IOException e) {
      log.error("Something went wrong loading board", e);
      throw new UncheckedIOException("Something went wrong loading board", e);
    }
  }
}
