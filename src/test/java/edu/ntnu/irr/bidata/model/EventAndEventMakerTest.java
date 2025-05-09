package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.irr.bidata.model.snakesandladders.event.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventAndEventMakerTest {

  @Test
  @DisplayName("EventMakker")
  void testEventMaker() {
    Event ladder = EventMaker.newLadder(20);
    Event quizEvent = EventMaker.newQuizTile(20);

    assertTrue(ladder instanceof LadderEvent);
    assertTrue(quizEvent instanceof QuizEvent);
  }

  @Test
  @DisplayName("LadderEvent test")
  void testLadderEvent() {

    Event ladder = EventMaker.newLadder(20);

    assertEquals(ladder.action(), 20);
  }
}
