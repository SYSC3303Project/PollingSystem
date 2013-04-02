package pollingSystem.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pollingSystem.client.AdminClient;

import pollingSystem.server.Poll;

public class CreatePanel extends JPanel implements ActionListener{
	
	private JLabel questionLabel;
	private JLabel optionLabel;
	
	private JTextField questionText;
	private JTextField optionText;
	
	private JButton addOption;
	private JButton enter;
	
	private List<String> options;
	
	private AdminClient client;
	
	public CreatePanel (AdminClient client)
	{
		super(new GridLayout(3,2));
		
		this.client=client;
		
		questionLabel=new JLabel("Question");
		optionLabel=new JLabel("Option");
		
		questionText=new JTextField("");
		optionText=new JTextField("");
		
		addOption=new JButton("Add Option");
		enter=new JButton("Enter");
		
		addOption.addActionListener(this);
		enter.addActionListener(this);
		
		options=new ArrayList<String>();
		
		add(questionLabel);
		add(questionText);
		add(optionLabel);
		add(optionText);
		add(addOption);
		add(enter);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String question;
		Poll poll;
		Object source = event.getSource();
	    if (source == enter)
	    {
	    	question=questionText.getText();
	    	
	    	//there must be a question and at least 1 option
	    	if(question!="" && !(options.isEmpty()))
	    	{
	    		poll=new Poll(question,options);
	    		//send poll object to the server
	    		client.sendPoll(poll);
	    		
	    		
	    	}
	    }
	    if (source == addOption)
	    {
	    	options.add(optionText.getText());
	    	optionText.setText("");
	    }
		
	}

}
