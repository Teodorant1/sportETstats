package sample;


import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        webscraper webscraper = new webscraper();
        webscraper.getPage();


        //   VerticalAPIscanner verticalAPIscanner = new VerticalAPIscanner();
        //   verticalAPIscanner.maintest("23740");


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
