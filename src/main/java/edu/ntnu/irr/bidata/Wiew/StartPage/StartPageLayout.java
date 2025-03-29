package edu.ntnu.irr.bidata.Wiew.StartPage;
import javafx.scene.layout.StackPane;

public class StartPageLayout extends StackPane {
    public StartPageLayout() {
        super();
        this.getStyleClass().add("createPlayer-layout");

        StartPageCard playersCard = new StartPageCard();
        this.getChildren().addAll(playersCard, new StartPageCard());  // Ensure both are present
    }
}