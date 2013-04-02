package pollingSystem.testCase;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 * Voter allows voter messages to be sent without use of a voter client GUI.
 * @author Andrew
 *
 */
public class Voter implements Runnable{
	long pollID ; 
	long voteID;
	int port;
	String server;
	
	String vote;
	
	public Voter(long pollID,long voteID,int port, String server)
	{
		
			this.pollID = pollID;
			this.voteID = voteID;
			this.port = port;
			this.server=server;
	}
	@Override	
	public void run(){
		
			try
			{
				String messageToSend = pollID+" "+voteID;
			
				DatagramPacket sendPacket = new DatagramPacket(messageToSend.getBytes(), 
										messageToSend.length(),InetAddress.getByName(server),port);
				DatagramSocket sendSocket = new DatagramSocket();
			
				sendSocket.send(sendPacket);
				System.out.println("Data: "+sendPacket.getData()+"\nSocketAddress: "+sendPacket.getSocketAddress());
			
				sendSocket.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}	
	}	
}
