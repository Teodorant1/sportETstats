package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerGroupAnalyzer {
    int score = 0;
    int time = 0;
    int incrementor = 1;
    HashMap<String, Player> playerHashMap = new HashMap<>();


    public Boolean SwitchCheck(ArrayList<event> eventArrayList, int gorillaposition) {
        return ((eventArrayList.get(gorillaposition).getDescription().equals("Uscita"))
                || (eventArrayList.get(gorillaposition).getDescription().equals("Ingresso")));
    }

    public JSONArray getJsonInQuadrants
            (HashMap<Integer, HashMap<String, ArrayList<ArrayList<event>>>> input) {
        // input is all quadrants and all both teams
        ArrayList<String> teamnames = new ArrayList<>();
        input.get(1).forEach((key, value) -> {
            teamnames.add(key);
        });

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

            JSONObject squeezedHashmap = squeezeResultsFromHashMap(quadrantNumba);

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
    public JSONObject squeezeResultsFromHashMap(int quadrantNumba) {

        JSONObject slicedHolder = new JSONObject();
        slicedHolder.put("time", playerHashMap.get("time").getPlaytime());
        // if (quadrantNumba == 1) {
        //     playerHashMap.forEach((key, value) -> {
        //         System.out.println(key);
        //     });
        //     System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");}


        JSONArray slicedJson = new JSONArray();
        playerHashMap.forEach((key, value) -> {

            if (!key.equals("time") && (!key.equals("score"))) {
                slicedJson.put(value.getOverview());
            }
            //  if (playerHashMap.size() < 5) {
            //      System.out.println("Size is smaller than 5");
            //  }

        });
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


        slicedHolder.put("slice", slicedJson);
        //    System.out.println(" squeezeResultsFromHashMap "+slicedJson);
        return slicedHolder;
    }

    public void sliceToHashMap
            (ArrayList<event> slice) {

        Player ignorestatus = new Player(0, 0, "ignore");
        if (slice.get(1).getDescription().equals("Uscita") || slice.get(1).getDescription().equals("Ingresso")) {
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
        if (SwitchCheck(slice, 0) && SwitchCheck(slice, 1)) {
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

            if (letime / 60 == 0) {
                actuallytime.setPlaytime(1);
            }

            playerHashMap.put("time", actuallytime);
        }
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
