package pollingSystem.testCase;

import java.util.List;

import org.junit.Test;

import pollingSystem.server.AdminListener;
import pollingSystem.server.Server;
import pollingSystem.server.VoteListener;
import pollingSystem.server.VoteObserver;

/**
 * Test server has several methods for use in testing such as setting up a server and
 * creating multiple voters that vote on that server.
 * @author Andrew
 *
 */
public class TestServer {
	Server server;
	List<Thread> voters;
	
	/**
	 * Creates a server and all of it's listener threads. Starts the appropriate threads.
	 */
	public void setUpServer()
	{
		Server serv = new Server();

		VoteListener voteListener = new VoteListener();
		voteListener.addObserver(serv);
		
		AdminListener adminListener = new AdminListener();
		adminListener.addObserver(serv);
		
		
		VoteObserver voteObserver = new VoteObserver();
		
		serv.addObserver(voteObserver);

		Thread voteListenThread = new Thread(voteListener);
		voteListenThread.start();
		
		Thread adminListenThread = new Thread(adminListener);
		adminListenThread.start();
	}
	/**
	 * Creates n copies of identical voters using the passed pollID,voteID port and server.
	 * @param voterNumber
	 * @param pollID
	 * @param voteID
	 * @param port
	 * @param server
	 */
	public void createNSameVoters(int voterNumber,long pollID,long voteID,int port, String server)
	{
		for(int i=0;i<voterNumber;i++)
		{
			voters.add(new Thread(new Voter(pollID,voteID,port,server)));
		}
		for(Thread currentThread:voters)
		{
			currentThread.start();
		}
	}
	
	@Test
	public void testCreatePoll() {
		
	}

}
