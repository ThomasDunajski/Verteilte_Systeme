package Central;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server extends Connectable{

	protected boolean running = false;
	protected ServerSocket serverSocket;
	protected DataInputStream fromClient;
	protected DataOutputStream toClient;
	
	
	public Server(int port){
		super(port);
		try{
			serverSocket = new ServerSocket(port);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
	public void start(){
		setRunning(true);
		
		Main.getThreadPool().insertTask(new Runnable(){		//Multithreading?
			public void run(){
				try {
					while (running) {
						if(serverSocket != null){
							Socket client = serverSocket.accept(); 		// create communication socket
							//System.out.println("Connected with: " + client.getRemoteSocketAddress());
							handleRequests(client);						// Handle connection request
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.err.println("Can not connect to a Client");
				}	
			}
		});
	}
	
	public void stop(){
		setRunning(false);
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean receiveRequest() {
		boolean holdTheLine = true;
		try {
			line = fromClient.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return holdTheLine;
	}
	
	
	public void handleRequests(Socket socket) {
		try {
			fromClient = new DataInputStream(socket.getInputStream());		// Datastream FROM Client
			toClient = new DataOutputStream(socket.getOutputStream());      // Datastream TO Client
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public boolean isRunning() {
		return running;
	}

	private void setRunning(boolean running) {
		this.running = running;
	}
	
}
