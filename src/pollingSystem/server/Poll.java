package src.pollingSystem.server;

import java.io.Serializable;
import java.util.*;

public class Poll implements Serializable{
	private String question;
	private List<String> options;
	private boolean paused;
	
	public Poll(String question,List<String> options)
	{
		this.question=question;
		this.options=new ArrayList<String>(options);
		paused=false;
	}
	
	public void pause()
	{
		paused=!(paused);
	}
	
	//displays a graph of the votes
	public void show()
	{
		
	}
	
	//prints the question and options on the console
	public void displayQuestion()
	{
		int number=1;
		System.out.println(question);
		System.out.println("Options");
		for(String option:options)
		{
			System.out.println(number+": "+option);
			number+=1;
		}
	}
	
}
