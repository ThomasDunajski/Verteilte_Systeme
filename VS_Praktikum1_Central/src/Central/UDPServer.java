package Central;

import java.net.Socket;

import RPC.Buyer;

public class UDPServer extends Server{

	private History history;	//just a reference
	private Buyer buyer;		//just a reference
	
	public UDPServer(int port, History history, Buyer buyer){
		super(port);
		this.history = history;
		this.buyer = buyer;
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
  				buyer.checkAndBuy(line);
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
