package pollingSystem.textCase;

import static org.junit.Assert.*;

import org.junit.Test;

import pollingSystem.server.Server;

public class TestServer {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCreatePoll() {
		Server s = new Server();
		
		s.startAdminListener();
		
		//send message on port 5000
		
		assertTrue(s.numberOfPolls()==1);
	}

}
