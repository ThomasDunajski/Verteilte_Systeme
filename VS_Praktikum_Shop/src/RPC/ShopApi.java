package RPC;
import java.util.Vector;

import RPC.Shop.Iface;

public class ShopApi extends Shop implements Iface{

	private Vector<Food> foods;
	
	public ShopApi() {
		super();
		this.foods = new Vector<Food>();
		this.foods.add(new Food(2.0, "Milch"));
		this.foods.add(new Food(2.0, "Käse"));
		this.foods.add(new Food(2.0, "Salami"));
		this.foods.add(new Food(2.0, "Butter"));
	}

	@Override
    public boolean placeOrder(Order order) throws org.apache.thrift.TException {
		for(Food food : foods) {
			if (food.getName().equals(order.getProduct())){
				return true;	//just if this order is buyable
			}
		}
		return false;
	}

	@Override
    public double getPrice(String name) throws org.apache.thrift.TException {
		for (Food food : foods) { 
			if (food.getName().equals(name)){
				return food.getPrice();
			}
		}
		return -1;
	}
}
