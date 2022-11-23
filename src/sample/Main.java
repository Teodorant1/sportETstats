package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
     // ElegantQuadrantCutter elegantQuadrantCutter = new ElegantQuadrantCutter();
     // elegantQuadrantCutter.getQuadrant(1, 2, "23740");
     // System.out.println(elegantQuadrantCutter.getQuadrant
     //         (1, 2, "23740"));

        ElegantCutterServer elegantCutterServer = new ElegantCutterServer();
        elegantCutterServer.launch();

     // VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
     // verticalAPIscanner.maintest("23740");
        APIserver apIserver = new APIserver();
        apIserver.launch();


    }
}
