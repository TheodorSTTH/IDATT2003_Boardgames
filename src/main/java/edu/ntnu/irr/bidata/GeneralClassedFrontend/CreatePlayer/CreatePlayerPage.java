package edu.ntnu.irr.bidata.GeneralClassedFrontend.CreatePlayer;


import javafx.scene.Scene;

public class CreatePlayerPage extends Scene {
  private static final CreatePlayerLayout layout = new CreatePlayerLayout();

  public CreatePlayerPage() {
    super(layout);
    this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
  }
  
}