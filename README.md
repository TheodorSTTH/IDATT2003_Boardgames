# Portfolio project IDATT2003

STUDENT NAME = "Imre Hamborg Romstad"  
STUDENT ID = "137455"  
STUDENT NAME = "Theodor Strøm-Thrane"  
STUDENT ID = "137421"

## Project description

This project is a portfolio submission for the IDATT2003 course at NTNU. It involves creating a system for managing board games and players using Java. The system incorporates object-oriented design principles.

## Project structure
```
IDATT2003_Boardgames/
│
├── IDATT2003_Boardgames-main/
│   ├── .gitignore
│   ├── README.md
│   ├── pom.xml
│   ├── .checkstyle/
│   │   └── google_checks.xml
│   └── src/
│       ├── main/
│       │   ├── resources/
│       │   └── java/
│       │       └── edu/
│       │           └── ntnu/
│       │               └── irr/
│       │                   └── bidata/
│       │                       ├── Main.java
│       │                       ├── MyWindow.java
│       │                       ├── NavigationManager.java
│       │                       ├── Model/
│       │                       |   ├── snakesandladders/
│       │                       |   |    ├── BoardSnakesAndLadders
│       │                       |   |    ├── SnakesAndLadders
│       │                       |   |    └── event/
│       │                       |   |        ├── Event
│       │                       |   |        ├── EventMaker
│       │                       |   |        ├── LadderEvent
│       │                       |   |        └── QuizEvent
│       │                       |   ├── risk/
│       │                       |   ├── interfaces/
│       │                       │   ├── Player.java
│       │                       │   ├── Die.java
│       │                       │   ├── Dice.java
│       │                       │   ├── FileHandler.java
│       │                       │   └── Game.java
│       │                       | 
│       │                       ├── View/
│       │                       │   ├── CreatePlayerPageView.java
│       │                       │   ├── DieView.java
│       │                       │   ├── StartPageView.java
│       │                       │   ├── WinningPageView.java
│       │                       │   ├── risk/
│       │                       │   │   ├── AbstractSidebarPaneView.java
│       │                       │   │   ├── AttackPaneView.java
│       │                       │   │   ├── CountryView.java
│       │                       │   │   ├── MoveTroopsPaneView.java
│       │                       │   │   ├── PlaceTroopsPaneView.java
│       │                       │   │   ├── RiskBoardView.java
│       │                       │   │   ├── RiskPage.java
│       │                       │   │   ├── RiskPageView.java
│       │                       │   │   └── RiskSidePanelView.java
│       │                       │   └── snakesandladders/
│       │                       │       ├── CanvasTileView.java
│       │                       │       ├── SnakesAndLaddersCanvasView.java
│       │                       │       ├── SnakesAndLaddersPageView.java
│       │                       │       └── SnakesAndLaddersSidePanelView.java
│       │                       └── controller/
│       │                           ├── CreatePlayerPageController.java
│       │                           ├── StartPageController.java
│       │                           ├── WinningPageController.java
│       │                           ├── risk/
│       │                           │   ├── AbstractSidebarPaneController.java
│       │                           │   ├── AttackPaneController.java
│       │                           │   ├── CountryController.java
│       │                           │   ├── MoveTroopsPaneController.java
│       │                           │   ├── PlaceTroopsPaneController.java
│       │                           │   └── RiskSidePanelController.java
│       │                           │   ├── RiskPageController.java
│       │                           └── snakesandladders/
│       │                               ├── SnakesAndLaddersSidePanelController.java
│       │                               └── SnakesAndLaddersCanvasController.java
```

## Link to repository

[https://github.com/TheodorSTTH/IDATT2003_Boardgames](https://github.com/TheodorSTTH/IDATT2003_Boardgames)

## How to run the project

To run the program, type the following command in the terminal from the project root:

`mvn javafx:run

## How to run the tests

To run the tests, type the following command in the terminal from the project root:

`mvn test`

## References
