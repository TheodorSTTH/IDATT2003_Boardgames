package edu.ntnu.irr.bidata.model.newlogic;

import javafx.scene.paint.Color;

public class Player {
  private String name;
  private int age;
  private Color color;

  public Player(String name, int age, Color color) {
    this.color = color;
    this.name = name;
    this.age = age;
  }
}
