package src.pollingSystem.server;

import javax.swing.*;

import java.util.*;

public class ServerGUI extends JFrame {
	
	private AdminPanel adminPanel;
	
	private Model model;
	
	public ServerGUI()
	{
		super("Admin Client");
		
		model=new Model();
		
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		adminPanel=new AdminPanel(model);
		
		this.add(adminPanel);
	}
	
	public static void main(String[] args) 
	{
		ServerGUI client = new ServerGUI();
   		client.setVisible(true);
	}
}
