package Central;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class TestHandler implements HttpHandler {
    	
    	private History history;
    	
        @Override
        public void handle(HttpExchange t) throws IOException {
        	String response = "";
        	for (int i = 0; i < 1000; i++){
        		response += "lorem ipusm \n";
        	}
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        
        
    }