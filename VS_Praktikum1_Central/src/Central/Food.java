package Central;

import java.util.Observable;

public class Food extends Observable{

	protected String name = "";
	protected int amount = 0;
	protected int optimalAmount = 0;
	protected int whenToBuy = 0;
	
	
	public Food(String name, int amount, int optimalAmount, int whenToBuy){		
		setName(name);
		setAmount(amount);
		setOptimalAmount(optimalAmount);
		setWhenToBuy(whenToBuy);
	}

	public void increaseAmount(int increase){
		setAmount(amount + increase);
	}
	
	public void decreaseAmount(int decrease){
		setAmount(amount - decrease);
	}
	
	
	@Override
	public String toString(){
		return name + " " + amount;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Food)){
			return false;
		}
		if(o == this){
			return true;
		}
		Food food = (Food) o;
		return food.getName() == name
				&& food.getAmount() == amount
				&& food.getOptimalAmount() == optimalAmount
				&& food.getWhenToBuy() == whenToBuy;
		
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		if(this.amount != amount){	//so unchanged amount will not be observed and set in history
			this.amount = amount;
			if(countObservers() > 0){ 
	            setChanged(); 
	            notifyObservers(amount);
	        }
		}
	}

	
	public int getOptimalAmount() {
		return optimalAmount;
	}

	public void setOptimalAmount(int optimalAmount) {
		this.optimalAmount = optimalAmount;
	}


	public int getWhenToBuy() {
		return whenToBuy;
	}

	public void setWhenToBuy(int whenToBuy) {
		this.whenToBuy = whenToBuy;
	}
	
}
