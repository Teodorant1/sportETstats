package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class yearly_reporter {

    webscraper webscraper = new webscraper();

    ArrayList<HashMap<String, Player>> team_year_slices = new ArrayList<>();
    HashMap<String, ArrayList<HashMap<String, Player>>> lineups = new HashMap<>();
    ArrayList<JSONObject> processed_lineups = new ArrayList<>();
    public void maintest() throws IOException {
        webscraper.getPage();
        ArrayList<String> teamEndpoints = webscraper.getTeamEndpoints();

        //  for ( int i = 0 ; i < teamEndpoints.size(); i++ )
        //  {
        //      if (teamEndpoints.get(i).equals("23726")) {
        //          teamEndpoints.remove(i);
        //      }
        //  }
        analyze_team(teamEndpoints, "Openjobmetis Varese");
    }

    public ArrayList<JSONObject> analyze_team(ArrayList<String> teamEndpoints, String teamname) throws IOException {
        for (int i = 0; i < teamEndpoints.size(); i++) {
            //  System.out.println(teamEndpoints.get(i));
            VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
            String gameID = teamEndpoints.get(i);
            verticalAPIscanner.maintest(gameID);
            HashMap<String, ArrayList<HashMap<String, Player>>> sliced_hashmaps = new HashMap<>();
            sliced_hashmaps.putAll(verticalAPIscanner.getPlayerGroupAnalyzer().getEternal_sliced_hashmaps());

            //  System.out.println("sliced hashmaps be like : " + sliced_hashmaps);

            //  sliced_hashmaps.forEach((key , value )->{System.out.println(key);});

            ArrayList<HashMap<String, Player>> teamEvents = sliced_hashmaps.get(teamname);
            //     System.out.println("TeamEvents Size be like:" + teamEvents.size());
            //    System.out.println("team events be like : " + teamEvents);
            team_year_slices.addAll(teamEvents);
        }
        //   System.out.println("Team year slices be like : " + team_year_slices);
        get_filtered_slices(team_year_slices);
// System.out.println("herp derp");
//  System.out.println("LINEUPS : " + lineups.size());

        JSONArray lineup_lifetime_stats = new JSONArray();

        lineups.forEach((key, value) ->
        {
         //   JSONArray players = new JSONArray(key);
         //  System.out.println(key);
         //  System.out.println(getAllPoints(value));
            getAllPoints(value);
        });
//      System.out.println("  LINEUPS SIZE BE LIKE  "+lineups.size());
        System.out.println(processed_lineups);
        return processed_lineups;
    }

    public int getAllPoints(ArrayList<HashMap<String, Player>> singularLineUp) {
        int points = 0;
        for (int i = 0; i < singularLineUp.size(); i++) {
            points = points + get_sum_from_PlayerHashMap(singularLineUp.get(i));
        }

        ArrayList<String> playernames = new ArrayList<>();

        if (!singularLineUp.get(0).containsKey("ignorestatus")) {
            singularLineUp.get(0).forEach((key, value) -> {
                playernames.add(key);
            });
            Collections.sort(playernames);
            //System.out.println("AASLDKJFHALSJFHASLJHALKSDJFHLKAJHSDFKHL");
            JSONObject lineup_overview = new JSONObject();
            lineup_overview.put("Points", points);
            lineup_overview.put("Lineup", playernames);
            processed_lineups.add(lineup_overview);
        }


        //   System.out.println(" First member of singular lineup be like :  "+singularLineUp.get(0));

        return points;
    }


    public int get_sum_from_PlayerHashMap(HashMap<String, Player> playerHashMap) {
        HashMap<String, Player> playerHashMap2 = new HashMap<>();
        playerHashMap2.putAll(playerHashMap);
        ArrayList<Integer> playerscores = new ArrayList<>();
        playerHashMap2.forEach((key, value) -> {
            if (!key.equals("time") && (!key.equals("enemyscore"))) {
                playerscores.add(value.getPersonalscore());
            }

        });

        int teampoints = 0;
        for (int n = 0; n < playerscores.size(); n++) {
            teampoints = teampoints + playerscores.get(n);
        }
        if (playerHashMap.containsKey("ignorestatus")) {
            //    System.out.println("DEEEEEEEEEEEEEEEEEEEEEEEEEEEEEERP");
            return 0;
        }
        return teampoints;
    }

    public String get_key_from_hashmapKEYS(HashMap<String, Player> playerHashMap) {
        ArrayList<String> keys = new ArrayList<>();
        playerHashMap.remove("time");
        playerHashMap.remove("enemyscore");


        playerHashMap.forEach((key, value) -> {
            keys.add(key);
        });
        Collections.sort(keys);
        return keys.toString();
    }

    public void get_filtered_slices(ArrayList<HashMap<String, Player>> team_year_slice_combos) {
//        System.out.println("herp derp");
        //  System.out.println("Size " + team_year_slice_combos.size());
        //  System.out.println(team_year_slice_combos);
        for (int i = 0; i < team_year_slice_combos.size(); i++) {
            String key = get_key_from_hashmapKEYS(team_year_slice_combos.get(i));


            if (!lineups.containsKey(key)) {
                //      System.out.println("Adding new lineup arraylist");
                ArrayList<HashMap<String, Player>> single_lineup = new ArrayList<>();
                lineups.put(key, single_lineup);
            }
            lineups.get(key).add(team_year_slice_combos.get(i));
            //    System.out.println("Added new lineup");
        }
        //   System.out.println("LINEUPS V2: " + lineups.size());

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
