package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerGroupAnalyzer {
    int score = 0;
    int time = 0;
    int incrementor = 0;
    HashMap<String, Player> playerHashMap = new HashMap<>();
    //  HashMap<String, Player> eternalplayerHashMap = new HashMap<>();
    ArrayList<ArrayList<ArrayList<event>>> TeamEvents = new ArrayList<>();


    public Boolean SwitchCheck(ArrayList<event> eventArrayList, int gorillaposition) {
        return ((eventArrayList.get(gorillaposition).getDescription().equals("Uscita"))
                || (eventArrayList.get(gorillaposition).getDescription().equals("Ingresso")));
    }

    public JSONArray doublesqueeze(ArrayList<ArrayList<ArrayList<event>>> TeamEvents) {
        JSONArray endresult = new JSONArray();
        for (int i = 0; i < TeamEvents.size(); i++) {
            JSONObject finalobject = new JSONObject();
            JSONArray timeline = bigsqueeze(TeamEvents.get(i));
            finalobject.put("teamname", TeamEvents.get(i).get(0).get(1).getTeamname());
            finalobject.put("timeline", timeline);
            endresult.put(finalobject);
            playerHashMap.clear();
        }
        //    System.out.println(" doublesqueeze "+endresult );
        return endresult;
    }


    // returns the JSON array for a single team
    public JSONArray bigsqueeze(ArrayList<ArrayList<event>> everyevent) {
        JSONArray endproduct = new JSONArray();
        for (int i = 0; i < everyevent.size(); i++) {
            HashMap<String, Player> hashMap = sliceToHashMap(everyevent.get(i), playerHashMap);

            // {
            //            id: 4,
            //            subtimeline:
            //
            //           this array is the squeezed hashmap
            //            [
            //              { name: "Jaron Johnson", points: 20 },
            //              { name: "Markel Brown", points: 0 },
            //              { name: "Giovanni De Nicolao", points: 0 },
            //              { name: "Colbey Ross", points: 10 },
            //              { name: "Guglielmo Caruso", points: 3 },
            //            ]
            //
            //            ,
            //          }

            JSONObject squeezedHashmap = squeezeResultsFromHashMap(hashMap);

            JSONObject squeezedHolder = new JSONObject();
            squeezedHolder.put("id", incrementor);
            squeezedHolder.put("subtimeline", squeezedHashmap);
            endproduct.put(squeezedHolder);
            incrementor++;
        }

        //    System.out.println(" bigsqueeze "+endproduct );

        return endproduct;
    }

    // returns a JSON array of a single slice
    public JSONObject squeezeResultsFromHashMap(HashMap<String, Player> playerHashMap1) {

        JSONObject slicedHolder = new JSONObject();
        slicedHolder.put("time", playerHashMap1.get("time").getPlaytime());
//      slicedHolder.put("score", playerHashMap1.get("score").getPersonalscore());

        JSONArray slicedJson = new JSONArray();
        playerHashMap1.forEach((key, value) -> {

            if (!key.equals("time"  ) && (!key.equals("score"))) {
                slicedJson.put(value.getOverview());
            }
            if (playerHashMap1.size() < 5) {
                System.out.println("Size is smaller than 5");
            }

        });
        HashMap<String, Player> playerHashMap2 = new HashMap<>();
        playerHashMap2.putAll(playerHashMap1);
        ArrayList<Integer> playerscores = new ArrayList<>();
        playerHashMap2.forEach((key, value) -> {
            playerscores.add(value.getPersonalscore());
        });
        int teampoints = 0;
        for (int n = 0; n < playerscores.size(); n++)
        {teampoints = teampoints + playerscores.get(n);}
        slicedHolder.put("score" , teampoints );




        slicedHolder.put("slice", slicedJson);
        //    System.out.println(" squeezeResultsFromHashMap "+slicedJson);
        return slicedHolder;
    }

    public HashMap<String, Player> sliceToHashMap
            (ArrayList<event> slice, HashMap<String, Player> playerHashMap1) {
        twoitemholder twoitemholder = new twoitemholder();
        twoitemholder.setStart(slice.get(0).getTime());
        twoitemholder.setEnd(slice.get(slice.size() - 1).getTime());
        int letime = getDeltaTime(twoitemholder.getStart(), twoitemholder.getEnd());
        if (letime == 0 && SwitchCheck(slice, 0)) {
            for (int i = 0; i < slice.size(); i++) {
                if (slice.get(i).getDescription().equals("Ingresso")) {
                    Player player = new Player(0, 0,

                            slice.get(i).getPlayer());
                    playerHashMap1.put(player.getFullname(), player);
                }
                if (slice.get(i).getDescription().equals("Uscita")) {
                    playerHashMap1.remove(slice.get(i).getPlayer());
                }
            }
        }

        if (letime > 0) {
            //   System.out.println(slice.get(1).getTeamname());
            //
            //
            //   System.out.println(" This took " + letime + " Minutes ");
            //
            for (int i = 0; i < slice.size(); i++) {
                if (!slice.get(i).getPlayer().equals("placeholder") && !slice.get(i).getPlayer().equals("placeholder placeholder")) {
                    playerHashMap1.put(slice.get(i).getPlayer(), new Player(0, letime,
                            slice.get(i).getPlayer()));
                }
                //    System.out.println(" hash size "+playerHashMap.size());
            }
            for (int i = 0; i < slice.size(); i++) {
                //   if (!slice.get(i).getPlayer().equals("placeholder")) {
                //       playerHashMap.put(slice.get(i).getPlayer(), new Player(0, 0,
                //               slice.get(i).getPlayer()));
                //   }

                this.setScore(getScore() + slice.get(i).getPoints());
                if (slice.get(i).description.equals("Tiro libero segnato")) {
                    this.setScore(getScore() + 1);
                    playerHashMap1.get(slice.get(i).getPlayer()).setPersonalscore(
                            playerHashMap1.get(slice.get(i).getPlayer()).getPersonalscore() + 1
                    );
                }

                if (slice.get(i).getPoints() > 0) {
                    playerHashMap1.get(slice.get(i).getPlayer()).setPersonalscore(
                            playerHashMap1.get(slice.get(i).getPlayer()).getPersonalscore()
                                    + slice.get(i).getPoints());
                    //    System.out.println(slice.get(i).getPlayer() + "  " + slice.get(i).getDescription());
                }
            }
            // I forgot how to make an object to hold the time so this is a workaround
            Player actuallytime = new Player(0, letime, "time");
            playerHashMap1.put("time", actuallytime);
        }
    //    ArrayList<Integer> playerscores = new ArrayList<>();
    //    playerHashMap1.forEach((key, value) -> {
    //        playerscores.add(value.getPersonalscore());
    //    });
    //    int teampoints = 0;
//
    //    for (int n = 0; n < playerscores.size(); n++)
    //    {teampoints = teampoints + playerscores.get(n);}
//
    //    Player teamscore = new Player(teampoints, letime , "score");
//
    //    if (playerHashMap1.containsKey("score")) {playerHashMap1.remove("score");}
//
    //    playerHashMap1.put("score" , teamscore );

//      System.out.println(" sliceToHashMap " +playerHashMap1);
        return playerHashMap1;


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
        int delta1 = delta0 / 60;
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


    //   public HashMap<String, Player> getPlayerHashMap() {
    //       return playerHashMap;
    //   }
    //
    //   public void setPlayerHashMap(HashMap<String, Player> playerHashMap) {
    //       this.playerHashMap = playerHashMap;
    //   }
}
