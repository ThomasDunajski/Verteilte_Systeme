package Central;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class WebFuellstandHandler implements HttpHandler {
	
    	private History history;
    	
    	public WebFuellstandHandler(History history){
    		setHistory(history);
    	}
    	
        @SuppressWarnings("unchecked")
		@Override
        public void handle(HttpExchange t) throws IOException {
        	String response = FoodParser.parseFoodsToString((Vector<String>) history.getHistory().clone()); 
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