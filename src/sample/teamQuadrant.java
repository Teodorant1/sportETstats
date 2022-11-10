package sample;

import java.util.ArrayList;

public class teamQuadrant {
    String teamname;
    ArrayList<event> teamevents = new ArrayList<>();




    public teamQuadrant() {
    }
    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public ArrayList<event> getTeamevents() {
        return teamevents;
    }

    public void setTeamevents(ArrayList<event> teamevents) {
        this.teamevents = teamevents;
    }
    public teamQuadrant(String teamname, ArrayList<event> teamevents) {
        this.teamname = teamname;
        this.teamevents = teamevents;
    }}
