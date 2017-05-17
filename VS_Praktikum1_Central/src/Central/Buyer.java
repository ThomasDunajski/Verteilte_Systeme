package Central;

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
		
	}
}
