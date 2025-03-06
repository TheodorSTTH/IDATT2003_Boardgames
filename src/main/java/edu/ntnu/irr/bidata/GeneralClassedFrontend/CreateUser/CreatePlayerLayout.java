package edu.ntnu.irr.bidata.GeneralClassedFrontend.CreateUser;

import javafx.scene.layout.StackPane;


public class CreatePlayerLayout extends StackPane {
  public CreatePlayerLayout() {
    super();
    this.getStyleClass().add("createUser-layout");
    this.getChildren().add(new CreatePlayerCard());
  }
}