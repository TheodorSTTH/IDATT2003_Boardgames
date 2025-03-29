package edu.ntnu.irr.bidata.Wiew.ChoosAmountOfPlayers;
import javafx.scene.layout.StackPane;

public class AmountOfPlayersLayout extends StackPane {
    public AmountOfPlayersLayout() {
        super();
        this.getStyleClass().add("createPlayer-layout");

        AmountOfPlayersCard playersCard = new AmountOfPlayersCard();
        this.getChildren().addAll(playersCard, new AmountOfPlayersCard());  // Ensure both are present
    }
}