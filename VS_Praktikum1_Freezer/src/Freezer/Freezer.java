package Freezer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;

public class Freezer implements ActionListener{

	protected boolean running = false;
	protected Vector<Food> foods;
	protected Timer decreaseTimer;
	protected UDPServer fromCentral;
	
	
	public Freezer(int port){		
		setFoods(new Vector<Food>());
		decreaseTimer = new Timer(5000, this);
		setUdpServer(new UDPServer(1111, this));
	}
	
	
	public void start(){
		setRunning(true);
		fromCentral.start();
		decreaseTimer.start();
	}
	
	public void stop(){
		setRunning(false);
		decreaseTimer.stop();
		fromCentral.stop();
	}
	
	
	public void insert(Food insert){
		if(insert.getAmount() > 0){
			insert.addObserver(new Sensor(8888));
			foods.add(insert);
		}else{
			System.err.println(insert.getName() + "'s amount " + insert.getAmount() + " is negativ and can not be inserted into the Freezer.");
		}
	}
	
	
	public void showFood(){
		System.out.println();
		//check if any element in freezer has > 0 amount
		boolean empty = true;
		for(int i = 0; i < foods.size(); i++){
			if(foods.elementAt(i).getAmount() > 0){
				empty = false;
				break;
			}
		}
		if(!empty){
			System.out.println("______________________________");
			System.out.println("Current Food List: ");
			for(int i = 0; i < foods.size(); i++){
				System.out.println(foods.elementAt(i).toString());
			}
			System.out.println("______________________________");
			System.out.println();
		}else{
			System.out.println("Freezer is empty.");
		}
	}
	
	
	public void decreaseFoodAmounts(int decreaseAmount){
		//decreases filled food
		for(int i = 0; i < foods.size(); i++){
			if(foods.elementAt(i).getAmount() > decreaseAmount){
				foods.elementAt(i).decreaseAmount(decreaseAmount);
			}else{
				foods.elementAt(i).setAmount(0);
			}
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		//for(int i = 0; i < 100; i++){		//Performance- / LastTest
			decreaseFoodAmounts(100);
		//}
	}
	
	
	public boolean isRunning(){
		return running;
	}
	
	public void setRunning(boolean running){
		this.running = running;
	}
	
	
	public Vector<Food> getFoods() {
		return foods;
	}

	public void setFoods(Vector<Food> foods) {
		this.foods = foods;
	}
	
	
	public UDPServer getFromCentral(){
		return fromCentral;
	}
	
	public void setUdpServer(UDPServer fromCentral){
		this.fromCentral = fromCentral;
	}
}
