package Central;

public class Central {

	private boolean running = false;
	private History history;
	private UDPServer udpServer;
	
	public Central(){
		history = new History();
		udpServer = new UDPServer(8888, history);
	}
	
	
	public void start(){
		setRunning(true);
		udpServer.start();
	}

	public void stop(){
		setRunning(false);
		udpServer.stop();
	}	

	public boolean isRunning() {
		return running;
	}

	private void setRunning(boolean running) {
		this.running = running;
	}
	
	
	public History getHistory(){
		return history;
	}
	
	public void setHistory(History history){
		this.history = history;
	}
	
}
