package sample;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        //    webscraper webscraper = new webscraper();
        //    webscraper.getPage();


        //     sqlsportbot sqlsportbot = new sqlsportbot();
        //     sqlsportbot.insert_account("marcopolo", "marcopolo@gmail.com",
        //     "bossman", "marcopolo", 23726);

   //     yearlyreporterHandler yearlyreporterHandler = new yearlyreporterHandler();
   //     yearlyreporterHandler.handlerequest("{teamname:Openjobmetis Varese, url:1543}");


     //    VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
     //    verticalAPIscanner.maintest("23726");

    //   ArrayList<Integer> numbers = new ArrayList<>();
    //   ArrayList<event> events = new ArrayList<>();

    //   for (int i = 1; i <= 100; i++) {
    //       // numbers.add(i);
    //       event event = new event();
    //       event.setIndex(i);
    //       events.add(event);
    //   }

    //   Collections.shuffle(events);
    //   for (int i = 0; i < events.size(); i++)
    //   {
    //       System.out.println(events.get(i).getIndex());
    //   }        //   System.out.println(numbers);
    //   System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    //   Collections.sort(events, new Comparator<event>() {
    //       @Override
    //       public int compare(event o1, event o2) {
    //           return o2.getIndex()-o1.getIndex();
    //       }
    //   });

    //   for (int i = 0; i < events.size(); i++)
    //   {
    //       System.out.println(events.get(i).getIndex());
    //   }

        //  int sliceCounter = 0;
        //  ArrayList<Integer> subnumbers = new ArrayList<>();

        //  for (int i = 0; i < numbers.size(); i++) {
        //      sliceCounter++;
        //      subnumbers.add(numbers.get(i));

        //      if (subnumbers.size() == 10) {
        //          System.out.println(subnumbers);
        //          subnumbers.clear();
        //      }
        //  }


    // if ((!(11 % 10 == 0))) {
    // }
    //   JSONArray jsonArray = new JSONArray();
    //   jsonArray.put("Q");

    //   System.out.println("size be like : "+jsonArray.length());


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
