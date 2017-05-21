package Freezer;

import java.net.Socket;

public class UDPServer extends Server{

	private Freezer freezer;	//just a reference
	
	public UDPServer(int port, Freezer freezer){
		super(port);
		this.freezer = freezer;
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
  				Food food = FoodParser.parseFood(line);
  				boolean found = false;
  				for(int i = 0; i < freezer.getFoods().size(); i++){
  					if(food.getName().equals(freezer.getFoods().elementAt(i).getName())){
  						Food current = freezer.getFoods().elementAt(i);
  						int before = current.getAmount();
  						current.increaseAmount(food.getAmount());
  						System.out.println(current.getName() + " is filled up from " + before + " to " + current.getAmount());
  						found = true;
  						break;
  					}
  				}
  				if(!found){		//not yet registered
  					freezer.insert(food);
  				}
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
