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
        	Vector<String> foods = (Vector<String>) history.getHistory().clone();	//original
        	Vector<String> currentFood = new Vector<String>();						//toPrint
        	
            for(int j = 0; j < foods.size(); j++){
            	String[] temp2 = foods.elementAt(j).split(" ");
            	String name = temp2[0];
            	String last = "";
                for(int i = 0; i < foods.size(); i++){
                	if(foods.elementAt(i).contains(name)){
                		last = foods.remove(i);
                	}
                }
                currentFood.add(last);
            }
            
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