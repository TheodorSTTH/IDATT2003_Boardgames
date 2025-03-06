package edu.ntnu.irr.bidata.GeneralClassedFrontend.ChoosAmoutOfPlayers;

import edu.ntnu.irr.bidata.GeneralClassedFrontend.CreatePlayer.CreatePlayerCard;
import javafx.scene.layout.StackPane;

public class AmountOfPlayersLayout extends StackPane {
    public AmountOfPlayersLayout() {
    super();
    this.getStyleClass().add("createPlayer-layout");
    this.getChildren().add(new CreatePlayerCard());
  }
  
}
