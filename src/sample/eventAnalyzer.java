package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class eventAnalyzer {
    ArrayList<event> events1 = new ArrayList<>();
    ArrayList<ArrayList<event>> megaevents1 = new ArrayList<>();
    ArrayList<ArrayList<event>> teamSlices = new ArrayList<>();

    public void analyzeQuadrant(Match match1) {
        JSONObject jsonObject1 = new JSONObject(match1.getQ1());
        JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
        JSONArray jsonArray1 = jsonObject2.getJSONArray("pbp");
        jsontoarraylist(jsonArray1);
        Splitevents();
        createSlices(megaevents1.get(0));
        PlayerGroup playerGroup = new PlayerGroup();
        playerGroup.sliceToStats(teamSlices.get(0));
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

    public void jsontoarraylist(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            {
                event event = new event(jsonObject.optString("player_name", "placeholder")
                        + " " + jsonObject.optString("player_surname", "placeholder"),
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
                events1.add(event);
            }
        }
    }

    public void Splitevents() {
        ArrayList<event> team1events = new ArrayList<event>();
        ArrayList<event> team2events = new ArrayList<event>();
        for (int i = 0; i < events1.size(); i++) {
            if (team1events.size() == 0) {
                if (events1.get(i).teamname.equals("placeholder")) {
                } else {
                    team1events.add(events1.get(i));
                }
            } else {
                if (events1.get(i).teamname.equals(team1events.get(0).getTeamname())) {
                    team1events.add(events1.get(i));
                } else {
                    {
                        team2events.add(events1.get(i));
                    }
                }
            }
        }
        event event = new event();
        event.setTime("10:00");
        event.setDescription("AND THE GAME BEGINS!");
        team1events.add(0, event);
        team2events.add(0,event);


        megaevents1.add(team1events);
        megaevents1.add(team2events);
        //    return megaevents1;
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


    //   1 2 3 4 5 6 7 8 9 10
// index 0 1 2 3 4 5 6 7 8 9
// size  1 2 3 4 5 6 7 8 9 10
    public void createSlices(ArrayList<event> eventArrayList) {
        ArrayList<event> slices = new ArrayList<>();
        for (int i = 0; i < eventArrayList.size() - 1; i++) {
            // checks for switch
            if (SwitchCheck(eventArrayList, i)) {
                // checks if it's the first switch event
                if (i > 0) {
                    if (!SwitchCheck(eventArrayList, i - 1)) {
                        //  xxxxxxxxYYYXxxxxxxxx
                        event event1 = new event();
                        event1.setDescription("stop");
                        event1.setTime(
                                eventArrayList.get(i).getTime());
                        slices.add(event1);

                        ArrayList<event> copiedslice = new ArrayList<>();
                        copiedslice.addAll(slices);
                        teamSlices.add(copiedslice);
                        slices.clear();
                        slices.add(eventArrayList.get(i));}}

                    // adds the very last event if it's a switch
                if (i == eventArrayList.size() - 1)
                   {slices.add(eventArrayList.get(i));
                    teamSlices.add(slices);}

                    // index is always 1 smaller than size
                    if (i <= eventArrayList.size()-2) {
                    // checks that both neighbors are also Switch events
                    if (SwitchCheck(eventArrayList, i - 1)
                        &&
                        SwitchCheck(eventArrayList, i + 1))
                        {slices.add(eventArrayList.get(i));}


                    // checks if it's the last Switch event in its chain
                    if (SwitchCheck(eventArrayList, i) && !SwitchCheck(eventArrayList, i + 1)) {
                        ArrayList<event> copiedslice = new ArrayList<>();
                        copiedslice.addAll(slices);
                        teamSlices.add(copiedslice);
                        slices.clear();

                        event event1 = new event();
                        event1.setDescription("start");
                        event1.setTime(eventArrayList.get(i).getTime());

                        slices.add(event1);
                        slices.add(eventArrayList.get(i));
                    }
                }
            } else {
                slices.add(eventArrayList.get(i));
            }
        }
    }


}
