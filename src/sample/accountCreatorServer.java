package sample;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class accountCreatorServer {
    HttpServer server = HttpServer.create(new InetSocketAddress(8005), 20000);
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    accountCreatorHandler accountCreatorHandler = new accountCreatorHandler();

    public void launch() {
        System.out.println("AND HER NAME IS ERIKA");

        server.createContext("/", accountCreatorHandler);

        server.setExecutor(threadPoolExecutor);

        server.start();

    }

    public accountCreatorServer() throws IOException {
    }
}
