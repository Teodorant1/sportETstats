package sample;

import org.json.JSONObject;

public class Player {


    public Player(int personalscore, int playtime, String fullname) {
        this.personalscore = personalscore;
        this.playtime = playtime;
        this.fullname = fullname;
    }


    public void printPoints() {
        System.out.println(this.getPersonalscore());
    }

    public JSONObject getOverview() {
        JSONObject playerreportcard = new JSONObject();

        double a = personalscore;
        double b = playtime;
    //    System.out.println(" pointsratio is "+  Double.valueOf(SCTratio) );
        playerreportcard.put("name", this.getFullname());
        playerreportcard.put("points", this.getPersonalscore());
    //    playerreportcard.put("pointsratio", SCTratio);
    //  playerreportcard.put("time",this.getPlaytime());
        return playerreportcard;
    }


    public Player() {
    }

    int personalscore = 0;
    int playtime = 0;
    String fullname = "placeholder";

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getPersonalscore() {
        return personalscore;
    }

    public void setPersonalscore(int personalscore) {
        this.personalscore = personalscore;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }
}
