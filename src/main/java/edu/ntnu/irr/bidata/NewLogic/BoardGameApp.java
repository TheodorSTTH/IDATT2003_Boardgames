package edu.ntnu.irr.bidata.NewLogic;

import edu.ntnu.irr.bidata.NewLogic.controllers.GameController;
import edu.ntnu.irr.bidata.NewLogic.models.LadderGame;
import edu.ntnu.irr.bidata.NewLogic.models.LadderGameFactory;
import edu.ntnu.irr.bidata.NewLogic.models.LadderPlayer;
import edu.ntnu.irr.bidata.NewLogic.views.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardGameApp extends Application {
  LadderGame ladderGame;

  @Override
  public void start(Stage primaryStage) {
    LadderGameFactory ladderGameFactory = new LadderGameFactory(2);
    ladderGame = ladderGameFactory.createGame();
    GameView view = new GameView(ladderGame);
    new GameController(ladderGame, view);
    Scene scene = new Scene(view, 600, 400);

    primaryStage.setTitle("Ladder Game");
    primaryStage.setScene(scene);
    primaryStage.setMaximized(true);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}