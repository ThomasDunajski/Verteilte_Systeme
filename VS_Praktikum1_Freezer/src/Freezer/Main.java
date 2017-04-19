package Freezer;

import java.util.Scanner;

public abstract class Main {
	
	private static ThreadPool threadPool;	//for multithreaded application
	
	
	public static void main(String[] args) {
		setThreadPool(new ThreadPool(Runtime.getRuntime().availableProcessors()));
		
		Freezer freezer = new Freezer(12345);
		fillFreezer(freezer);
		freezer.start();
		
		handleInput(freezer);
	}
	
	
	private static void fillFreezer(Freezer freezer){
		freezer.insert(new Food("Milch", 1000, 1000, 200));
		freezer.insert(new Food("Käse", 600, 600, 150));
		freezer.insert(new Food("Salami", 300, 500, 200));
		freezer.insert(new Food("Butter", 500, 500, 300));
	}
	
	
	private static void handleInput(Freezer freezer){
		Scanner scanner = new Scanner(System.in);
		while(freezer.isRunning()){
			String nextLine = scanner.nextLine();
			if(nextLine.equals("stop")){
				freezer.stop();
				System.out.println("Freezer stopped.");
				System.exit(0);		//ending program
			}else if(nextLine.equals("show food")){
				freezer.showFood();
			}else if (nextLine.equals("insert negative amount")){
				Food negativ = new Food("Schinken", -150, 400, 300);
				freezer.insert(negativ);
			}else if(nextLine.equals("freezer into historie")){
				Client client = new Client(8888);
				System.out.println("Sending data from freezer to history...");
				for(int i = 0; i < freezer.getFoods().size(); i++){
					client.sendData(freezer.getFoods().elementAt(i).toString());
				}
			}else if(nextLine.equals("negativ food into history")){
				Client client = new Client(8888);
				Food food = new Food("Eier", -3, 6, 4);
				client.sendData(food.toString());
			}
		}
		scanner.close();		
	}
	
	
	public static ThreadPool getThreadPool(){
		return threadPool;
	}
	
	public static void setThreadPool(ThreadPool ThreadPool){
		threadPool = ThreadPool;
	}
	
}
