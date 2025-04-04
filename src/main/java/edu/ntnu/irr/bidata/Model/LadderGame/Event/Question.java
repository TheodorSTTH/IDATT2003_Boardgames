package edu.ntnu.irr.bidata.Model.LadderGame.Event;

public class Question {
  private String question;
  private String[] alternativs;
  private String correctAnswer;

  public Question(String question, String[] alternativs, String correctAnswer) {
    this.question = question;
    this.alternativs = alternativs;
    this.correctAnswer = correctAnswer;
  }

  public String getQuestion() {
    return question;
  }

  public String[] getAlternativs() {
    return alternativs;
  }

  public boolean isCorrectAnswer(String answer) {
    return correctAnswer.equals(answer);
  }
}
