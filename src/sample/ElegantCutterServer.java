package sample;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ElegantCutterServer {
    HttpServer server = HttpServer.create(new InetSocketAddress(8002), 20000);
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    ElegantHandler ElegantHandler1 = new ElegantHandler();

    public void launch()
    {   System.out.println("AND HER NAME IS ERIKA");

        server.createContext("/", ElegantHandler1);

        server.setExecutor(threadPoolExecutor);

        server.start();}

    public ElegantCutterServer() throws IOException {
    }
}
