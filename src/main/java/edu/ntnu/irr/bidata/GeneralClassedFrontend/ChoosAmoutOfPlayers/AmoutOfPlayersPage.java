package edu.ntnu.irr.bidata.GeneralClassedFrontend.ChoosAmoutOfPlayers;

import javafx.scene.Scene;

public class AmoutOfPlayersPage extends Scene {
  private static final AmountOfPlayersLayout layout = new AmountOfPlayersLayout();

  public AmoutOfPlayersPage() {
      super(layout);  
      this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
  }
}
