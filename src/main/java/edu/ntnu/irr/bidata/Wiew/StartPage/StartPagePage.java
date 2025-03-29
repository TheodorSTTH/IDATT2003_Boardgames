package edu.ntnu.irr.bidata.Wiew.StartPage;

import javafx.scene.Scene;

public class StartPagePage extends Scene {
    private static final StartPageLayout layout = new StartPageLayout();

    public StartPagePage() {
        super(layout);  
        if (getClass().getResource("/style.css") != null) {
            this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } else {
            System.err.println("Warning: style.css not found!");
        }
    }
}