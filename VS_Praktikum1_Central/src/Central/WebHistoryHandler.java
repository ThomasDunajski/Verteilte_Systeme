package Central;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class WebHistoryHandler implements HttpHandler {
    	
    	private History history;
    	
    	public WebHistoryHandler(History history){
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