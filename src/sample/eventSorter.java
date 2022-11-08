package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class eventSorter {
    ArrayList<event> events1 = new ArrayList<>();
    ArrayList<ArrayList<event>> megaevents1 = new ArrayList<>();
    ArrayList<event> team1events = new ArrayList<>();
    ArrayList<event> team2events = new ArrayList<>();
    Match match1;

    public eventSorter(Match match1) {
        this.match1 = match1;
    }

    public eventSorter() {
    }

    public void processAllquadrants() {
        processQuadrant(this.match1.q1);
        processQuadrant(this.match1.q2);
        processQuadrant(this.match1.q3);
        processQuadrant(this.match1.q4);
    }

    public ArrayList<ArrayList<ArrayList<event>>> getTeamSlices() {
        processAllquadrants();
        ArrayList<ArrayList<event>> teamSlices1 = createSlices(team1events);
        ArrayList<ArrayList<event>> teamSlices2 = createSlices(team2events);
        ArrayList<ArrayList<ArrayList<event>>> TeamEvents = new ArrayList<>();
        TeamEvents.add(teamSlices1);
        TeamEvents.add(teamSlices2);

        return TeamEvents;


    }

    public void processQuadrant(String matchQuadrant)
    {   JSONObject jsonObject1 = new JSONObject(matchQuadrant);
        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
        JSONArray jsonArray = jsonObject2.getJSONArray("pbp");
        ArrayList<event> events = jsontoarraylist(jsonArray);
        events1.addAll(events);
        Splitevents();
        events1.clear();}

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
                String[] scorecheck = event.description.split(" ");
                if (scorecheck.length > 1) {
                    if (scorecheck[1].equals("punti")) {
                        if (!issbagliato(scorecheck)) {
                            event.setPoints(Integer.parseInt(scorecheck[0]));
                        }
                    }
                }
                if (!event.getDescription().equals("Inizio Tempo")) {
                    events0.add(event);
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

    public void Splitevents() {
        for (int i = 0; i < events1.size(); i++) {
            if (!events1.get(i).teamname.equals("placeholder")) {
                if (team1events.size() == 0 && team2events.size() == 0) {
                    team1events.add(events1.get(i));
                }
                if (team1events.size() > 0) {
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
    }

    public Boolean SwitchCheck(ArrayList<event> eventArrayList, int gorillaposition) {
        return ((eventArrayList.get(gorillaposition).getDescription().equals("Uscita")) || (eventArrayList.get(gorillaposition).getDescription().equals("Ingresso")));
    }

    public Boolean endofSwitchCheck(ArrayList<event> eventArrayList, int gorillaposition) {

        if (gorillaposition + 1 <= eventArrayList.size()) {
            return (!eventArrayList.get(gorillaposition + 1).getDescription().equals("Uscita") && !eventArrayList.get(gorillaposition + 1).getDescription().equals("Ingresso"));
        }
        return false;
    }
    // q1 action switch action switch action switch action end
    // q2 Switch action action switch action action maybeswitch
    // q3 similar
    // q4 similar\
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //   1 2 3 4 5 6 7 8 9 10
// index 0 1 2 3 4 5 6 7 8 9
// size  1 2 3 4 5 6 7 8 9 10
    public ArrayList<ArrayList<event>> createSlices(ArrayList<event> eventArrayList) {
        ArrayList<ArrayList<event>> teamSlices = new ArrayList<>();

        ArrayList<event> slices = new ArrayList<>();
        for (int i = 0; i < eventArrayList.size() - 1; i++) {
            // checks for switch
            if (SwitchCheck(eventArrayList, i)) {
                // checks if it-s the very last one
                if (i == eventArrayList.size() - 1) {
                    slices.add(eventArrayList.get(i));
                    teamSlices.add(slices);
                } else {
                    slices.add(eventArrayList.get(i));
                }
            } else {

                if (i == 0) {
                    event event1 = new event();
                    event1.setDescription("start");
                    event1.setTime("10:00");
                    slices.add(event1);
                    slices.add(eventArrayList.get(i));

                }
                if (i > 0 && i < eventArrayList.size() - 1) {

                    if (SwitchCheck(eventArrayList, i - 1)) {

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
                    if (SwitchCheck(eventArrayList, i + 1)) {
                        ArrayList<event> copiedslices = new ArrayList<>();

                        slices.add(eventArrayList.get(i));
                        event event3 = new event();
                        event3.setDescription("stop");
                        event3.setTime(eventArrayList.get(i + 1).getTime());
                        slices.add(event3);

                        copiedslices.addAll(slices);
                        teamSlices.add(copiedslices);
                        slices.clear();
                    } else {
                        slices.add(eventArrayList.get(i));
                    }

                }

                if ((eventArrayList.size() - 1 == i)) {
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

    public ArrayList<event> getEvents1() {
        return events1;
    }

    public void setEvents1(ArrayList<event> events1) {
        this.events1 = events1;
    }

    public ArrayList<ArrayList<event>> getMegaevents1() {
        return megaevents1;
    }

    public void setMegaevents1(ArrayList<ArrayList<event>> megaevents1) {
        this.megaevents1 = megaevents1;
    }


    public Match getMatch1() {
        return match1;
    }

    public void setMatch1(Match match1) {
        this.match1 = match1;
    }


}
