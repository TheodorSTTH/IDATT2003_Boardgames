package edu.ntnu.irr.bidata.Wiew.ChoosAmountOfPlayers;

import javafx.scene.Scene;

public class AmountOfPlayersPage extends Scene {
    private static final AmountOfPlayersLayout layout = new AmountOfPlayersLayout();

    public AmountOfPlayersPage() {
        super(layout);  
        if (getClass().getResource("/style.css") != null) {
            this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } else {
            System.err.println("Warning: style.css not found!");
        }
    }
}