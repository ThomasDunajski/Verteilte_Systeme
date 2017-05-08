package xgb.st.de;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Test {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/kuehlschrank/history", new HistoryHandler());
        server.createContext("/kuehlschrank/fuellstand", new FuellstandHandler());
        server.start();
    }

    static class HistoryHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "TODO: History hier ausgeben";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    static class FuellstandHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "TODO: Füllstand hier ausgeben";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}