package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class eventSorter {
    Match match1;
    HashMap<Integer, HashMap<String, ArrayList<event>>> QuadrantEvents = new HashMap<>();

    public eventSorter(Match match1) {
        this.match1 = match1;
    }

    public eventSorter() {
    }

    public void processQuadrant(String matchQuadrant, int quadrantID) {
        ArrayList<event> events1 = new ArrayList<>();
        JSONObject jsonObject1 = new JSONObject(matchQuadrant);
        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
        JSONArray jsonArray = jsonObject2.getJSONArray("pbp");
        ArrayList<event> events = jsontoarraylist(jsonArray);

        events1.addAll(events);

        //  if (quadrantID == 1) {
        //      System.out.println(events1.get(events.size() - 2).getPlayer());
        //      System.out.println(events1.get(events.size() - 2).getDescription());
        //      System.out.println(events1.get(events.size()-2).getTime());
        //  }

        HashMap<String, ArrayList<event>> splitEvents = Splitevents(events1, quadrantID);
        QuadrantEvents.put(quadrantID, splitEvents);
    }

    public void processAllquadrants() {
        processQuadrant(this.match1.q1, 1);
        processQuadrant(this.match1.q2, 2);
        processQuadrant(this.match1.q3, 3);
        processQuadrant(this.match1.q4, 4);
    }
    // slices for both teams

    public HashMap<Integer, HashMap<String, ArrayList<ArrayList<event>>>>
    getTeamSlices() {
        processAllquadrants();
        // System.out.println(QuadrantEvents);
        HashMap<Integer, HashMap<String, ArrayList<ArrayList<event>>>>
                QuadrantTeamslices = new HashMap<>();

        // value holds events for both teams
        // key is the number for the quadrant
        QuadrantEvents.forEach((key, value) ->


        {
            HashMap<String, ArrayList<ArrayList<event>>> TeamSlices = new HashMap<>();
// value is the team events for this quadrant
// key is team name
            value.forEach((key1, value1) -> {
                // this gets us the team slices for a single team
                ArrayList<ArrayList<event>> teamSlices1 = createSlices(value1);
                TeamSlices.put(key1, teamSlices1);
            });
            // teamslices now holds the slices for both teams for that quarter/quadrant

            QuadrantTeamslices.put(key, TeamSlices);
            // this now holds the slices for both teams for all quadrants after this double for each

        });

        // we need to get the slices for each quadrant with both teams
        return QuadrantTeamslices;
    }

    public ArrayList<event> jsontoarraylist(JSONArray jsonArray) {
        ArrayList<event> events0 = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            {
                event event = new event
                        (jsonObject.optString("player_name", "placeholder") + " " +
                                jsonObject.optString("player_surname", "placeholder"),
                                jsonObject.optString("description", "placeholder"),
                                jsonObject.optString("print_time", "placeholder"),
                                jsonObject.optString("team_name", "placeholder"));


                //    if (event.getPlayer().equals("Jordan Howard") && event.getTime().equals("00:07") && event.getDescription().equals("Ingresso")) {
                //        System.out.println(events0.get(events0.size() - 1).getPlayer());
                //        System.out.println(events0.get(events0.size() - 1).getTime());
                //        System.out.println(events0.get(events0.size() - 1).getDescription());
                //    }


                String[] scorecheck = event.description.split(" ");
                if (scorecheck.length > 1) {
                    if (scorecheck[1].equals("punti")) {
                        if (!issbagliato(scorecheck)) {
                            event.setPoints(Integer.parseInt(scorecheck[0]));
                        }
                    }
                }

                //    if (event.getPlayer().equals("Elijah Stewart") && event.getTime().equals("00:00")) {
                //        System.out.println("Cha Ching");
                //        System.out.println(event.getDescription());
                //        System.out.println(events0.size());
                //    }

                if (!event.getDescription().equals("Inizio Tempo")) {
                    events0.add(event);
                    // if (event.getPlayer().equals("Elijah Stewart") && event.getTime().equals("00:00")) {
                    //     System.out.println("Cha Ching");
                    //     System.out.println(event.getDescription());
                    //     System.out.println(events0.size());
                    // }
                }
            }
        }

        return events0;
    }

    // Sbagliato means wrong in italian
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

    public HashMap<String, ArrayList<event>> Splitevents(ArrayList<event> events1, int quadrantID) {

        HashMap<String, ArrayList<event>> teamevents = new HashMap<>();
        ArrayList<event> team1events = new ArrayList<>();
        ArrayList<event> team2events = new ArrayList<>();

// the phantom seven happens after this because it is present in events1 arraylist here
        //      if (quadrantID == 1) {
        //          System.out.println(events1.get(events1.size() - 2).getPlayer());
        //          System.out.println(events1.get(events1.size() - 2).getDescription());
        //          System.out.println(events1.get(events1.size() - 2).getTime());
        //          System.out.println(events1.get(events1.size() - 2).getTeamname());
        //      }


        //5 1 2 3 4 5
        //  0 1 2 3 4

        for (int i = 0; i < events1.size(); i++) {
            if (!events1.get(i).teamname.equals("placeholder")) {


                if (team1events.size() == 0 && team2events.size() == 0) {
                    team1events.add(events1.get(i));
                    //    teamevents.put(events1.get(i).getTeamname(), team1events);
                }
                if (team1events.size() > 0 && i > 0) {
                    if (events1.get(i).teamname.equals(team1events.get(0).getTeamname())) {
                        team1events.add(events1.get(i));
                    } else {
                        {
                            team2events.add(events1.get(i));
                        }
                    }
                }
            }
        }

        String teamname1 = team1events.get(0).getTeamname();
        String teamname2 = team2events.get(0).getTeamname();


        //   if (quadrantID == 1) {
        //       System.out.println(team2events.get(team2events.size() - 1).getPlayer());
        //       System.out.println(team2events.get(team2events.size() - 1).getTime());
        //       System.out.println(team2events.get(team2events.size()-1).getDescription());
        //   }


        teamevents.put(teamname1, team1events);
        teamevents.put(teamname2, team2events);

    //   if (quadrantID == 1) {
    //       System.out.println(teamevents.get(teamname2).get(teamevents.get(teamname2).size() - 1).getPlayer());
    //       System.out.println(teamevents.get(teamname2).get(teamevents.get(teamname2).size() - 1).getDescription());
    //       System.out.println(teamevents.get(teamname2).get(teamevents.get(teamname2).size() - 1).getTime());
    //   }

        return teamevents;
    }

    public Boolean SwitchCheck(ArrayList<event> eventArrayList, int gorillaposition) {
        return ((eventArrayList.get(gorillaposition).getDescription().equals("Uscita")) ||
                (eventArrayList.get(gorillaposition).getDescription().equals("Ingresso")));
    }

//   public Boolean endofSwitchCheck(ArrayList<event> eventArrayList, int gorillaposition) {
//
//
//       //8+1=9                           10
//       if (gorillaposition + 1 < eventArrayList.size())
//       {return (!eventArrayList.get(gorillaposition + 1).getDescription().equals("Uscita") && !eventArrayList.get(gorillaposition + 1).getDescription().equals("Ingresso"));}
//       return false;}

    // q2 Switch action action switch action action switch EMPTYAction


//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//   1 2 3 4 5 6 7 8 9 10
// index 0 1 2 3 4 5 6 7 8 9
// size  1 2 3 4 5 6 7 8 9 10

    // the phantom 7 bug happens before here, the event is'nt present in the array list

    public ArrayList<ArrayList<event>> createSlices(ArrayList<event> eventArrayList) {
        ArrayList<ArrayList<event>> teamSlices = new ArrayList<>();
        ArrayList<event> slices = new ArrayList<>();
        for (int i = 0; i < eventArrayList.size(); i++) {
            // checks for switch
            if (SwitchCheck(eventArrayList, i)) {
                //  if (eventArrayList.get(i).getPlayer().equals("Andrea Zerini") && eventArrayList.get(i).getDescription().equals("Uscita") ) {
                //      System.out.println(eventArrayList.get(i+1).getPlayer());
                //      System.out.println(eventArrayList.get(i+1).getDescription());
                //      System.out.println(eventArrayList.get(i+1).getTime());
                //      System.out.println(i+1);
                //      System.out.println(eventArrayList.size());}
                //     if (eventArrayList.get(i).getPlayer().equals("Jordan Howard") && eventArrayList.get(i).getDescription().equals("Ingresso") ) {
                //       System.out.println(eventArrayList.get(i-1).getPlayer());
                //       System.out.println(eventArrayList.get(i-1).getDescription());
                //       System.out.println(eventArrayList.get(i-1).getTime());
                //     //  System.out.println(i+1);
                //     //  System.out.println(eventArrayList.size());
                //         System.out.println("Size1: "+slices.size());
                //     }
                // checks if it's the very last one

                //if (i == eventArrayList.size() - 1) {
                //    slices.add(eventArrayList.get(i));
                //    ArrayList<event> copiedslices = new ArrayList<>();
                //    copiedslices.addAll(slices);
                //    teamSlices.add(copiedslices);
                //    slices.clear();} else {
                slices.add(eventArrayList.get(i));
                //     if (eventArrayList.get(i).getPlayer().equals("Jordan Howard") && eventArrayList.get(i).getDescription().equals("Ingresso") ) {
                //       //  System.out.println(eventArrayList.get(i-1).getPlayer());
                //       //  System.out.println(eventArrayList.get(i-1).getDescription());
                //       //  System.out.println(eventArrayList.get(i-1).getTime());
                //         //  System.out.println(i+1);
                //         //  System.out.println(eventArrayList.size());
                //         System.out.println("Size2: "+slices.size());
                //     }
                //  }
            } else {

                if (i == 0) {
                    event event1 = new event();
                    event1.setDescription("start");
                    event1.setTime("10:00");
                    slices.add(event1);
                    slices.add(eventArrayList.get(i));
                }
                //makes sure it is in the middle
                if (i > 0 && i < eventArrayList.size() - 1) {
                    boolean switched = false;
                    // if the previous one is a switch
                    if (SwitchCheck(eventArrayList, i - 1)) {
                        switched = true;
                        ArrayList<event> copiedslices = new ArrayList<>();
                        copiedslices.addAll(slices);
                        teamSlices.add(copiedslices);
                        slices.clear();

                        event event2 = new event();
                        event2.setDescription("start");
                        event2.setTime(eventArrayList.get(i - 1).getTime());
                        slices.add(event2);
                        slices.add(eventArrayList.get(i));
                    }
                    // if the next one is a switch
                    if (SwitchCheck(eventArrayList, i + 1)) {
                        switched = true;
                        ArrayList<event> copiedslices = new ArrayList<>();

                        slices.add(eventArrayList.get(i));
                        event event3 = new event();
                        event3.setDescription("stop");
                        event3.setTime(eventArrayList.get(i + 1).getTime());
                        slices.add(event3);

                        copiedslices.addAll(slices);
                        teamSlices.add(copiedslices);
                        slices.clear();
                    }

                    if (!switched) {slices.add(eventArrayList.get(i));}



                }

                if ((i == eventArrayList.size() - 1)) {
                   // the phantom 7 bug happens before here, the event is'nt present after this
               //     if (  eventArrayList.get(i).getTime().equals("00:00")) {
               //         System.out.println(eventArrayList.get(i).getPlayer());
               //         System.out.println(eventArrayList.get(i).getDescription());
               //         System.out.println(eventArrayList.get(i).getTime());
               //     }

                    if (SwitchCheck(eventArrayList, i - 1)) {

                 //       if (  eventArrayList.get(i).getTime().equals("00:00")) {System.out.println("$$$$$$$");}

                            ArrayList<event> copiedslices = new ArrayList<>();
                        copiedslices.addAll(slices);
                        teamSlices.add(copiedslices);
                        slices.clear();

                        event start = new event();
                        start.setDescription("start");
                        start.setTime(eventArrayList.get(i - 1).getTime());

                        event stop = new event();
                        stop.setDescription("stop");
                        stop.setTime("00:00");

                        slices.add(start);
                        slices.add(eventArrayList.get(i));
                        slices.add(stop);
                        teamSlices.add(slices);
                    } else {
                        ArrayList<event> copiedslices = new ArrayList<>();
                        slices.add(eventArrayList.get(i));

                        event event4 = new event();
                        event4.setDescription("stop");
                        event4.setTime("00:00");
                        slices.add(event4);

                        copiedslices.addAll(slices);
                        teamSlices.add(copiedslices);
                        slices.clear();
                    }


                }


            }
        }
        return teamSlices;
    }

    //  public JSONArray getQuadrantFromMatch(String matchQuadrant) {
    //      JSONObject jsonObject1 = new JSONObject(matchQuadrant);
    //      JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
    //      JSONArray jsonArray1 = jsonObject2.getJSONArray("pbp");
    //      return jsonArray1;
    //  }

    //   public ArrayList<JSONArray> getjsonARRAYSfromMatch(Match match) {
    //       ArrayList<JSONArray> jsonArrays = new ArrayList<>();
    //       JSONArray jsonArray1 = getQuadrantFromMatch(match.q1);
    //       JSONArray jsonArray2 = getQuadrantFromMatch(match.q2);
    //       JSONArray jsonArray3 = getQuadrantFromMatch(match.q3);
    //       JSONArray jsonArray4 = getQuadrantFromMatch(match.q4);
    //       jsonArrays.add(jsonArray1);
    //       jsonArrays.add(jsonArray2);
    //       jsonArrays.add(jsonArray3);
    //       jsonArrays.add(jsonArray4);
    //       return jsonArrays;
    //   }


    public Match getMatch1() {
        return match1;
    }

    public void setMatch1(Match match1) {
        this.match1 = match1;
    }


}
