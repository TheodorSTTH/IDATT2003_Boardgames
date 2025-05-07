package edu.ntnu.irr.bidata.Model.SnakesAndLadders;

import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ntnu.irr.bidata.Model.FileHandler;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.SnakesAndLadders.Event.Event;
import edu.ntnu.irr.bidata.Model.SnakesAndLadders.Event.EventMaker;

public class BoardLaderGame {
    @JsonProperty
    private HashMap<Integer, Event> events = new HashMap<Integer, Event>();
    @JsonProperty
    private int endTile = 90;
    @JsonProperty
    private HashMap<String, Integer> playerPositions = new HashMap<String, Integer>();

    public BoardLaderGame() {
        // Default constructor for Json
    }

    public HashMap<Integer, Event> getEvents() {
        return events;
    }
    
    
    public BoardLaderGame(String boardType) {
        events = FileHandler.loadLaderGameEvents(boardType);

    }

    public void setPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            playerPositions.put(player.getName(), 0);
        }
    }

    public void move(Player player, int steps) {
        playerPositions.put(player.getName(), playerPositions.get(player.getName()) + steps);
        if (events.containsKey(playerPositions.get(player.getName()))) {
            playerPositions.put(player.getName(), events.get(playerPositions.get(player.getName())).action());
        } 
    }
    
    public boolean hasWon(Player player) {
        return playerPositions.get(player.getName()) >= endTile;
    }

    public HashMap<String, Integer> getPlayerPositions() {
        return playerPositions;
    }

    public void addEvent(int tile, Event event) {
        events.put(tile, event);
    }

    @JsonIgnore
    public ArrayList<String> getPlayers() {
        return new ArrayList<String>(playerPositions.keySet());
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
