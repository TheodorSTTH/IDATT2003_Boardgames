package edu.ntnu.irr.bidata.GeneralClassedFrontend.CreateUser;


import javafx.scene.Scene;

public class CreatePlayerPage extends Scene {
  private static final CreatePlayerLayout layout = new CreatePlayerLayout();

  public CreatePlayerPage() {
    super(layout);
    this.getStylesheets().add(getClass().getResource("/no/ntnu/idatx1005/demo/view/style.css").toExternalForm());
  }
  
}