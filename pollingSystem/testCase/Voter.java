package pollingSystem.testCase;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
/**
 * Voter allows voter messages to be sent without use of a voter client GUI.
 * @author Andrew
 *
 */
public class Voter implements Runnable{
	private long pollID ; 
	private long voteID;
	private int port;
	private String server;
	
	/**
	 * Creates a voter that votes the given votID
	 * @param pollID
	 * @param voteID
	 * @param port
	 * @param server
	 */
	public Voter(long pollID,long voteID,int port, String server)
	{
		this.pollID = pollID;
		this.voteID = voteID;
		this.port = port;
		this.server=server;
	}
	
	/**
	 * Creates a voter that votes a random voteID
	 * @param pollID
	 * @param port
	 * @param server
	 * @param options
	 */
	public Voter(long pollID,int port, String server,int options)
	{
		this.pollID = pollID;
		this.port = port;
		this.server=server;
		
		Random random=new Random();
		int vote=((Math.abs(random.nextInt()))%options)+1;//random int from 1 to OPTIONS
		voteID=new Long(vote).longValue();
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
				//System.out.println("Data: "+sendPacket.getData()+"\nSocketAddress: "+sendPacket.getSocketAddress());
			
				sendSocket.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}	
	}	
}
