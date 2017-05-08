package Central;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;


public class Webserver {
    
    public void start(History history) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/kuehlschrank/history", new WebHistoryHandler(history));
        server.createContext("/kuehlschrank/fuellstand", new WebFuellstandHandler(history));
        server.start();
        //http://localhost:8000/kuehlschrank/history
        //http://localhost:8000/kuehlschrank/fuellstand
    }

}