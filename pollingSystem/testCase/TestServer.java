package pollingSystem.testCase;

import java.util.ArrayList;
import java.util.List;

import pollingSystem.client.AdminClient;
import pollingSystem.server.AdminListener;
import pollingSystem.server.Poll;
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
	AdminClient admin;
	
	public TestServer()
	{
		voters=new ArrayList<Thread>();
	}
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
	
	public void setUpAdmin(String server,int port)
	{
		admin=new AdminClient(server,port);
	}
	
	/**
	 * Sends a create poll message to the admins server.
	 * @param question
	 * @param options
	 */
	public void createPoll(String question,List<String> options){
		Poll poll=new Poll(question, options);
		admin.sendPoll(poll);
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
	
	/**
	 * Creates n copies of random voters using the passed pollID, port and server.
	 * @param voterNumber
	 * @param pollID
	 * @param voteID
	 * @param port
	 * @param server
	 */
	public void createNRandomVoters(int numberOfVoters,long pollID,int options,int port, String server)
	{
		for(int i=0;i<numberOfVoters;i++)
		{
			voters.add(new Thread(new Voter(pollID,port,server,options)));
		}
		for(Thread currentThread:voters)
		{
			currentThread.start();
		}
	}
	
	/**
	 * Sets up the server, makes a poll with 4 options then makes 100 voters vote randomly on it.
	 */
	public void basicTest() {
		setUpServer();
		setUpAdmin("localhost",5050);
		
		List<String> options=new ArrayList<String>();
		options.add("first");
		options.add("second");
		options.add("third");
		options.add("fourth");
		createPoll("Test Question",options);
		
		createNRandomVoters(100,1,4,1500,"localhost");
		
	}
	
	public static void main(String[] args) {
		TestServer test=new TestServer();
		test.basicTest();
	}
	
}
