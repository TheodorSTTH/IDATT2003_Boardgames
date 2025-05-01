package edu.ntnu.irr.bidata.View.CreatePlayer;

import javafx.scene.Scene;

public class CreatePlayerPage extends Scene {
    private static final CreatePlayerLayout layout = new CreatePlayerLayout();

    public CreatePlayerPage() {
        super(layout);

        if (getClass().getResource("/style.css") != null) {
            this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } else {
            System.err.println("Warning: style.css not found!");
        }
    }
}