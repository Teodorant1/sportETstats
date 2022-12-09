package sample;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

public class yearlyreporterHandler implements HttpHandler {
    sqlsportbot sqlsportbot = new sqlsportbot();

    @Override
    public void handle(HttpExchange t) throws IOException {
        InputStream ios = t.getRequestBody();
        byte[] input = ios.readAllBytes();
        String inputString = new String(input, StandardCharsets.UTF_8);
        System.out.println(inputString);
        System.out.println(8006);
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
        assert response != null;
        t.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public String handlerequest(String entrystring) throws SQLException, IOException {

        System.out.println(entrystring);
        JSONObject request = new JSONObject(entrystring);

        int url = request.getInt("url");
        String teamname = request.getString("teamname");
        // System.out.println(url);
        // System.out.println(teamname.length());
        // System.out.println(teamname);
        yearly_reporter yearly_reporter = new yearly_reporter();

        String payload = yearly_reporter.maintest(String.valueOf(url), teamname);

        System.out.println(payload);
        return payload;
    }
}
