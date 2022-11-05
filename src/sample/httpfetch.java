package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class httpfetch {

// "https://www.legabasket.it/match/23736/pbp/3"

    public String bigFetch( String gameID ) throws IOException {
        String left = "https://www.legabasket.it/match/";
        String right = "/pbp/";
        StringBuilder bigStringbuilder1 = new StringBuilder();
        bigStringbuilder1.append(Fetch(left+gameID+right+"1"));
        bigStringbuilder1.append(Fetch(left+gameID+right+"2"));
        bigStringbuilder1.append(Fetch(left+gameID+right+"3"));
        bigStringbuilder1.append(Fetch(left+gameID+right+"4"));

        return bigStringbuilder1.toString();

    }

    public String Fetch(String address) throws IOException {


        URL url = new URL(address);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
