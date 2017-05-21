package Central;

import java.util.Vector;

public class History {

	private Vector<String> history;
	
	public History(){
		history = new Vector<String>();
	}
	
	
	public void insert(String food){
		String[] temp = food.split(" ");
		try{
			int amount = Integer.parseInt(temp[1]);
			if(amount >= 0){
				history.add(food);
			}else{
				temp[0] += " 0";
				history.add(temp[0]);
				System.err.println("Can not save a negative amount for food in history. Saved 0 instead.");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.err.println("Wrong data format to write something into history");
		}
	}
	
	public void clear(){
		history.clear();
	}
	
	
	public void print(int element){
		if(element >= 0 && element < history.size()){
			System.out.print(element + ". Time: \t");
			System.out.println(history.elementAt(element).toString());
		}else{
			System.err.println(element + " must be between 0 and " + history.size());
		}
	}
	
	public void printAll(){
		System.out.println();
		if(history.size() > 0){
			System.out.println("History: ");
			for(int i = 0; i < history.size(); i++){
				print(i);
			}
		}else{
			System.out.println("History is empty");
		}

	}
	
	public void printLast(){
		print(history.size() - 1);
	}
		
	
	public Vector<String> getHistory(){
		return history;
	}
	
	public void setHistory(Vector<String> history){
		this.history = history;
	}
}
