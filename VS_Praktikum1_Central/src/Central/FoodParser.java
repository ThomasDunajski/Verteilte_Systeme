package Central;

import java.util.Vector;

public class FoodParser {

	public static String parseFoodsToString(Vector<String> foods) {

		Vector<String> currentFood = filterFoods(foods);

		String response = "";
		for (int i = 0; i < currentFood.size(); i++) {
			response += currentFood.elementAt(i) + "\n";
		}

		return response;
	}
	
	public static Vector<Food> parseFoods(Vector<String> foods) {

		Vector<String> currentFood = filterFoods(foods);


		Vector<Food> response = new Vector<Food>();
		for (int i = 0; i < currentFood.size(); i++) {
			String[] temp = currentFood.elementAt(i).split(" ");
			response.add(new Food(temp[0], Integer.parseInt(temp[1]), 1000, 200));
		}

		return response;
	}
	
	public static Food parseFood(String foodString) {
		
		String[] temp = foodString.split(": ");
		return new Food(temp[0], Integer.parseInt(temp[1]), 1000, 200);
	}
	
	public static Vector<String> filterFoods(Vector<String> foods) {
	
		Vector<String> currentFood = new Vector<String>(); // toPrint

		for (int j = 0; j < foods.size(); j++) {
			String[] temp2 = foods.elementAt(j).split(" ");
			String name = temp2[0];
			String last = "";
			for (int i = 0; i < foods.size(); i++) {
				if (foods.elementAt(i).contains(name)) {
					last = foods.remove(i);
				}
			}
			currentFood.add(last);
		}
		return currentFood;
	}
}
