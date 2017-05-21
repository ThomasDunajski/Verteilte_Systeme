package RPC;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import Central.Food;
import Central.FoodParser;
import Central.History;

public class Buyer extends Client {

	private double budget;
	private History history; 	//just a reference

	public Buyer(int port, double budget, History history) {
		super(port);			//port to freezer
		this.setBudget(budget);
		this.setHistory(history);
	}

	public void checkAndBuy(String foodString) {
		Food food = FoodParser.parseFood(foodString);
		if (food.getAmount() < food.getWhenToBuy()) {
			order(food, food.getOptimalAmount() - food.getAmount());	//just buy needed food
		}
	}

	private void order(Food food, int amount) {
		try {
			TTransport transport = new TSocket("localhost", 9090);	//port to shop
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			Shop.Client client = new Shop.Client(protocol);

			perform(client, food, amount);

			transport.close();
		} catch (TException x) {
			System.err.println("Can not connect to a shop");
		}
	}

	private void perform(Shop.Client client, Food food, int amount){
		try{
			Order o = new Order();
			o.setAmount(amount);
			o.setProduct(food.getName());
			
			boolean orderAccepted = client.placeOrder(o);
			double price = client.getPrice(o.getProduct());
			if(orderAccepted && budget >= price) {
				System.out.println("Buyer send an accepted order to shop.");
				sendData(food.getName() + " " + amount + " " 
						+ food.getOptimalAmount() + " " + food.getWhenToBuy());
				subBudget(price);
				System.out.println("Increased " + food.getName() + " in freezer by " + amount + ".");
			} else {
				System.out.println("failed to order " + o.getProduct() + ": " + o.getAmount());
			}
			System.out.println("Current Budget = " + budget);
		}catch(TException x){
			x.printStackTrace();
		}
	}

	
	public void addBudget(double add){
		setBudget(budget + add);
	}
	
	public void subBudget(double sub){
		setBudget(budget - sub);
	}
	
	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}
}
