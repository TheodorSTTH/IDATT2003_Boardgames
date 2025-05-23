package edu.ntnu.irr.bidata.model.newlogic;

import javafx.scene.paint.Color;

public class Player {
  private String name;
  private int age;
  private String color;

  public Player(String name, int age, String color) {
    this.color = color;
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }
  public int getAge() {
    return age;
  }
  public String getColor() {
    return color;
  }
}
