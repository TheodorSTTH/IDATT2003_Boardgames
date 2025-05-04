package edu.ntnu.irr.bidata.Model.LadderGame;

import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ntnu.irr.bidata.Model.FileHandler;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.LadderGame.Event.Event;
import edu.ntnu.irr.bidata.Model.LadderGame.Event.EventMaker;

public class BoardLaderGame {
    @JsonProperty
    private HashMap<Integer, Event> events = new HashMap<Integer, Event>();
    @JsonProperty
    private int endTile = 90;
    @JsonProperty
    private HashMap<Player, Integer> playerPositions = new HashMap<Player, Integer>();

    public BoardLaderGame() {
        // Default constructor for Json
    }
    
    
    public BoardLaderGame(String boardType) {
        events = FileHandler.loadLaderGameEvents(boardType);

    }

    public void setPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            playerPositions.put(player, 0);
        }
    }

    public void move(Player player, int steps) {
        playerPositions.put(player, playerPositions.get(player) + steps);
        if (events.containsKey(playerPositions.get(player))) {
            playerPositions.put(player, events.get(playerPositions.get(player)).Action());
        } 
    }
    
    public boolean hasWon(Player player) {
        return playerPositions.get(player) >= endTile;
    }

    public HashMap<Player, Integer> getPlayerPositions() {
        return playerPositions;
    }

    public void addEvent(int tile, Event event) {
        events.put(tile, event);
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<Player>(playerPositions.keySet());
    }


    private void setUpClassicSnakesAndLadders() {
        events.put(2, EventMaker.newLadder(40));
        events.put(8, EventMaker.newLadder(10));
        events.put(24, EventMaker.newLadder(5));
        events.put(33, EventMaker.newLadder(3));
        events.put(36, EventMaker.newLadder(52));
        events.put(42, EventMaker.newLadder(30));
        events.put(56, EventMaker.newLadder(37));
        events.put(64, EventMaker.newLadder(27));
        events.put(65, EventMaker.newLadder(82));
        events.put(68, EventMaker.newLadder(68));
        events.put(74, EventMaker.newLadder(12));
        events.put(87, EventMaker.newLadder(70));
        events.put(7, EventMaker.newQizzTile(7));
        events.put(17, EventMaker.newQizzTile(17));
        events.put(28, EventMaker.newQizzTile(28));
        events.put(37, EventMaker.newQizzTile(37));
        events.put(47, EventMaker.newQizzTile(47));
        events.put(57, EventMaker.newQizzTile(57));
        events.put(67, EventMaker.newQizzTile(67));
        events.put(78, EventMaker.newQizzTile(78));
        events.put(89, EventMaker.newQizzTile(89));

    }

    public void saveBoard(String gameName) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File("src/main/resources/files/"+gameName+".board.json"), this);
            System.out.println("Board saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BoardLaderGame loadBoard(String gameName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/files/"+gameName+".board.json"), BoardLaderGame.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}   
