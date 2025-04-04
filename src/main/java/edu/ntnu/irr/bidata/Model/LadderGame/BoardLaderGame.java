package edu.ntnu.irr.bidata.Model.LadderGame;
import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.LadderGame.Event.Event;
import edu.ntnu.irr.bidata.Model.LadderGame.Event.TileMaker;

public class BoardLaderGame {
    private HashMap<Integer, Event> events = new HashMap<Integer, Event>();
    private int endTile = 90;
    private HashMap<Player, Integer> playerPositions = new HashMap<Player, Integer>();

    public BoardLaderGame() {
        setUpLadersClasic();
    }

    public void move(Player player, int steps) {
        playerPositions.put(player, playerPositions.get(player) + steps);
        if (events.containsKey(playerPositions.get(player))) {
            playerPositions.put(player, events.get(playerPositions.get(player)).Action());
        } 
    }
    
    public boolean hasWone(int tileNumber) {
        return tileNumber >= endTile;
    }


    private void setUpLadersClasic() {
        events.put(1, TileMaker.newLadder(40));
        events.put(8, TileMaker.newLadder(10));
        events.put(24, TileMaker.newLadder(5));
        events.put(33, TileMaker.newLadder(3));
        events.put(36, TileMaker.newLadder(52));
        events.put(42, TileMaker.newLadder(30));
        events.put(56, TileMaker.newLadder(37));
        events.put(64, TileMaker.newLadder(27));
        events.put(65, TileMaker.newLadder(82));
        events.put(68, TileMaker.newLadder(68));
        events.put(74, TileMaker.newLadder(12));
        events.put(87, TileMaker.newLadder(70));
    }
}   
