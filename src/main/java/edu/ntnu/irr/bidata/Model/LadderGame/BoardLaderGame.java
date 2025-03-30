package edu.ntnu.irr.bidata.Model;
import java.util.HashMap;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BoardLaderGame {
    private HashMap<Integer, Integer> laders = new HashMap<Integer, Integer>();
    private int endTile = 90;

    public BoardLaderGame() {
        setUpLadersClasic();
    }


    private void setUpLadersClasic() {
        laders.put(1, 40);
        laders.put(8, 10);
        laders.put(24, 5);
        laders.put(33, 3);
        laders.put(36, 52);
        laders.put(42, 30);
        laders.put(56, 37);
        laders.put(64, 27);
        laders.put(65, 82);
        laders.put(68, 85);
        laders.put(74, 12);
        laders.put(87, 70);
    }

    public int landOnTile(int tileNumber) {
        if (laders.containsKey(tileNumber)) {
            return laders.get(tileNumber);
        } else {
            return tileNumber;
        }

    }
    
    public boolean hasWone(int tileNumber) {
        return tileNumber >= endTile;
    }
}   
