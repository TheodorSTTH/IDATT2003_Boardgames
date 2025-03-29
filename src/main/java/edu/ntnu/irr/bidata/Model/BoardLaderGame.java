package edu.ntnu.irr.bidata.Model;
import java.util.HashMap;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BoardLaderGame {
    private HashMap<Integer, Integer> GameBorad = new HashMap<Integer, Integer>();

    public BoardLaderGame() {
        this.setUpLadersClasic();
    }

    private void setUpLadersClasic() {
        for (int i = 1; i < 90 + 1; i++) {
            GameBorad.put(i, i);
        }

        GameBorad.put(1, 40);
        GameBorad.put(8, 10);
        GameBorad.put(24, 5);
        GameBorad.put(33, 3);
        GameBorad.put(36, 52);
        GameBorad.put(42, 30);
        GameBorad.put(56, 37);
        GameBorad.put(64, 27);
        GameBorad.put(65, 82);
        GameBorad.put(68, 85);
        GameBorad.put(74, 12);
        GameBorad.put(87, 70);
    }

    public int landOnTile(int tileNumber){
        return GameBorad.get(tileNumber);
    }

    public HashMap<Integer, Integer> getGameBorad() {
        return GameBorad;
    }
}   
