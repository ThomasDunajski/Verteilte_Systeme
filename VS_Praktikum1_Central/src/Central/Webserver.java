package Central;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Vector;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Webserver {

    
    public void start(History history) throws IOException{		//http://localhost:8000/kuehlschrank/fuellstand
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/kuehlschrank/history", new HistoryHandler(history));
        server.createContext("/kuehlschrank/fuellstand", new FuellstandHandler(history));
        server.start();
    }

    static class HistoryHandler implements HttpHandler {
    	private History history;
    	
    	public HistoryHandler(History history){
    		setHistory(history);
    	}
    	
        @Override
        public void handle(HttpExchange t) throws IOException {
        	Vector<String> foods = history.getHistory();
            String response = "";
            for(int i = 0; i < foods.size(); i++){
            	response += foods.elementAt(i) + "\n";
            }
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        
		public History getHistory() {
			return history;
		}


		public void setHistory(History history) {
			this.history = history;
		}
        
    }
    
    static class FuellstandHandler implements HttpHandler {
    	private History history;
    	public FuellstandHandler(History history){
    		setHistory(history);
    	}
    	
        @Override
        public void handle(HttpExchange t) throws IOException {
        	Vector<String> foods = history.getHistory();	//original
        	Vector<String> currentFood = new Vector<String>();
        	
        	
        	
            String temp = "";
            for(int j = 0; j < )
            String last = "";
            for(int i = 0; i < foods.size(); i++){
            	String[] temp2 = foods.elementAt(i).split(" ");
            	temp = temp2[0];
            	if(foods.elementAt(i).contains(temp)){
            		last = foods.elementAt(i);
            	}
            }
            currentFood.add(last);
            
            
            String response = "";
            for(int i = 0; i < currentFood.size(); i++){
            	response += currentFood.elementAt(i) + "\n";
            }
            
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }


		public History getHistory() {
			return history;
		}


		public void setHistory(History history) {
			this.history = history;
		}
    }

}