package sample;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VerticalAPIscanner {


    PlayerGroupAnalyzer playerGroupAnalyzer = new PlayerGroupAnalyzer();


    public String maintest(String gameID) throws IOException {
        //23736
        httpfetch httpfetch = new httpfetch();
        Match match = httpfetch.bigFetch(gameID);
        eventSorter eventSorter = new eventSorter(match);

        HashMap<Integer, HashMap<String, ArrayList<ArrayList<event>>>> TeamEvents
                = eventSorter.getTeamSlices();


        JSONArray jsonArray
                = playerGroupAnalyzer
                .getJsonInQuadrants(TeamEvents, eventSorter.QuadrantEvents);
        //  System.out.println(jsonArray);
        return jsonArray.toString();

    }

    public PlayerGroupAnalyzer getPlayerGroupAnalyzer() {
        return playerGroupAnalyzer;
    }

    public void setPlayerGroupAnalyzer(PlayerGroupAnalyzer playerGroupAnalyzer) {
        this.playerGroupAnalyzer = playerGroupAnalyzer;
    }
}
