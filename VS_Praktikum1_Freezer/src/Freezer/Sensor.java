package Freezer;

import java.util.Observable;
import java.util.Observer;

public class Sensor extends Client implements Observer{
	
	
	public Sensor(int port){
		super(port);
	}
	
	
	@Override
	public void update(Observable observable, Object status) {
		if(observable instanceof Food){
			saveInHistory((Food) observable);
		}else{
			System.err.println("Can not update a non-Food-Instance");
		}
	}
	
	
	private void saveInHistory(Food food){
		line = food.toString() + " " + food.getOptimalAmount() + " " + food.getWhenToBuy();
		sendData(line);
	}
}
