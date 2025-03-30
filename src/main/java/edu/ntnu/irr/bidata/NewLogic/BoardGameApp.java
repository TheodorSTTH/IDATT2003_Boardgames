package edu.ntnu.irr.bidata.NewLogic;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.NewLogic.controllers.GameController;
import edu.ntnu.irr.bidata.NewLogic.models.BoardGame;
import edu.ntnu.irr.bidata.NewLogic.models.Player;
import edu.ntnu.irr.bidata.NewLogic.views.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardGameApp extends Application {
  BoardGame boardGame = new BoardGame(2);

  @Override
  public void start(Stage primaryStage) {
    boardGame.createInitialGameState();

    Player Arne = new Player("Arne", this.boardGame, boardGame.getBoard().getTile(0));
    boardGame.addPlayer(Arne);
    boardGame.addPlayer(new Player("Ivar", this.boardGame, boardGame.getBoard().getTile(0)));
    boardGame.addPlayer(new Player("Majid", this.boardGame, boardGame.getBoard().getTile(0)));
    boardGame.addPlayer(new Player("Atle", this.boardGame, boardGame.getBoard().getTile(0)));
    boardGame.setCurrentPlayer(Arne); // TODO: Refactor to factory or builder to create board game

    GameView view = new GameView(boardGame);
    new GameController(boardGame, view);
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