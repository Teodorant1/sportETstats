package sample;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        //    webscraper webscraper = new webscraper();
        //    webscraper.getPage();

        //    VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
        //    verticalAPIscanner.maintest("23726");

       //     sqlsportbot sqlsportbot = new sqlsportbot();
       //     sqlsportbot.insert_account("marcopolo", "marcopolo@gmail.com",
       //     "bossman", "marcopolo", 23726);

        // yearlyreporterHandler yearlyreporterHandler = new yearlyreporterHandler();
        // yearlyreporterHandler.handlerequest("{teamname:NutriBullet Treviso Basket, url:1542}");




        gameIDserver gameIDserver = new gameIDserver();
        gameIDserver.launch();

        ElegantCutterServer elegantCutterServer = new ElegantCutterServer();
        elegantCutterServer.launch();

        APIserver apIserver = new APIserver();
        apIserver.launch();

        loginserver loginserver = new loginserver();
        loginserver.launch();

        //8005
        accountCreatorServer accountCreatorServer = new accountCreatorServer();
        accountCreatorServer.launch();

        yearlyreporterServer yearlyreporterServer = new yearlyreporterServer();
        yearlyreporterServer.launch();

    }
}
