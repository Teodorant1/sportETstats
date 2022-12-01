package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PlayerGroupAnalyzer {
    int score = 0;
    int time = 0;
    int incrementor = 1;
    HashMap<String, Player> playerHashMap = new HashMap<>();

    public HashMap<String, ArrayList<HashMap<String, Player>>> getEternal_sliced_hashmaps() {
        System.out.println("We are fetching this : "+eternal_sliced_hashmaps);
        return eternal_sliced_hashmaps;
    }

    public void setEternal_sliced_hashmaps(HashMap<String, ArrayList<HashMap<String, Player>>> eternal_sliced_hashmaps) {
        this.eternal_sliced_hashmaps = eternal_sliced_hashmaps;
    }

    HashMap<String, ArrayList<HashMap<String, Player>>> eternal_sliced_hashmaps = new HashMap<>();
    HashMap<Integer, HashMap<String, ArrayList<event>>> QuadrantEvents;


    public Boolean SwitchCheck(ArrayList<event> eventArrayList, int gorillaposition) {
        return ((eventArrayList.get(gorillaposition).getDescription().equals("Uscita"))
                || (eventArrayList.get(gorillaposition).getDescription().equals("Ingresso")));
    }


    public JSONArray getJsonInQuadrants
            (HashMap<Integer, HashMap<String, ArrayList<ArrayList<event>>>> input
                    , HashMap<Integer, HashMap<String, ArrayList<event>>> unfilteredEvents) {
        // input is all quadrants and all both teams
        setQuadrantEvents(unfilteredEvents);
        ArrayList<String> teamnames = new ArrayList<>();
        input.get(1).forEach((key, value) -> {
            teamnames.add(key);
        });

        eternal_sliced_hashmaps.put(teamnames.get(1), new ArrayList<>());
        eternal_sliced_hashmaps.put(teamnames.get(0), new ArrayList<>());


        HashMap<String, ArrayList<ArrayList<event>>> quadrant1 = input.get(1);
        HashMap<String, ArrayList<ArrayList<event>>> quadrant2 = input.get(2);
        HashMap<String, ArrayList<ArrayList<event>>> quadrant3 = input.get(3);
        HashMap<String, ArrayList<ArrayList<event>>> quadrant4 = input.get(4);
        ArrayList<HashMap<String, ArrayList<ArrayList<event>>>> quadrants = new ArrayList<>();
        quadrants.add(quadrant1);
        quadrants.add(quadrant2);
        quadrants.add(quadrant3);
        quadrants.add(quadrant4);

        // the phantom seven bug happens before this because it isn't present in the array
        //   int size = quadrant1.get("GeVi Napoli Basket").size();
        //   System.out.println(quadrant1.get("GeVi Napoli Basket").get(size-1).get(1).getPlayer());
        //   System.out.println(quadrant1.get("GeVi Napoli Basket").get(size-1).get(1).getTime());
        //   System.out.println(quadrant1.get("GeVi Napoli Basket").get(size-1).get(1).getDescription());


        JSONArray finalproduct = new JSONArray();
        teamnames.forEach((value) -> {
            //for each quadrant
            JSONObject team = new JSONObject();
            team.put("teamname", value);

            for (int i = 0; i < quadrants.size(); i++) {
                ArrayList<ArrayList<event>> quadrantslices = quadrants.get(i).get(value);
                JSONArray teamtimeline = bigsqueeze(quadrantslices, i + 1);
                incrementor = 1;
                JSONObject teamquadrant = new JSONObject();
                teamquadrant.put("timeline", teamtimeline);
                int quadrantnumber = i + 1;
                team.put("quadrant" + quadrantnumber, teamquadrant);
            }

            finalproduct.put(team);
            playerHashMap.clear();
        });
        return finalproduct;
    }


    // returns the JSON array for a single team
    public JSONArray bigsqueeze(ArrayList<ArrayList<event>> everyevent, int quadrantNumba) {
        JSONArray endproduct = new JSONArray();
        // for every slice

        for (int i = 0; i < everyevent.size(); i++) {

            //    if (quadrantNumba == 1 && i == 0) {
            sliceToHashMap(everyevent.get(i));
            //    }
            JSONObject squeezedHashmap = squeezeResultsFromHashMap();
            //    if (i > 0) {
            //        sliceToHashMap(everyevent.get(i));
            //    }


            // the phantom player bug happens before here


            if (!playerHashMap.containsKey("ignorestatus")) {
                //    System.out.println("Ignoring status");

                JSONObject squeezedHolder = new JSONObject();
                squeezedHolder.put("id", incrementor);
                squeezedHolder.put("subtimeline", squeezedHashmap);
                endproduct.put(squeezedHolder);
                incrementor++;
            } else {

                //   System.out.println(playerHashMap.size())
                playerHashMap.remove("ignorestatus");
                //   System.out.println(playerHashMap.size());
                //   System.out.println("Removed ignorestatus");
            }
        }

        // if (quadrantNumba==1){System.out.println(endproduct);}

        return endproduct;
    }

    // returns a JSON array of a single slice
    public JSONObject squeezeResultsFromHashMap() {

        JSONObject slicedHolder = new JSONObject();
        slicedHolder.put("time", playerHashMap.get("time").getPlaytime());

        JSONArray slicedJson = new JSONArray();
        playerHashMap.forEach((key, value) -> {
            if (!key.equals("time") && (!key.equals("enemyscore")))
            {slicedJson.put(value.getOverview());}});

        HashMap<String, Player> playerHashMap2 = new HashMap<>();
        playerHashMap2.putAll(playerHashMap);
        ArrayList<Integer> playerscores = new ArrayList<>();
        playerHashMap2.forEach((key, value) -> {
            playerscores.add(value.getPersonalscore());
        });

        int teampoints = 0;
        for (int n = 0; n < playerscores.size(); n++) {
            teampoints = teampoints + playerscores.get(n);
        }
        slicedHolder.put("score", teampoints);

        slicedHolder.put("enemyscore",playerHashMap.get("enemyscore").getPersonalscore());

        slicedHolder.put("slice", slicedJson);
        //    System.out.println(" squeezeResultsFromHashMap "+slicedJson);
        return slicedHolder;
    }

    public Boolean issbagliato(
            String[] sbagliatoHideout
    ) {
        for (int i = 0; i < sbagliatoHideout.length; i++) {
            if (sbagliatoHideout[i].equals("sbagliato")) {
                return true;
            }

        }
        return false;
    }

    public int get_opposing_team_score_during_interval
            (int start, int end, String ourteam, int Quadrant) {
        HashMap<String, ArrayList<event>> quadrant = QuadrantEvents.get(Quadrant);
        HashMap<String, ArrayList<event>> copyquadrant = new HashMap<>();
        copyquadrant.putAll(quadrant);
        copyquadrant.remove(ourteam);
        ArrayList<String> enemyname = new ArrayList<>();
        copyquadrant.forEach((key, value) -> {

            enemyname.add(key);

        });
        ArrayList<event> enemyevents = copyquadrant.get(enemyname.get(0));

    //    System.out.println(enemyname.get(0));
    //    System.out.println(ourteam);

        int enemypoints = 0;
        for (int i = 0; i < enemyevents.size(); i++) {

            if (enemyevents.get(i).getIndex() > start && enemyevents.get(i).getIndex() < end) {

                if (enemyevents.get(i).description.equals("Tiro libero segnato")) {
                    enemypoints = enemypoints + 1;
                }


                String[] scorecheck = enemyevents.get(i).description.split(" ");
                if (scorecheck.length > 1) {
                    if (scorecheck[1].equals("punti")) {
                        if (!issbagliato(scorecheck)) {
                            enemypoints = enemypoints + Integer.parseInt(scorecheck[0]);

                        }
                    }
                }
            }

        }

        return enemypoints;
    }

    public void sliceToHashMap(ArrayList<event> slice) {

        Player ignorestatus = new Player(0, 0, "ignore");
     // System.out.println(slice.get(0).getDescription());
     // System.out.println(slice.get(0).getPlayer());
     // System.out.println(slice.get(0).getTeamname());
        if (slice.get(0).getDescription().equals("Uscita") || slice.get(0).getDescription().equals("Ingresso")) {
            playerHashMap.put("ignorestatus", ignorestatus);
            //        System.out.println("block inserted");
        }

        //  if (slice.get(0).getPlayer().equals("Elijah Stewart") &&
        //      slice.get(0).getDescription().equals("3 punti sbagliato")
        //   && slice.get(0).getTime().equals("00:00")
//
        //  ) {
        //      System.out.println("Cha Ching");
        //  }

        twoitemholder twoitemholder = new twoitemholder();
        twoitemholder.setStart(slice.get(0).getTime());
        twoitemholder.setEnd(slice.get(slice.size() - 1).getTime());
        int letime = getDeltaTime(twoitemholder.getStart(), twoitemholder.getEnd());
        if (SwitchCheck(slice, 0) && SwitchCheck(slice, slice.size()-1)) {


            for (int i = 0; i < slice.size(); i++) {
                //  System.out.println("Gorilla position");
                if (slice.get(i).getDescription().equals("Uscita")) {
                    playerHashMap.remove(slice.get(i).getPlayer());
                }

                if (slice.get(i).getDescription().equals("Ingresso")) {
                    Player player = new Player(0, 0,
                            slice.get(i).getPlayer());
                    playerHashMap.put(player.getFullname(), player);
                }
            }
        }


        if (letime > 0) {
            int enemyscore = get_opposing_team_score_during_interval(slice.get(1).getIndex(), slice.get(slice.size() - 2).getIndex(),
                    slice.get(1).getTeamname(), slice.get(1).getQuadrant());
            Player score = new Player(enemyscore, 1, "Enemy Score");
            playerHashMap.put("enemyscore", score);

            for (int i = 0; i < slice.size(); i++) {
                if (!slice.get(i).getPlayer().equals("placeholder") &&
                        !slice.get(i).getPlayer().equals("placeholder placeholder") && !SwitchCheck(slice, i)) {
                    Player player = new Player(0, letime / 60, slice.get(i).getPlayer());
                    if (letime / 60 == 0) {
                        player.setPlaytime(1);
                    }
                    playerHashMap.put(slice.get(i).getPlayer(), player);
                }
            }
            for (int i = 0; i < slice.size(); i++) {
                this.setScore(getScore() + slice.get(i).getPoints());
                if (slice.get(i).description.equals("Tiro libero segnato")) {
                    this.setScore(getScore() + 1);
                    playerHashMap.get(slice.get(i).getPlayer()).setPersonalscore(
                            playerHashMap.get(slice.get(i).getPlayer()).getPersonalscore() + 1
                    );
                }

                if (slice.get(i).getPoints() > 0) {
                    playerHashMap.get(slice.get(i).getPlayer()).setPersonalscore(
                            playerHashMap.get(slice.get(i).getPlayer()).getPersonalscore()
                                    + slice.get(i).getPoints());
                    //    System.out.println(slice.get(i).getPlayer() + "  " + slice.get(i).getDescription());
                }
            }
            // I forgot how to make an object to hold the time so this is a workaround
            Player actuallytime = new Player(0, letime / 60, "time");

            if (letime / 60 == 0) {actuallytime.setPlaytime(1);}
        playerHashMap.put("time", actuallytime);}

        String teamname = slice.get(0).getTeamname();
        eternal_sliced_hashmaps.get(teamname).add(playerHashMap);
    //    System.out.println("PlayerHashMap be like : "+playerHashMap);
    }


    public int getTimeFromString(String TimeString) {
        String countdown = TimeString;
        String[] prototime = countdown.split(":");
        if (prototime[0].equals("10")) {
            return 600;
        } else {
            int minutes = Integer.parseInt(prototime[0]);
            int seconds = Integer.parseInt(prototime[1]);
            int totaltime = (minutes * 60) + seconds;
            return totaltime;
        }
    }

    public int getDeltaTime(String START, String END) {
        int starttime = getTimeFromString(START);
        int endtime = getTimeFromString(END);
        int delta0 = starttime - endtime;
        int delta1 = delta0;
        return delta1;
    }

    public int getIncrementor() {
        return incrementor;
    }

    public void setIncrementor(int incrementor) {
        this.incrementor = incrementor;
    }

    public HashMap<String, Player> getPlayerHashMap() {
        return playerHashMap;
    }

    public void setPlayerHashMap(HashMap<String, Player> playerHashMap) {
        this.playerHashMap = playerHashMap;
    }

    public HashMap<Integer, HashMap<String, ArrayList<event>>> getQuadrantEvents() {
        return QuadrantEvents;
    }

    public void setQuadrantEvents(HashMap<Integer, HashMap<String, ArrayList<event>>> quadrantEvents) {
        QuadrantEvents = quadrantEvents;
    }

    public PlayerGroupAnalyzer() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
