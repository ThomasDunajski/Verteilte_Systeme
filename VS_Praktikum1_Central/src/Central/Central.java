package Central;

import RPC.Buyer;

public class Central {

	private boolean running = false;
	private History history;
	private Buyer buyer;
	private UDPServer udpServer;
	
	public Central(){
		history = new History();
		buyer = new Buyer(1111, 500.00, history);
		udpServer = new UDPServer(8888, history, buyer);
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
