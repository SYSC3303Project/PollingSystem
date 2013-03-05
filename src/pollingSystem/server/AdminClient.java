package src.pollingSystem.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import src.pollingSystem.PollingMessage;

public class AdminClient {
	
	public static String SERVER="127.0.0.1";
	public static final int PORT=5000;
	
	
	private Socket pollSocket;
	private ObjectOutputStream out;
	
	private AdminFrame adminGUI;
	
	public AdminClient()
	{
		adminGUI=new AdminFrame(this);
		adminGUI.setVisible(true);
		
		try {
			pollSocket=new Socket(SERVER,PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			out = new ObjectOutputStream(pollSocket.getOutputStream());
	    } catch (IOException e2) {
	       System.err.println("Couldn't get I/O connection");
	       System.exit(1);
	    }
	}
	
	public void close()
	{
		try {
			   out.close();
			   pollSocket.close();   
		} catch (IOException e) { 
			   System.err.println("Couldn't get I/O for the connection");
	           System.exit(1);
		}	   
	}
	
	//sends a poll
	public void sendPoll(Poll poll) {
		PollingMessage message=new PollingMessage("Pause");
		
		
		try {
	    	  out.writeObject(message);
	      } catch (IOException e) { 
	    	  System.err.println("Couldn't get I/O for the connection");
	    	  System.exit(1);
	      }
	}
	
	
	
	public static void main(String[] args) 
	{
		AdminClient client = new AdminClient();
   	}
	
	
}