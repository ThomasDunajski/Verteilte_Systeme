package Central;

public abstract class Connectable {

	protected int port = 1234;
	protected String line = "";
	
	
	public Connectable(int port){
		setPort(port);
	}
	
	
	public int getPort(){
		return port;
	}
	
	public void setPort(int port){
		this.port = port;
	}
	
	
	public String getLine(){
		return line;
	}
	
	public void setLine(String line){
		this.line = line;
	}
	
}
