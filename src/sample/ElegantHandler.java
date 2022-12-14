package sample;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class ElegantHandler implements HttpHandler {

    public ElegantHandler() {
    }

    sqlsportbot sqlsportbot = new sqlsportbot();


    @Override
    public void handle(HttpExchange t) throws IOException {
        InputStream ios = t.getRequestBody();
        byte[] input = ios.readAllBytes();
        String inputString = new String(input, StandardCharsets.UTF_8);
        System.out.println(inputString);
        String response = null;
        try {
            response = handlerequest(inputString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        t.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        t.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
        t.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
        t.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public String handlerequest(String entrystring)
            throws SQLException, IOException {

        JSONObject request = new JSONObject(entrystring);
        System.out.println(23740);
        String username = request.getString("username");
        String password = request.getString("password");
        String method = request.getString("method");
        //  Boolean AccessCard = sqlsportbot.IsAccountPresent(username,password);
        int GameID1 = 23740;

  //      System.out.println(request.getString("DEEEEEEERP"));
        if (method.equals("getslice")) {
            //    System.out.println("OP SUCCESS");
            GameID1 = sqlsportbot.get_lastgameid(username, password);
        }
        int team = request.getInt("teamID");
        int quadrant = request.getInt("QuarterNumber");
        ElegantQuadrantCutter elegantQuadrantCutter = new ElegantQuadrantCutter();
        JSONObject theresponse = elegantQuadrantCutter.getQuadrant(quadrant, team,
                String.valueOf(GameID1));

      //System.out.print(theresponse);

        return String.valueOf(theresponse);
    }
}
