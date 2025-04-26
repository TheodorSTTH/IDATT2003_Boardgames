package edu.ntnu.irr.bidata.Model.LadderGame;

import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.LadderGame.Event.Event;
import edu.ntnu.irr.bidata.Model.LadderGame.Event.TileMaker;

public class BoardLaderGame {
    @JsonIgnore
    private HashMap<Integer, Event> events = new HashMap<Integer, Event>();
    @JsonIgnore
    private int endTile = 90;
    private HashMap<String, Integer> playerPositions = new HashMap<String, Integer>();

    public BoardLaderGame() {
        setUpLadersClasic();
    }

    public void setPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            playerPositions.put(player.getName(), 0);
        }
    }

    public void move(Player player, int steps) {
        playerPositions.put(player.getName(), playerPositions.get(player.getName()) + steps);
        if (events.containsKey(playerPositions.get(player.getName()))) {
            playerPositions.put(player.getName(), events.get(playerPositions.get(player.getName())).Action());
        } 
    }
    
    public boolean hasWone(Player player) {
        return playerPositions.get(player.getName()) >= endTile;
    }

    public HashMap<String, Integer> getPlayerPositions() {
        return playerPositions;
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

    public void saveBoard(String gameName) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(gameName+".board.json"), this);
            System.out.println("Board saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BoardLaderGame loadBoard(String gameName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(gameName+".board.json"), BoardLaderGame.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}   
