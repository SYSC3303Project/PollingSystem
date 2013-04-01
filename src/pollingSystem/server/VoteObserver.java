package src.pollingSystem.server;

import java.util.Observable;
import java.util.Observer;

public class VoteObserver implements Runnable, Observer {


	public VoteObserver() {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("In Vote Observer Update");
		if(arg1.getClass().equals(Server.class)) {
			System.out.println("Server class sent me");
			Server server = (Server) arg1;
			server.printPolls();
			System.out.println("Server class printed polls");
		}
	}

}
