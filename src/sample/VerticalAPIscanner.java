package sample;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VerticalAPIscanner {

    public String maintest(String gameID) throws IOException {
    //23736
        httpfetch httpfetch = new httpfetch();
        Match match = httpfetch.bigFetch(gameID);


        PlayerGroupAnalyzer playerGroupAnalyzer = new PlayerGroupAnalyzer();

        eventSorter eventSorter = new eventSorter(match);

        HashMap<Integer, HashMap<String, ArrayList<ArrayList<event>>>> TeamEvents
                = eventSorter.getTeamSlices();



        JSONArray jsonArray = playerGroupAnalyzer.getJsonInQuadrants(TeamEvents);
        System.out.println(jsonArray);
        return jsonArray.toString();

    }}
