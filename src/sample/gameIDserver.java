package sample;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class gameIDserver {
    HttpServer server = HttpServer.create(new InetSocketAddress(8004), 20000);
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    gameIDchangeHandler gameIDchangeHandler = new gameIDchangeHandler();

    public gameIDserver() throws IOException {  }

    public void launch()
    {   System.out.println("AND HER NAME IS ERIKA");

        server.createContext("/", gameIDchangeHandler);

        server.setExecutor(threadPoolExecutor);

        server.start();}
}
