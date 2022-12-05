package sample;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        //    webscraper webscraper = new webscraper();
        //    webscraper.getPage();


        //   VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
        //   verticalAPIscanner.maintest("23740");


        //  ArrayList <String> num1 = new ArrayList<>();
        //  ArrayList <String> num2 = new ArrayList<>();

        //  num1.add("Donald Trump");
        //  num1.add("Desantis");
        //  num1.add("Mike Pence");
        //  num1.add("Nick Fuentes");

        //  num2.add("Bernie Sanders");
        //  num2.add("Joe Biden");
        //  num2.add("Andrew Yang");
        //  num2.add("Steven Bonell");


        //  Collections.sort(num1);
        //  Collections.sort(num2);

        //  System.out.println(num1);
        //  System.out.println(num2);

        //  System.out.println(num1.equals(num2));

       yearly_reporter yearly_reporter = new yearly_reporter();
       yearly_reporter.maintest();

    //    JSONObject jsonObject = new JSONObject();
    //    jsonObject.put("derp", "derp");
    //    System.out.println(jsonObject + "1");
    //    jsonObject.clear();
    //    System.out.println(jsonObject + "1");

     //  String paloki = "[Giancarlo Ferrero, Giovanni De Nicolao, Jaron Johnson, Markel Brown, Tariq Owens, enemyscore, time]";

     //  String SonOfPaloki = paloki.substring(1, paloki.length() - 1);

     //  String[] splits = SonOfPaloki.split(",");


     //  ArrayList<String> splitslist = new ArrayList(List.of(splits));
     //  ArrayList<String> splitslist1 = new ArrayList();
     //  splitslist1.addAll(splitslist);

     //  ArrayList<String> filteredSplits = new ArrayList<>();


     //  for (int i = 0; i < splitslist1.size(); i++) {
     //      if (!splitslist1.get(i).strip().equals("enemyscore") && !splitslist1.get(i).strip().equals("time")) {

     //          filteredSplits.add(splitslist1.get(i).strip());

     //    System.out.println(splitslist1.get(i));
     //    System.out.println(splitslist1.get(i).length());
     //    System.out.println(splitslist1.get(i).strip().length());
     //       }
     //   }


      //  System.out.println(filteredSplits);


        //   JSONArray jsonArray = new JSONArray(paloki);
        //   System.out.println(jsonArray.get(0));

        VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
        verticalAPIscanner.maintest("23726");

        gameIDserver gameIDserver = new gameIDserver();
        gameIDserver.launch();


        ElegantCutterServer elegantCutterServer = new ElegantCutterServer();
        elegantCutterServer.launch();


        APIserver apIserver = new APIserver();
        apIserver.launch();

        loginserver loginserver = new loginserver();
        loginserver.launch();


    }
}
