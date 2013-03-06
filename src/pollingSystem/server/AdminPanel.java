package src.pollingSystem.server;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class AdminPanel extends JPanel implements ActionListener{
	
	private AdminFrame frame;
	
	private JButton create;
	private JButton pause;
	private JButton end;
	
	private JTextField selectText;
	
	private JLabel selectLabel;
	
	private CreateFrame cFrame;
	
	private AdminClient client;
	
	public AdminPanel(AdminClient client)
	{
		super(new GridLayout(2,3));
		
		this.client=client;
		
		create=new JButton("create poll");
		pause=new JButton("pause poll");
		end=new JButton("end poll");
		
		selectLabel=new JLabel("Poll #");
		
		selectText=new JTextField("");
		
		add(create);
		add(pause);
		add(end);
		
		add(selectLabel);
		add(selectText);
		
		create.addActionListener(this);
		pause.addActionListener(this);
		end.addActionListener(this);
	}
		
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
	    int pollNumber;
	    
		if (source == create)
	    {
	    	cFrame=new CreateFrame(client);
	    	cFrame.setVisible(true);
	    }
		else
		{
			pollNumber=Integer.parseInt(selectText.getText());
		
		    if (source == pause)
		    {
		    	//send a pause message to the server
		    	client.sendPause(pollNumber);
		    }
		    if (source == end)
		    {
		    	//send an end message to the server
		    	client.sendEnd(pollNumber);
		    }
		}
	}
}
