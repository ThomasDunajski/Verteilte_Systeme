package RPC;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Main {

	public static ShopApi handler;

	public static Shop.Processor processor;

	public static void main(String[] args) {
		try {
			System.out.println("reading config file...");

			JSONParser parser = new JSONParser();

			String filePath = new File("").getAbsolutePath();
			filePath += "\\config.json";
			Object obj = parser.parse(new FileReader(filePath));

			JSONObject jsonObject = (JSONObject) obj;

			int port = new BigDecimal((long) jsonObject.get("port")).intValueExact();
			JSONArray products = (JSONArray) jsonObject.get("products");

			System.out.println("Port: " + port);
			Iterator<JSONObject> iterator = products.iterator();
			Food newFood;
			JSONObject data;
			Vector<Food> foods = new Vector<Food>();
			while (iterator.hasNext()) {
				data = iterator.next();
				double price = ((Number)data.get("price")).doubleValue();
				String name = (String) data.get("name");
				System.out.println(name + ":" + price );
				foods.add(new Food(price, name));
			}

			handler = new ShopApi(foods);
			processor = new Shop.Processor(handler);

			Runnable simple = new Runnable() {
				public void run() {
					simple(processor, port);
				}
			};

			new Thread(simple).start();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	public static void simple(Shop.Processor processor, int port) {
		try {
			TServerTransport serverTransport = new TServerSocket(port);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

			System.out.println("Starting the shop server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
