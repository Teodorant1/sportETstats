package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException {
     // ElegantQuadrantCutter elegantQuadrantCutter = new ElegantQuadrantCutter();
     // elegantQuadrantCutter.getQuadrant(1, 2, "23740");
     // System.out.println(elegantQuadrantCutter.getQuadrant
     //         (1, 2, "23740"));

     //   sqlsportbot sqlsportbot = new sqlsportbot();
     //   sqlsportbot.update_lastgameID("john","john",23736);

        gameIDserver gameIDserver = new gameIDserver();
        gameIDserver.launch();


        ElegantCutterServer elegantCutterServer = new ElegantCutterServer();
        elegantCutterServer.launch();

     // VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
     // verticalAPIscanner.maintest("23740");
        APIserver apIserver = new APIserver();
        apIserver.launch();

        loginserver loginserver = new loginserver();
        loginserver.launch();


    }
}
