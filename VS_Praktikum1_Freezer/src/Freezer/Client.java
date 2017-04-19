package Freezer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client extends Connectable{

	protected Socket socket;
	protected DataInputStream fromServer;
	protected DataOutputStream toServer;
	
	public Client(int port){
		super(port);
	}
	
	public void sendData(String data){
		try {
			//Creating connection
			socket = new Socket("localhost", port);
			fromServer = new DataInputStream(socket.getInputStream());	// Datastream FROM Server
			toServer = new DataOutputStream(socket.getOutputStream());	// Datastream TO Server
			
			//sending data
			line = data;
			toServer.writeBytes(line + "\n");
			
			//ending connection
			socket.close();
			toServer.close();
			fromServer.close();
		} catch (Exception ex) {
			System.err.println("Can not send data to port: " + port);
		}	
	}
	
}
