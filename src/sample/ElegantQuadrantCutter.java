package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ElegantQuadrantCutter {
    public ElegantQuadrantCutter() {
    }

    VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();

    JSONObject getQuadrant(int quadrant, int team, String gameID) throws IOException {

        String entirematch = verticalAPIscanner.maintest(gameID);
        JSONArray entirematchJSON = new JSONArray(entirematch);
        JSONObject SingleTeamQuadrants = entirematchJSON.getJSONObject(team - 1);
        String teamname = SingleTeamQuadrants.getString("teamname");
      //System.out.println(teamname);
        String TargetQuadrant = "quadrant" + quadrant;
        JSONObject Quadrant = SingleTeamQuadrants.getJSONObject(TargetQuadrant);
        JSONArray timeline = Quadrant.getJSONArray("timeline");
        JSONObject newquad = new JSONObject();

        newquad.put("quadrantnumber" , quadrant);
        newquad.put("timeline", timeline);
        newquad.put("teamname", teamname);

        return newquad;

    }


}
