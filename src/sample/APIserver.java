package sample;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class APIserver {
    HttpServer server = HttpServer.create(new InetSocketAddress(8001), 20000);
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    apihandler apihandler1 = new apihandler();
    public void launch()
    {
        System.out.println("AND HER NAME IS ERIKA");

        server.createContext("/", apihandler1);

        server.setExecutor(threadPoolExecutor);

        server.start();

    }
    public APIserver() throws IOException {
    }
}
