package sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {


        HashMap<String, String> paloki = new HashMap<>();
        System.out.println("hash size:" + paloki.size());
        paloki.put("paloki","paloki");
        System.out.println("hash size:" + paloki.size());



        VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
        verticalAPIscanner.maintest("23736");

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
