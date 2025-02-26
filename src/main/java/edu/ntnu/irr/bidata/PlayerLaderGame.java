package edu.ntnu.irr.bidata;

public class PlayerLaderGame extends Player {
    private int currentTile;

    public PlayerLaderGame(String name) {
        super(name);
        this.currentTile = 0;
    }

    public int getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(int currentTile) {
        this.currentTile = currentTile;
    }
  
}
