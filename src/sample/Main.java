package sample;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

    public static void main(String[] args) throws IOException {

    //    webscraper webscraper = new webscraper();
    //    webscraper.getPage();


        //   VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
        //   verticalAPIscanner.maintest("23740");


        ArrayList <String> num1 = new ArrayList<>();
        ArrayList <String> num2 = new ArrayList<>();

        num1.add("Donald Trump");
        num1.add("Desantis");
        num1.add("Mike Pence");
        num1.add("Nick Fuentes");

        num2.add("Bernie Sanders");
        num2.add("Joe Biden");
        num2.add("Andrew Yang");
        num2.add("Steven Bonell");


        Collections.sort(num1);
        Collections.sort(num2);

        System.out.println(num1);
        System.out.println(num2);

        System.out.println(num1.equals(num2));

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
