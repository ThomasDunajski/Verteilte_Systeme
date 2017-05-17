package RPC;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.sun.javafx.runtime.SystemProperties;

import Central.Food;
import Central.FoodParser;
import Central.History;

public class Buyer {

	private double budget;
	private History history;
	
	public Buyer(double budget, History history) {
		this.budget = budget;
		this.history = history;
	}
	
	public void check(String foodString){
		Food food = FoodParser.parseFood(foodString);
		if (food.getAmount() < food.getWhenToBuy()){
			order(food.getName(), food.getOptimalAmount());
		}
	}
	
	private void order(String food, int amount){
	    try {
	        TTransport transport;
	       
	        transport = new TSocket("localhost", 9090);
	        transport.open();

	        TProtocol protocol = new  TBinaryProtocol(transport);
	        Shop.Client client = new Shop.Client(protocol);

	        perform(client);

	        transport.close();
	      } catch (TException x) {
	        x.printStackTrace();
	      } 
	    }

  private static void perform(Shop.Client client) throws TException
  {
    Order o = new Order();
    o.setAmount(1);
    o.setProduct("Milch");
    boolean orderAccepted = client.placeOrder(o);
    if (orderAccepted){
    	System.out.println("Order accepted by shop");  	
    }
    else{
    	System.out.println("failed to order new " + o.getProduct());
    }
    
  }
}
