package src.pollingSystem.server;

import java.util.Observable;
import java.io.*;
import java.net.*;

public class AdminListener extends Observable implements Runnable {
	
	public static final int PORT=5000;
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private BufferedReader in;

	   public AdminListener()
	   {
		   serverSocket = null;
	       try {
	           serverSocket = new ServerSocket(PORT);
	 
	       } catch (IOException e) {
	           e.printStackTrace(System.err);
	           System.exit(1);
	       }
	   }
	   
	   //recieves a message from the admin client
	   public String receive()
	   {
		   String msg="";
		   try {
		       clientSocket = serverSocket.accept();
		       in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		       //while (!clientSocket.isOutputShutdown())  // isClosed(), isConnected(),isInputShutdown do not work
			   msg = in.readLine(); 
			   
			   in.close();  
			   clientSocket.close();
			   serverSocket.close();
	      
		   } catch (SocketException e2) { System.out.println("Done"); System.exit(0); }
		   catch (IOException e) { e.printStackTrace(System.err); System.exit(1);  }
		   
		   return msg;
	   }   
	
	
	@Override
	public void run() {
		while(true)
		{
			this.notifyObservers(receive());
		}
		
	}
}
