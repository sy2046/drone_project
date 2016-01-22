package communicator;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class FAAClient {

	   public void post(String msg) throws Exception{
		   String host = "127.0.0.1";  // IP
		   int port = 8899;   // Port
		   Socket client;
		   client = new Socket(host, port); 
		   // Build connection with host server

		   Writer writer;
		   writer = new OutputStreamWriter(client.getOutputStream());
		   writer.write(msg);// write message
		   writer.flush();
		   writer.close();
		   client.close();
	   }
	   
	   public static void main(String args[]){
		   FAAClient client = new FAAClient();
		   try {
			client.post("The drone takes off");
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
	   }
	   
}
