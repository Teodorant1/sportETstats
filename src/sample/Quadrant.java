package sample;

import java.util.ArrayList;

public class Quadrant {



    public Quadrant(int quadrantID, ArrayList<event> quadrantevents) {
        this.quadrantID = quadrantID;
        this.quadrantevents = quadrantevents;
    }

    public Quadrant() {
    }




    int quadrantID;
    ArrayList<ArrayList<event>> quadrantslices;
    ArrayList<event> quadrantevents;




















    public ArrayList<event> getQuadrantevents() {
        return quadrantevents;
    }

    public void setQuadrantevents(ArrayList<event> quadrantevents) {
        this.quadrantevents = quadrantevents;
    }



    public int getQuadrantID() {
        return quadrantID;
    }

    public void setQuadrantID(int quadrantID) {
        this.quadrantID = quadrantID;
    }

    public ArrayList<ArrayList<event>> getQuadrantslices() {
        return quadrantslices;
    }

    public void setQuadrantslices(ArrayList<ArrayList<event>> quadrantslices) {
        this.quadrantslices = quadrantslices;
    }
}
