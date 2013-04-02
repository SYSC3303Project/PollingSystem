package pollingSystem.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


import pollingSystem.PollingMessage;
import pollingSystem.server.Poll;

public class AdminClient {
	
	public static String SERVER="localhost";
	public static final int PORT=5050;
	
	
	private Socket pollSocket;
	private ObjectOutputStream out;
	
	public AdminClient(String server,int port)
	{
		
		try {
			pollSocket=new Socket(server,port);
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
			   System.err.println("Couldn't get I/O for the connection close");
	           System.exit(1);
		}
	}
	
	//sends a pause message to pause a poll
	public void sendPause(int pollID)
	{
		PollingMessage message=new PollingMessage(pollID+"$Pause");
		//System.out.println("send pause");
		try {
			out.writeObject(message);
	      } catch (IOException e) { 
	    	  System.err.println("Couldn't get I/O for the connection pause");
	    	  System.exit(1);
	      }
	}
	
	//sends a end message to pause a poll
	public void sendEnd(int pollID)
	{
		PollingMessage message=new PollingMessage(pollID+"$End");
		try {
	    	  out.writeObject(message);
	      } catch (IOException e) { 
	    	  System.err.println("Couldn't get I/O for the connection end");
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
	    	  System.err.println("Couldn't get I/O for the connection sendpoll");
	    	  System.exit(1);
	      }
	}
	
	
	
	public static void main(String[] args) {
		AdminClient client = new AdminClient(SERVER,PORT);
		AdminFrame adminGUI=new AdminFrame(client);
		adminGUI.setVisible(true);
   	}
}