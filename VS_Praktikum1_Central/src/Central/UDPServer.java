package Central;

import java.net.Socket;

public class UDPServer extends Server{

	private History history;
	private Buyer buyer;
	
	public UDPServer(int port, History history){
		super(port);
		this.history = history;
		this.buyer = new Buyer(500, history);
	}
	
	@Override
	public void start(){
		super.start();
		
	}
	
	@Override
	public void stop(){
		super.stop();
	}
	
	
	@Override
  	public void handleRequests(Socket socket) {
  		super.handleRequests(socket);
  		try{
  			while (receiveRequest()) {
  				history.insert(line);
  				buyer.check(line);
  			}
  			fromClient.close();
  			toClient.close();
  			socket.close();
  		}catch(Exception ex){
  			ex.printStackTrace();
  		}
  		//System.out.println("Session ended, Server remains active");
  	}
	
	
	@Override
	public boolean receiveRequest() {
  		boolean holdTheLine = super.receiveRequest();
  		if (line == null) {     // ends session when getting
			holdTheLine = false;
		}else{
			System.out.println("Package received: \t" + line);
		}
  		return holdTheLine;
	}
	
	
	
	
}
