package edu.ntnu.irr.bidata.model.SnakesAndLadders.Event;

import edu.ntnu.irr.bidata.View.PopUp;

/**
 * Represents a quiz event in the Snakes and Ladders game.
 *
 * <p>When a player lands on this tile, they must answer a question. If the answer is correct, they
 * move forward 3 tiles; if incorrect, they move back 3 tiles.
 */
public class QuizEvent extends Event {
  private String question; // The question presented to the player
  private String answer; // The correct answer to the question
  private int tileNumber; // The current tile number the player is on

  /**
   * Default no-argument constructor.
   *
   * <p>Required for JSON serialization/deserialization frameworks like Jackson.
   */
  public QuizEvent() {
    // No initialization needed here
  }

  /**
   * Constructs a QuizEvent with a question, its answer, and the tile number.
   *
   * @param question the question to ask the player
   * @param answer the correct answer to the question
   * @param tileNumber the current tile number the event occurs on
   */
  public QuizEvent(String question, String answer, int tileNumber) {
    this.question = question;
    this.answer = answer;
    this.tileNumber = tileNumber;
  }

  /**
   * Executes the quiz event logic.
   *
   * <p>If the player answers correctly via a pop-up, they move forward 3 tiles. Otherwise, they
   * move back 3 tiles.
   *
   * @return the new tile number after the quiz is resolved
   */
  @Override
  public int action() {
    if (PopUp.askQuestion(question, answer)) {
      return tileNumber + 3; // Correct answer: move forward
    } else {
      return tileNumber - 3; // Wrong answer: move backward
    }
  }

  // Getters and setters

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public int getTileNumber() {
    return tileNumber;
  }

  public void setTileNumber(int tileNumber) {
    this.tileNumber = tileNumber;
  }
}
