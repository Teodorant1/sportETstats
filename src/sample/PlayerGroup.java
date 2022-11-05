package sample;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerGroup {

    int score = 0;
    int time = 0;
    HashMap<String, Player> playerHashMap = new HashMap<>();
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<event> events = new ArrayList<>();

    public int getTimeFromString(String TimeString) {

        twoitemholder twoitemholder = new twoitemholder();
        String countdown = TimeString;
        String[] prototime = countdown.split(":");
        if (prototime[0].equals("10")) {
            return 600;
        } else {
            int minutes = Integer.parseInt(prototime[0]);
            int seconds = Integer.parseInt(prototime[1]);
            int totaltime = (minutes * 60) + seconds;
            return totaltime;
        }
    }

    public int getDeltaTime(String START, String END) {
        int starttime = getTimeFromString(START);
        int endtime = getTimeFromString(END);
        int delta = starttime - endtime;

        return delta;
    }


    public void sliceToStats(ArrayList<event> slice) {


        twoitemholder twoitemholder = new twoitemholder();
        twoitemholder.setStart(slice.get(0).getTime());
        twoitemholder.setEnd(slice.get(slice.size() - 1).getTime());
        int letime = getDeltaTime(twoitemholder.getStart(),twoitemholder.getEnd());
        System.out.println(" And the time this slice happened over is "+letime);

        System.out.println(slice.get(0).getTeamname());
        for (int i = 0; i < slice.size(); i++) {
            this.setScore(getScore() + slice.get(i).getPoints());
            if (slice.get(i).description.equals("Tiro libero segnato")) {
                this.setScore(getScore() + 1);
            }

            if (slice.get(i).getPoints() > 0) {
                System.out.println(slice.get(i).getPlayer() + "  " + slice.get(i).getDescription());
            }

        }
        System.out.println(" Total score is: " + getScore());
    }


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
