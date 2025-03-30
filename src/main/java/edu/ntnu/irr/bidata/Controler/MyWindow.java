package edu.ntnu.irr.bidata.Controler;


import edu.ntnu.irr.bidata.Model.LadderGame.BoardLaderGame;
import edu.ntnu.irr.bidata.Wiew.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main window for my application!
 */
public class MyWindow extends Application {
  private static Stage primaryStage;


  //@Override
  //public void start(Stage primaryStage) {
  //  MyWindow.primaryStage = primaryStage;
  //  MyWindow.primaryStage.setTitle("BoardGame");
  //
  //  // Switch scenes AFTER JavaFX is ready
  //  UI.toAmountOfPlayersPage();
  //}
  @Override
  public void start(Stage primaryStage) {
    MyWindow.primaryStage = primaryStage;
    MyWindow.primaryStage.setTitle("BoardGame");
    
    // Switch scenes AFTER JavaFX is ready
    UI.toStartPage();




    // BoardLaderGame myBoardGame = new BoardLaderGame();
    // LaderGame ladderGame = new LaderGame(myBoardGame);
    // primaryStage.setTitle("Stigespill");
    // Pane root = new Pane();
    // BoardView boardDisplay = new BoardView(myBoardGame);
    // root.getChildren().add(boardDisplay);
    // primaryStage.setScene(new Scene(root, 600, 600));
    // primaryStage.show();


  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args); // Launch the JavaFX application
  }
}
