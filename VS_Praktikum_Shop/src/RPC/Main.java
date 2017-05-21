package RPC;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Main {

	  private static ShopApi handler;
	  private static Shop.Processor<ShopApi> processor;

	  
	  public static void main(String [] args) {
	    try {
	      handler = new ShopApi();
	      processor = new Shop.Processor<ShopApi>(handler);

	      Runnable simple = new Runnable() {
	        public void run() {
	          simple(processor);
	        }
	      };      
	     
	      new Thread(simple).start();
	    } catch (Exception x) {
	      x.printStackTrace();
	    }
	  }

	  public static void simple(Shop.Processor<ShopApi> processor) {
	    try {
	      TServerTransport serverTransport = new TServerSocket(9090);
	      TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

	      System.out.println("Starting the shop-server...");
	      server.serve();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	}

}
