package Central;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool{

	private ExecutorService es;
	
	public ThreadPool(int maxThreads) {
		es = Executors.newFixedThreadPool(maxThreads);
	}
	
	
	public void insertTask(Runnable task){
		es.execute(task);
	}
}
