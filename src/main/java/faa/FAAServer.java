package faa;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class FAAServer {

	public static void main(String args[]) throws IOException {
		
		      int port = 8899;
		      ServerSocket server = new ServerSocket(port);

		      while(true){
			      Socket socket = server.accept();
			      Reader reader = new InputStreamReader(socket.getInputStream());
			      char chars[] = new char[64];
			      int len;
			      StringBuilder sb = new StringBuilder();
			      while ((len=reader.read(chars)) != -1) {
			    	  sb.append(new String(chars, 0, len));
			      }
			      System.out.println("from client: " + sb);
		      }
		      
		   }
}
