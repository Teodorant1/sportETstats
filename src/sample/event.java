package sample;

public class event {
    String player = "placeholder";
    String description;
    int points = 0;
    String time;
    String teamname;
    int index;
    int quadrant;


    public event() {}

    public event(String player, String description, String time, String teamname, int index , int quadrant ) {
        this.player = player;
        this.description = description;
        this.time = time;
        this.teamname = teamname;
        this.index = index;
        this.quadrant = quadrant;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getQuadrant() {
        return quadrant;
    }

    public void setQuadrant(int quadrant) {
        this.quadrant = quadrant;
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
