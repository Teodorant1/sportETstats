package sample;

public class event {
    String player;
    String description;
    int points = 0;
    String time;
    String teamname;

    public event() {
    }

    public event(String player, String description, String time, String teamname) {
        this.player = player;
        this.description = description;
        this.time = time;
        this.teamname = teamname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


}
