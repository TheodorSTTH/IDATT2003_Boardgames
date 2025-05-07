package edu.ntnu.irr.bidata.model.SnakesAndLadders.Event;

/**
 * Represents a ladder event in the Snakes and Ladders game.
 *
 * <p>When triggered, this event moves the player to a new destination square.
 */
public class LadderEvent extends Event {
  private int destination; // The square number the player moves to when landing on a ladder

  /**
   * Default no-argument constructor.
   *
   * <p>Required for JSON serialization/deserialization frameworks like Jackson.
   */
  public LadderEvent() {
    // No initialization needed here
  }

  /**
   * Constructs a LadderEvent with a specific destination square.
   *
   * @param destination the board square the player will move to
   */
  public LadderEvent(int destination) {
    this.destination = destination;
  }

  /**
   * Executes the ladder event logic by returning the destination square.
   *
   * @return the destination square number
   */
  @Override
  public int action() {
    return destination;
  }

  /**
   * Gets the destination square.
   *
   * @return the destination square number
   */
  public int getDestination() {
    return destination;
  }

  /**
   * Sets the destination square.
   *
   * @param destination the new destination square number
   */
  public void setDestination(int destination) {
    this.destination = destination;
  }
}
