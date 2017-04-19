package Freezer;

import java.net.Socket;

public class TCPServer extends Server{

	private String endSession = ".";
	
	public TCPServer(int port) {
		super(port);
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
  				sendResponse();
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
  	public boolean receiveRequest(){
  		boolean holdTheLine = super.receiveRequest();
  		if (line == null || line.equals(endSession)) {     // ends session when getting any of this strings
			holdTheLine = false;
		}else{
			System.out.println("Package received: \t" + line);
		}
  		return holdTheLine;
  	}

  	
  	public void sendResponse() {
  		try {
			toClient.writeBytes(line + "\n");	// sends answer
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
  	}
}
