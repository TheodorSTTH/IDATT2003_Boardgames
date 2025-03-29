package edu.ntnu.irr.bidata.Model;

import java.util.ArrayList;

public abstract class Board {
  private ArrayList<Player> players = new ArrayList<Player>();

  public void addPlayer(String name) {
    players.add(new Player(name));
  }

  public int getAmountOfPlayers() {
    return players.size();
  }

  public Player getPlayer(int index) {
    return players.get(index);
  }
}
