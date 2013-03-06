package src.pollingSystem.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


import src.pollingSystem.PollingMessage;
import src.pollingSystem.server.Poll;

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
	
	//sends a pause message to pause a poll
	public void sendPause(int pollNumber)
	{
		PollingMessage message=new PollingMessage("Pause$"+pollNumber);
		try {
	    	  out.writeObject(message);
	      } catch (IOException e) { 
	    	  System.err.println("Couldn't get I/O for the connection");
	    	  System.exit(1);
	      }
	}
	
	//sends a end message to pause a poll
	public void sendEnd(int pollNumber)
	{
		PollingMessage message=new PollingMessage("End$"+pollNumber);
		try {
	    	  out.writeObject(message);
	      } catch (IOException e) { 
	    	  System.err.println("Couldn't get I/O for the connection");
	    	  System.exit(1);
	      }
	}
	
	
	//sends a create message with the information about a created poll
	public void sendPoll(Poll poll) {
		
		// send the poll information to the server
		// "$" Separates questions and options
		//poll formated as "Create:question:option1:option2:option3"
		StringBuffer buff=new StringBuffer("Create$"+poll.getQuestion());
		for(String s:poll.getOptions())
		{
			buff.append("$"+s);
		}
		
		PollingMessage message=new PollingMessage(buff.toString());
		
		
		try {
	    	  out.writeObject(message);
	      } catch (IOException e) { 
	    	  System.err.println("Couldn't get I/O for the connection");
	    	  System.exit(1);
	      }
	}
	
	
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AdminClient client = new AdminClient();
   	}
}