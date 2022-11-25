package sample;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class loginserver {
    HttpServer server = HttpServer.create(new InetSocketAddress(8003), 20000);
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    loginhandler loginhandler = new loginhandler();

    public loginserver() throws IOException {
    }

    public void launch() {
        System.out.println("AND HER NAME IS ERIKA");

        server.createContext("/", loginhandler);

        server.setExecutor(threadPoolExecutor);

        server.start();
    }


}
