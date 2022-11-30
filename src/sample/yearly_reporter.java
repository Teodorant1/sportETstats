package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class yearly_reporter {
    webscraper webscraper = new webscraper();
    ArrayList<HashMap<String, Player>> team_year_slices = new ArrayList<>();
    HashMap<String, ArrayList<HashMap<String, Player>>> lineups = new HashMap<>();

    public void analyze_team(ArrayList<String> teamEndpoints, String teamname) throws IOException {
        for (int i = 0; i < teamEndpoints.size(); i++) {
            VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
            String gameID = teamEndpoints.get(i);
            verticalAPIscanner.maintest(gameID);
            HashMap<String, ArrayList<HashMap<String, Player>>> sliced_hashmaps
                    = verticalAPIscanner.getPlayerGroupAnalyzer().eternal_sliced_hashmaps;
            ArrayList<HashMap<String, Player>> teamEvents = sliced_hashmaps.get(teamname);
            team_year_slices.addAll(teamEvents);
        }
        get_filtered_slices(team_year_slices);
    }

    public void get_sum_totals () {}

    public String get_key_from_hashmapKEYS(HashMap<String, Player> playerHashMap) {
        ArrayList<String> keys = new ArrayList<>();
        playerHashMap.forEach((key, value) -> {
            keys.add(key);
        });
        Collections.sort(keys);
        return keys.toString();
    }

    public void get_filtered_slices(ArrayList<HashMap<String, Player>> team_year_slice_combos) {
        for (int i = 0; i < team_year_slice_combos.size(); i++) {
            String key = get_key_from_hashmapKEYS(team_year_slice_combos.get(i));
            if (!lineups.containsKey(key)) {
                ArrayList<HashMap<String, Player>> single_lineup = new ArrayList<>();
                lineups.put(key, single_lineup);
            }
            lineups.get(key).add(team_year_slice_combos.get(i));
        }

    }
    //  public boolean isSameTeam(HashMap<String, Player> team1, HashMap<String, Player> team2)
    //  { ArrayList <String> stringArrayList = new ArrayList<>();
    //      team1.forEach((key , value )->{
    //          if (team2.containsKey(key)) {
    //            stringArrayList.add("derp");
    //          }
    //      });
    //      if (stringArrayList.size() == 5){
    //          return true;
    //      }
    //      return false;
    //  }
}
