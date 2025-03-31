package edu.ntnu.irr.bidata.NewLogic.models;

public interface IGameFactory<T extends IPlayer<?>> {
  IGame<T> createGame();
  //IGame<T> getGameFromFile(String filepath); TODO: Add loading games from files
}
