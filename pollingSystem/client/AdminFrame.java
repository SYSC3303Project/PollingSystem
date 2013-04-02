package pollingSystem.client;

import javax.swing.*;

import pollingSystem.client.AdminClient;

public class AdminFrame extends JFrame {
	
	private AdminPanel adminPanel;
	
	
	
	public AdminFrame(AdminClient client)
	{
		super("Admin Client");
		
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		adminPanel=new AdminPanel(client);
		
		this.add(adminPanel);
	}
	
	/*
	public static void main(String[] args) 
	{
		AdminFrame client = new AdminFrame();
   		client.setVisible(true);
	}
	*/
}
