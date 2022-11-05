package sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        sqlsportbot sqlsportbot1 = new sqlsportbot();
        //  sqlsportbot1.insertMatch(
        //          "23736",
        //          "OPENJOBMETIS VARESE",
        //          "NUTRIBULLET TREVISO BASKET" );


        //  HashMap<String, Player> playerHashMap = new HashMap<>();
        //  System.out.println(playerHashMap.size());
        //  playerHashMap.put("Paloki", new Player());
        //  System.out.println(playerHashMap.size());
        //  playerHashMap.put("Paloki", new Player());
        //  System.out.println(playerHashMap.size());
        //  playerHashMap.put("Paloki", new Player());
        //  System.out.println(playerHashMap.size());



        Match match1 = sqlsportbot1.pullMatch("23736", "23736", "23736");
        eventAnalyzer eventAnalyzer1 = new eventAnalyzer();
        eventAnalyzer1.analyzeQuadrant(match1);


//    httpfetch httpfetch1 = new httpfetch();
//    System.out.println(httpfetch1.bigFetch("23736"));

        //  System.out.println("Team1 is " + match1.team1);
        //  System.out.println("Team2 is " + match1.team2);
        //  System.out.println(match1.getQ1());
        //  System.out.println("xxxxxxxxxxxxxx");
        //  System.out.println(match1.getQ2());
        //  System.out.println("xxxxxxxxxxxxxx");
        //  System.out.println(match1.getQ3());
        //  System.out.println("xxxxxxxxxxxxxx");
        //  System.out.println(match1.getQ4());
        //  System.out.println("xxxxxxxxxxxxxx");
    }
}
