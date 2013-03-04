package pollingSystem.server;

import java.util.*;


//a Model models the state of the polling system
//mostly a model has a list of polls
public class Model {
	private List<Poll> polls;
	
	public Model()
	{
		polls=new ArrayList<Poll>();
	}
	
	public void addPoll(Poll poll)
	{
		polls.add(poll);
	}
	
	public Poll getPoll(int index)
	{
		return polls.get(index);
	}
}
