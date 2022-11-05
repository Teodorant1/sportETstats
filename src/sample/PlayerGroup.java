package sample;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerGroup {

    int score = 0;
    int time = 0;
    HashMap<String, Player> playerHashMap = new HashMap<>();

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<event> events = new ArrayList<>();




    public PlayerGroup() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public ArrayList<event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<event> events) {
        this.events = events;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public HashMap<String, Player> getPlayerHashMap() {
        return playerHashMap;
    }

    public void setPlayerHashMap(HashMap<String, Player> playerHashMap) {
        this.playerHashMap = playerHashMap;
    }
}
