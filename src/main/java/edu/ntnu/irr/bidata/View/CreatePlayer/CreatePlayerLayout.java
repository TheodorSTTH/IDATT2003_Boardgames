package edu.ntnu.irr.bidata.View.CreatePlayer;

import javafx.scene.layout.StackPane;

public class CreatePlayerLayout extends StackPane {
    public CreatePlayerLayout() {
        super();
        this.getStyleClass().add("createPlayer-layout");
        this.getChildren().add(new CreatePlayerCard()); // Ensure CreatePlayerCard is properly initialized
    }
}