package edu.ntnu.irr.bidata;

public class Tile{
    private final int laderDestination;
    
    public Tile(int laderDestination) {
        this.laderDestination = laderDestination;
    }
    
    public int TileAction() {
        return laderDestination;
    }
}
