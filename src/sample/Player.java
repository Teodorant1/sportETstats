package sample;

public class Player {


    public Player(int personalscore, int playtime) {
        this.personalscore = personalscore;
        this.playtime = playtime;
    }

    int personalscore = 0;
    int playtime = 0;

    public Player() {}

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
