package src.pollingSystem.server;

import java.util.*;

public class Poll {
	private String question;
	private List<String> options;
	private boolean paused;
	private ArrayList<Integer> resultCount;
	private String pollID;
	public Poll(String question,List<String> options)
	{
		this.question=question;
		this.options=new ArrayList<String>(options);
		resultCount = new ArrayList<Integer>();
		for(String option : options){
			resultCount.add(0);
		}
		paused=false;
	}
	public void pause()
	{
		paused=!(paused);
	}

	/**
	 * prints the question and options on the console
	 */
	public void print()
	{
		int number=1;
		System.out.println(question);
		System.out.println("Options");
		for(String option:options)
		{
			System.out.println(number+": "+option);
			System.out.println("Count: " + resultCount.get(0));
			number+=1;
		}
	}
	/**
	 * adds 1 to the result count of the option number passed as a parameter
	 * @param option - number option being voted for
	 */
	public void vote(int option){
		resultCount.set(option, resultCount.get(option) + 1);
	}
	/**
	 * decrements 1 to the result count of the option number passed as a parameter
	 * @param option - number option being voted for
	 */
	void unVote(int option){
		resultCount.set(option, resultCount.get(option) - 1);
	}
	
	public String getQuestion()
	{
		return question;
	}

	public List<String> getOptions()
	{
		return options;
	}

	/**
	 * @return the pollID
	 */
	public String getPollID() {
		return pollID;
	}

	/**
	 * @param pollID the pollID to set
	 */
	public void setPollID(String pollID) {
		this.pollID = pollID;
	}

}