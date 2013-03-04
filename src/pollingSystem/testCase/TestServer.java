package src.pollingSystem.testCase;

import static org.junit.Assert.*;

import org.junit.Test;

import src.pollingSystem.server.Server;

public class TestServer {

	@Test
	public void testCreatePoll() {
		Server s = new Server();
		
		s.startAdminListener();
		
		//send message on port 5000
		
		assertTrue(s.numberOfPolls()==1);
	}

}
