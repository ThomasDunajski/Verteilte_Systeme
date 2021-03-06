package Central;

import java.io.IOException;
import java.util.Scanner;

import Web.Webserver;

public abstract class Main {

	private static ThreadPool threadPool;	//for multithreaded application
	
	
	public static void main(String[] args) throws IOException {
		setThreadPool(new ThreadPool(Runtime.getRuntime().availableProcessors()));
		
		Central central = new Central();
		central.start();
		
		Webserver webserver = new Webserver();
		webserver.start(central.getHistory());
		
		handleInput(central);
	}

	
	private static void handleInput(Central central){
		Scanner scanner = new Scanner(System.in);
		while(central.isRunning()){
			String nextLine = scanner.nextLine();
			if(nextLine.equals("stop")){
				central.stop();
				System.out.println("Central stopped.");
				central.getHistory().printAll();
				System.exit(0);		//ending program
			}else if(nextLine.equals("show history")){
				central.getHistory().printAll();
			}else{
				//do something...
			}
		}
		scanner.close();		
	}
	

	public static ThreadPool getThreadPool() {
		return threadPool;
	}

	public static void setThreadPool(ThreadPool ThreadPool) {
		threadPool = ThreadPool;
	}
}
