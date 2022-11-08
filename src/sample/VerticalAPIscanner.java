package sample;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public class VerticalAPIscanner {

    public String maintest(String gameID) throws IOException {
    //23736
        httpfetch httpfetch = new httpfetch();
        Match match = httpfetch.bigFetch(gameID);
        PlayerGroupAnalyzer playerGroupAnalyzer = new PlayerGroupAnalyzer();
        eventSorter eventSorter = new eventSorter(match);
        ArrayList<ArrayList<ArrayList<event>>> TeamEvents = eventSorter.getTeamSlices();
        JSONArray jsonArray = playerGroupAnalyzer.doublesqueeze(TeamEvents);
    //  System.out.println(jsonArray.toString());

        return jsonArray.toString();

    }}
