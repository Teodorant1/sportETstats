package sample;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class gameIDchangeHandler implements HttpHandler {


    sqlsportbot sqlsportbot = new sqlsportbot();


    public gameIDchangeHandler(){}

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

        int gameID = request.getInt("gameID");
        String username = request.getString("username");
        String password = request.getString("password");

        sqlsportbot.update_lastgameID(username,password,gameID);

        return ("OP SUCCESS");
    }

}
