package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class yearly_reporter {

    int incrementor = 0;
    webscraper webscraper = new webscraper();
    ArrayList<HashMap<String, Player>> team_year_slices = new ArrayList<>();
    HashMap<String, ArrayList<HashMap<String, Player>>> lineups = new HashMap<>();
    ArrayList<JSONObject> processed_lineups = new ArrayList<>();

    public JSONArray maintest(String id1, String teamname) throws IOException {

        String url1 = "https://www.legabasket.it/lba/squadre/2022/" + id1 + "/nutribullet-treviso-basket/match_schedule";

        //    System.out.println("paloki");
        webscraper.getPage(url1);
        ArrayList<String> teamEndpoints = webscraper.getTeamEndpoints();
        //   analyze_team(teamEndpoints, "Openjobmetis Varese");

        ArrayList<JSONObject> lineupSlices = analyze_team(teamEndpoints, teamname);
        JSONArray finalslices = pagify_lineups(lineupSlices);

        System.out.println(finalslices);

        return finalslices;
    }

    public JSONArray pagify_lineups(ArrayList<JSONObject> lineupSlices) {

        Collections.sort(lineupSlices, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                return o2.getInt("Points") - o1.getInt("Points");
            }
        });

        int pageindex = 1;

        JSONArray final_array = new JSONArray();
        ArrayList<JSONObject> sublist = new ArrayList<>();
        for (int i = 0; i < lineupSlices.size(); i++) {
            sublist.add(lineupSlices.get(i));
            if (sublist.size() == 10) {

                JSONObject page = new JSONObject();
                page.put("pageindex",pageindex);
                page.put("contents",sublist);

                final_array.put(page);
                sublist.clear();
                pageindex++;
                //    System.out.println("TEN SIZE");
            }

            if (i == lineupSlices.size() - 1 && (!(lineupSlices.size() % 10 == 0))) {
                JSONObject page = new JSONObject();
                page.put("pageindex",pageindex);
                page.put("contents",sublist);

                final_array.put(page);
                sublist.clear();
                pageindex++;

                //   System.out.println("lAUNCHING INCOMPLETE");
            }

        }

        // System.out.println(final_array.length());

        return final_array;
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

        //    JSONArray lineup_lifetime_stats = new JSONArray();

        lineups.forEach((key, value) ->
        {
            //   JSONArray players = new JSONArray(key);
            //  System.out.println(key);
            //  System.out.println(getAllPoints(value));
            getAllPoints(value);
        });
//      System.out.println("  LINEUPS SIZE BE LIKE  "+lineups.size());
        //     System.out.println(processed_lineups);
        return processed_lineups;
    }


    public void getAllPoints(ArrayList<HashMap<String, Player>> singularLineUp) {
        //   int points = 0;
        HashMap<String, Player> firstLineUp = singularLineUp.get(0);
        if (!firstLineUp.containsKey("ignorestatus")) {
            if (singularLineUp.size() > 1) {
                for (int i = 1; i < singularLineUp.size(); i++) {
                    singularLineUp.get(i).forEach((key, value) -> {
                        {
                            firstLineUp.get(key).setPlaytime(firstLineUp.get(key).getPlaytime() + value.getPlaytime());
                            firstLineUp.get(key).setPersonalscore(firstLineUp.get(key).getPlaytime() + value.getPersonalscore());
                        }
                    });
                }
            }
            ArrayList<String> playernames = new ArrayList<>();
            //   if (!singularLineUp.get(0).containsKey("ignorestatus"))

            singularLineUp.get(0).forEach((key, value) -> {
                playernames.add(key);
            });
            Collections.sort(playernames);
            int time = firstLineUp.get("time").getPlaytime();
            int points = get_sum_from_PlayerHashMap(firstLineUp);
            int enemypoints = firstLineUp.get("enemyscore").getPersonalscore();

            JSONObject lineup_overview = new JSONObject();

            lineup_overview.put("Points", points);
            lineup_overview.put("EnemyPoints", enemypoints);
            lineup_overview.put("time", time);
            firstLineUp.remove("time");
            firstLineUp.remove("enemyscore");

            incrementor++;
            lineup_overview.put("id", incrementor);

            ArrayList<Player> PlayerObjects = new ArrayList<>(firstLineUp.values());
            JSONArray players = new JSONArray();
            for (int i = 0; i < PlayerObjects.size(); i++) {
                JSONObject player = new JSONObject();
                player.put("points", PlayerObjects.get(i).getPersonalscore());
                player.put("name", PlayerObjects.get(i).getFullname());
                players.put(player);
            }

            //  System.out.println("Players be like "+players);

            lineup_overview.put("Lineup", players);
            processed_lineups.add(lineup_overview);
        }
        //   System.out.println(" First member of singular lineup be like :  "+singularLineUp.get(0));
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
        //     playerHashMap.remove("time");
        //     playerHashMap.remove("enemyscore");


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
