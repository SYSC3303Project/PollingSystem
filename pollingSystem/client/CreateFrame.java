package pollingSystem.client;

import javax.swing.JFrame;

import pollingSystem.client.AdminClient;

public class CreateFrame extends JFrame{
	
	private CreatePanel cPanel;
	
	public CreateFrame(AdminClient client)
	{
		super("Create a Poll");
		
		setSize(300,100);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cPanel=new CreatePanel(client);
		this.add(cPanel);
	}
	
	
}
