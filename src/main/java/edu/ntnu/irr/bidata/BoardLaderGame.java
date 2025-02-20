package edu.ntnu.irr.bidata;
import java.util.HashMap;

public class BoardLaderGame extends Board {
    private HashMap<Integer, Tile> GameBorad;
    private HashMap<Integer, Integer> Laders;

    public BoardLaderGame() {
        GameBorad = new HashMap<Integer, Tile>();
        Laders = new HashMap<Integer, Integer>();

    }

    private void setUpLadersClasic() {
        Laders.put(1, 40);
        Laders.put(8, 10);
        Laders.put(24, 5);
        Laders.put(33, 3);
        Laders.put(36, 52);
        Laders.put(42, 30);
        Laders.put(56, 37);
        Laders.put(64, 27);
        Laders.put(65, 82);
        Laders.put(68, 85);
        Laders.put(74, 12);
        Laders.put(87, 70);
    }
    
    @Override
    public void setUp(){
        for (int i = 1; i < 90; i++) {
            GameBorad.put(i, new Tile(i));
        }

        this.setUpLadersClasic();

        for (int i : Laders.keySet()) {
            GameBorad.put(i, new Tile(Laders.get(i)));
        }
    }

    public int landOnTile(int tileNumber){
        return GameBorad.get(tileNumber).TileAction();
    }
}   
