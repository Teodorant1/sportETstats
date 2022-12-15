package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpfetch {

// "https://www.legabasket.it/match/23736/pbp/3"

    public Match bigFetch(String gameID) throws IOException
    {   String left = "https://www.legabasket.it/match/";
        String right = "/pbp/";
        String rightest = "/ASC";
        String q1 = Fetch(left + gameID + right + "1"+rightest);
        String q2 = Fetch(left + gameID + right + "2"+rightest);
        String q3 = Fetch(left + gameID + right + "3"+rightest);
        String q4 = Fetch(left + gameID + right + "4"+rightest);
        Match match = new Match(q1, q2, q3, q4, gameID, gameID, gameID);
        return match;}

    public String Fetch(String address) throws IOException {


        URL url = new URL(address);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder Stringbuilder1 = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            //    System.out.println(inputLine);
            Stringbuilder1.append(inputLine);
        }
        in.close();

        return Stringbuilder1.toString();

    }
}
