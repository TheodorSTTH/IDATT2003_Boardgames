package edu.ntnu.irr.bidata.Model.LadderGame;
import java.util.HashMap;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BoardLaderGame {
    private HashMap<Integer, Tile> laders = new HashMap<Integer, Tile>();
    private int endTile = 90;

    public BoardLaderGame() {
        setUpLadersClasic();
    }


    private void setUpLadersClasic() {
        laders.put(1, TileMaker.newLadder(40));
        laders.put(8, TileMaker.newLadder(10));
        laders.put(24, TileMaker.newLadder(5));
        laders.put(33, TileMaker.newLadder(3));
        laders.put(36, TileMaker.newLadder(52));
        laders.put(42, TileMaker.newLadder(30));
        laders.put(56, TileMaker.newLadder(37));
        laders.put(64, TileMaker.newLadder(27));
        laders.put(65, TileMaker.newLadder(82));
        laders.put(68, TileMaker.newLadder(68));
        laders.put(74, TileMaker.newLadder(12));
        laders.put(87, TileMaker.newLadder(70));
    }

    public int landOnTile(int tile) {
        if (laders.containsKey(tile)) {
            return laders.get(tile).tlleAction();
        } else {
            return tile;
        }
    }
    
    public boolean hasWone(int tileNumber) {
        return tileNumber >= endTile;
    }
}   
