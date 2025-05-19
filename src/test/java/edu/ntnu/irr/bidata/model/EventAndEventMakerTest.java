package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.irr.bidata.model.snakesandladders.event.Event;
import edu.ntnu.irr.bidata.model.snakesandladders.event.EventMaker;
import edu.ntnu.irr.bidata.model.snakesandladders.event.LadderEvent;
import edu.ntnu.irr.bidata.model.snakesandladders.event.QuizEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link EventMaker} and related {@link Event} subclasses such as {@link
 * LadderEvent} and {@link QuizEvent}.
 *
 * <p>These tests verify that the EventMaker correctly creates instances of specific event types,
 * and that those instances behave as expected.
 */
public class EventAndEventMakerTest {

  /**
   * Tests the {@link EventMaker} factory methods for creating {@link LadderEvent} and {@link
   * QuizEvent}.
   *
   * <p>Asserts that the returned objects are instances of the expected types and that their
   * internal state (like tile number and question content) is correctly initialized.
   */
  @Test
  @DisplayName("EventMakker")
  void testEventMaker() {
    Event ladder = EventMaker.newLadder(20);
    Event quizEvent = EventMaker.newQuizTile(15);

    assertTrue(ladder instanceof LadderEvent, "Expected a LadderEvent instance");
    assertTrue(quizEvent instanceof QuizEvent, "Expected a QuizEvent instance");

    assertNotNull(((QuizEvent) quizEvent).getQuestion(), "Quiz question should not be null");
    assertNotNull(((QuizEvent) quizEvent).getAnswer(), "Quiz answer should not be null");
    assertEquals(15, ((QuizEvent) quizEvent).getTileNumber(), "QuizEvent should be on tile 15");

    assertEquals(20, ((LadderEvent) ladder).getDestination(), "Ladder should go to tile 20");
  }

  /**
   * Tests the behavior of a {@link LadderEvent} by verifying the return value of the {@link
   * LadderEvent#action()} method.
   */
  @Test
  @DisplayName("LadderEvent test")
  void testLadderEvent() {
    Event ladder = EventMaker.newLadder(20);
    assertEquals(20, ladder.action(), "Ladder action should return the destination tile number");
  }
}
