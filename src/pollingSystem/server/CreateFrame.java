package src.pollingSystem.server;

import javax.swing.JFrame;

public class CreateFrame extends JFrame{
	
	private CreatePanel cPanel;
	
	public CreateFrame(Model model)
	{
		setSize(300,100);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cPanel=new CreatePanel(model);
		this.add(cPanel);
	}
	
	
}
