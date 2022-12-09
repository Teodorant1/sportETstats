package sample;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class yearlyreporterServer {
    HttpServer server = HttpServer.create(new InetSocketAddress(8006), 20000);
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    yearlyreporterHandler yearlyreporterHandler = new yearlyreporterHandler();

    public void launch() {
        System.out.println("AND HER NAME IS ERIKA");

        server.createContext("/", yearlyreporterHandler);

        server.setExecutor(threadPoolExecutor);

        server.start();

    }

    public yearlyreporterServer() throws IOException {
    }
}
