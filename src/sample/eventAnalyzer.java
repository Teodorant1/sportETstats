package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class eventAnalyzer {

    ArrayList<ArrayList<event>> megaevents1 = new ArrayList<>();
    ArrayList<twoitemholder> coordinates = new ArrayList<>();
    ArrayList<PlayerGroup> playerGroupArrayList = new ArrayList<>();
    ArrayList<event> events1 = new ArrayList<>();


    public void analyzeQuadrant(Match match1) {
        JSONObject jsonObject1 = new JSONObject(match1.getQ1());
        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
        JSONArray jsonArray1 = jsonObject2.getJSONArray("pbp");
        jsontoarraylist(jsonArray1);
        Splitevents(events1);
        createcoordinates(megaevents1.get(1));
        loadevents(megaevents1.get(1), coordinates);

        for (int i = 0; i < playerGroupArrayList.get(0).getEvents().size(); i++) {}}
    public void jsontoarraylist(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.getString("description").equals("Ingresso")) {System.out.println(" Ingresso ");}
            {
                event event = new event(
                        jsonObject.optString("player_name", "placeholder")
                                + " " + jsonObject.optString("player_surname", "placeholder"),
                        jsonObject.optString("description", "placeholder"),
                        jsonObject.optString("print_time", "placeholder"),
                        jsonObject.optString("team_name", "placeholder")
                );

                if (event.description.equals("Ingresso")) {
                    System.out.println("We have Ingresso");
                    System.out.println(i);
                }

                // System.out.println(event.getDescription());

                String[] scorecheck = event.description.split(" ");

                if (scorecheck.length > 1) {
                    if (scorecheck[1].equals("punti")) {
                        event.setPoints(Integer.parseInt(scorecheck[0]));
                    }
                    events1.add(event);
                }
            }

        }


    }

    public ArrayList<ArrayList<event>> Splitevents(ArrayList<event> eventArrayList) {
        ArrayList<ArrayList<event>> megaevents = new ArrayList<>();
        ArrayList<event> team1events = new ArrayList<event>();
        ArrayList<event> team2events = new ArrayList<event>();

        for (int i = 0; i < eventArrayList.size(); i++) {
            //  System.out.println(i);
            //  System.out.println(eventArrayList.get(i).getTeamname());
            System.out.println(eventArrayList.get(i).getDescription());
            //  System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        }
        for (int i = 0; i < eventArrayList.size(); i++) {
            if (team1events.size() == 0) {
                if (eventArrayList.get(i).teamname.equals("placeholder")) {
                } else {
                    team1events.add(eventArrayList.get(i));
                }
            } else {
                if (eventArrayList.get(i).teamname.equals(team1events.get(0).getTeamname())) {
                    team1events.add(eventArrayList.get(i));
                } else {
                    //    if (eventArrayList.get(i).teamname.equals("placeholder"))
                    //    {}
                    // else
                    {
                        team2events.add(eventArrayList.get(i));
                    }
                }
            }

        }

        megaevents.add(team1events);
        megaevents.add(team2events);
        return megaevents;
    }

    public Boolean checkswitchintervals(ArrayList<event> eventArrayList, int gorillaposition) {
        //       System.out.println(gorillaposition);
        //       System.out.println((eventArrayList.get(gorillaposition).getDescription()));
        //
        //    System.out.println((eventArrayList.get(gorillaposition).getDescription().equals("Ingresso")));
        //  System.out.println((!eventArrayList.get(gorillaposition + 1).getDescription().equals("Uscita")));


        return (eventArrayList.get(gorillaposition).getDescription().equals("Ingresso")) &&
                (!eventArrayList.get(gorillaposition + 1).getDescription().equals("Uscita"));
    }

    public ArrayList<twoitemholder> createcoordinates(ArrayList<event> eventArrayList) {
        ArrayList<twoitemholder> coordinates = new ArrayList<>();
        coordinates.add(new twoitemholder());
        for (int i = 0; i < eventArrayList.size() - 1; i++) {

            //      System.out.println(checkswitchintervals(eventArrayList, i));

            if (checkswitchintervals(eventArrayList, i))
            //        && (coordinates.get(coordinates.size()).getStart().equals("placeholder")))
            {
                //    System.out.println("very true");
                // String numba = Integer.toString(i);

                if (coordinates.get(coordinates.size()).getStart() == (-10)) {
                    coordinates.get(coordinates.size()).setStart(i);
                } else {
                    coordinates.get(coordinates.size()).setEnd(i);
                }
            }
            //   else {
            //       String numba = Integer.toString(i);
            //       coordinates.get(coordinates.size()).setEnd(numba);
            //       coordinates.add(new twoitemholder());
            //   }
        }

        return coordinates;
    }


    public ArrayList<PlayerGroup> loadevents
            (ArrayList<event> eventArrayList, ArrayList<twoitemholder> coordinates) {
        ArrayList<PlayerGroup> playerGroupArrayList = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i++) {
            playerGroupArrayList.add(new PlayerGroup());
            //    System.out.println("120");
            //    System.out.println((coordinates.get(i).getStart()));
            //    System.out.println((coordinates.get(i).getEnd()));

            for (int o = (coordinates.get(i).getStart()); o < (coordinates.get(i).getEnd()); o++) {
                playerGroupArrayList.get(i).getEvents().add(eventArrayList.get(o));
                //    System.out.println(eventArrayList.get(0).getDescription());
            }

        }
        return playerGroupArrayList;
    }


}
