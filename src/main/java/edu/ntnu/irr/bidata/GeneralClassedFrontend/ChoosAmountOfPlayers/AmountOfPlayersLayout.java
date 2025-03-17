package edu.ntnu.irr.bidata.GeneralClassedFrontend.ChoosAmountOfPlayers;

import edu.ntnu.irr.bidata.GeneralClassedFrontend.CreatePlayer.CreatePlayerCard;
import javafx.scene.layout.StackPane;

public class AmountOfPlayersLayout extends StackPane {
    public AmountOfPlayersLayout() {
        super();
        this.getStyleClass().add("createPlayer-layout");

        AmountOfPlayersCard playersCard = new AmountOfPlayersCard();
        this.getChildren().addAll(playersCard, new CreatePlayerCard());  // Ensure both are present
    }
}