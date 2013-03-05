package src.pollingSystem.server;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class AdminPanel extends JPanel implements ActionListener{
	
	private ServerGUI frame;
	
	private JButton create;
	private JButton show;
	private JButton pause;
	private JButton end;
	
	private JTextField selectText;
	
	private JLabel selectLabel;
	
	private CreateFrame cFrame;
	
	private Model model;
	
	public AdminPanel(Model model)
	{
		super(new GridLayout(3,2));
		
		this.model=model;
		
		create=new JButton("create poll");
		show=new JButton("show poll");
		pause=new JButton("pause poll");
		end=new JButton("end poll");
		
		selectLabel=new JLabel("Poll #");
		
		selectText=new JTextField("");
		
		add(create);
		add(show);
		add(pause);
		add(end);
		
		add(selectLabel);
		add(selectText);
		
		create.addActionListener(this);
		show.addActionListener(this);
		pause.addActionListener(this);
		end.addActionListener(this);
	}
		
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
	    int pollNumber;
	    
		if (source == create)
	    {
	    	cFrame=new CreateFrame(model);
	    	cFrame.setVisible(true);
	    }
		
			
		
		    if (source == show)
		    {
		    	pollNumber=Integer.parseInt(selectText.getText());
		    	model.getPoll(pollNumber).show();
		    }
		    if (source == pause)
		    {
		    	pollNumber=Integer.parseInt(selectText.getText());
		    	model.getPoll(pollNumber).pause();
		    }
		    if (source == end)
		    {
		    }
		    else{
		    	//do nothing if the event is not one of the listed buttons
		    }
	}
}
