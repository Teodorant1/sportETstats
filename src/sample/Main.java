package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

      //   JSONArray jsonArray = new JSONArray();
      //   jsonArray.put(1);
      //   jsonArray.put(2);
      //   jsonArray.put(3);
      //   jsonArray.put(4);
      //   jsonArray.put(5);

      //   System.out.println(jsonArray);
      //   System.out.println(jsonArray.length());
      //   System.out.println(jsonArray.get(4));

        VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
        verticalAPIscanner.maintest("23740");

        APIserver apIserver = new APIserver();
        apIserver.launch();


//    HashMap <String , Integer> paloki = new HashMap<>() ;
//
//    paloki.put("bla" , 1);
//    paloki.put("bla" , 2);
//
//    System.out.println(paloki.get("bla"));
//
//    paloki.put("bla" , 3);
//
//    System.out.println(paloki.get("bla"));


    }
}
