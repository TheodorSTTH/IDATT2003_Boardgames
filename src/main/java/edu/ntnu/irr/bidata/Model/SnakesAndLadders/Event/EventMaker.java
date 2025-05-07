package edu.ntnu.irr.bidata.Model.SnakesAndLadders.Event;

import edu.ntnu.irr.bidata.Model.FileHandler;

import java.util.List;

/**
 * Factory class responsible for creating different types of game events.
 * <p>
 * Provides static methods to create predefined events like ladders and quiz tiles.
 */
public class EventMaker {

    /**
     * Creates a new LadderEvent that moves the player to the specified destination.
     *
     * @param destination the board tile the player should move to
     * @return a new instance of LadderEvent
     */
    public static Event newLadder(int destination) {
        return new LadderEvent(destination);
    }

    /**
     * Creates a new QuizEvent at the given tile number.
     * <p>
     * Retrieves a random question and answer from the FileHandler to populate the event.
     *
     * @param tileNumber the tile number where the quiz event will take place
     * @return a new instance of QuizEvent
     */
    public static Event newQuizTile(int tileNumber) {
        // Get a random question-answer pair from file
        List<String> questionList = FileHandler.getRandomQuizQuestion();
        String question = questionList.get(0); // Question text
        String answer = questionList.get(1);   // Correct answer
        return new QuizEvent(question, answer, tileNumber);
    }
}