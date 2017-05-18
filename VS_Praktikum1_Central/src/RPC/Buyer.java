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

	private double currentPrice;
	private Shop.Client cheapestShop;
	private TTransport cheapestTransport;

	public Buyer(double budget, History history) {
		this.budget = budget;
		this.history = history;
	}

	public void check(String foodString) {
		Food food = FoodParser.parseFood(foodString);
		if (food.getAmount() < food.getWhenToBuy()) {
			order(food.getName(), food.getOptimalAmount());
		}
	}

	private void order(String food, int amount) {

		currentPrice = Double.MAX_VALUE;
		cheapestShop = null;
		
		checkPrices(food, 9090);
		checkPrices(food, 9091);
		if (currentPrice != Double.MAX_VALUE){
		try {
			System.out.println("trying to buy " + amount + food + " for " + currentPrice );
			performOrder(cheapestShop, amount);
		} catch (TException e1) {
			cheapestTransport.close();
			cheapestShop = null;
		}
		}
	}

	private void checkPrices(String name, int port) {
		try {
			TTransport transport;

			transport = new TSocket("localhost", port);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			Shop.Client client = new Shop.Client(protocol);

			double price = client.getPrice(name);
			if (price < currentPrice) {
				if (cheapestShop != null) {
					cheapestTransport.close();	
				}
				cheapestShop = client;
				currentPrice = price;
			}
		} catch (TException x) {
			System.out.println("shop at: " + port + " could not be reached");
		}
	}

	private void performOrder(Shop.Client client, int amount) throws TException {
		Order o = new Order();
		o.setAmount(amount);
		o.setProduct("Milch");
		boolean orderAccepted = client.placeOrder(o);
		if (orderAccepted) {
			System.out.println("Order accepted by shop");
		} else {
			System.out.println("failed to order new " + o.getProduct());
		}
		if (cheapestTransport != null){
			cheapestTransport.close();
		}
	}
}
