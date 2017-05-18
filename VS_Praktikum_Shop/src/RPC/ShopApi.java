package RPC;
import java.util.Vector;

import RPC.Shop.Iface;

public class ShopApi extends Shop implements Iface{

	private Vector<Food> foods;
	
	public ShopApi(Vector<Food> food) {
		super();
		this.foods = food;
	}

	@Override
    public boolean placeOrder(Order order) throws org.apache.thrift.TException {
		for (Food food : foods) { 
			if (food.getName().equals(order.getProduct())){
				return true;
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
