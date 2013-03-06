
/**
 * Voter Client
 * This Lab will print out a message every two seconds for one minute
 * @author Matthew Smith - 100 827 363
 * @version 01/17/2013
 */
package src.pollingSystem.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class VoterClient extends JFrame implements ActionListener{
	
	/**Default port for UDP connection*/
	public static final int DEFAULT_PORT = 1500;
	/**Default Server for UDP connetion*/
	public static final String DEFAULT_SERVER = "localhost";
	/**The port number to listen for connection.*/
	private int port;
	/**Server for writing to the socket*/
	private DatagramSocket udpSocket;
	
	/**Will store the server address for the udp connection*/
	private JTextField serverTextField;
	/**The label for the server text field*/
	private static final String SERVER_LABEL = "Server";
	/**Store the port for the udp connection*/
	private JTextField portTextField;
	/**The label for the port text field*/
	private static final String PORT_LABEL = "Port";
	
	/**Generated Serial ID*/
	private static final long serialVersionUID = -5817022285820394734L;
	private JTextField pollTextField;
	private static final String POLL_LABEL = "PollID";
	private JTextField voteTextField;
	private static final String VOTE_LABEL = "VoteID";
	
	public VoterClient() {
		super("Voter Client");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel clientPanel = new JPanel(new GridLayout(2,1));
		
		clientPanel.add(networkPanel());
		clientPanel.add(votePanel());
		
		this.add(clientPanel);
		//this.pack();
		this.setBounds(100, 100, 400, 300);
	}
	
	/**
	 * Helper method sets up the Network Panel which contains 
	 * the fields for connection information to the server
	 * @return JPanel with Server Input Fields
	 */
	private JPanel networkPanel() {
		JPanel networkPanel = new JPanel(new GridLayout(2,1));
		
		serverTextField = new JTextField(DEFAULT_SERVER);
		JPanel serverPanel = labeledInputField(SERVER_LABEL, serverTextField);
		
		portTextField = new JTextField(""+DEFAULT_PORT);
		JPanel portPanel = labeledInputField(PORT_LABEL, portTextField);
		
		networkPanel.add(serverPanel);
		networkPanel.add(portPanel);
		networkPanel.setBorder(BorderFactory.createTitledBorder("Network Information"));
		
		return networkPanel;
	}
	
	/**
	 * Helper Method sets up the Vote Panel which contains the fields for what 
	 * is being voted on and the vote cast.
	 * @return JPanel with Vote Fields
	 */
	private JPanel votePanel() {
		JPanel votePanel = new JPanel(new GridLayout(3,1));
		

		pollTextField = new JTextField();
		JPanel pollField = labeledInputField(POLL_LABEL, pollTextField);
		voteTextField = new JTextField();
		JPanel voteField = labeledInputField(VOTE_LABEL, voteTextField);
		
		
		JButton submitButton = new JButton("Submit Vote");
		submitButton.addActionListener(this);
		
		votePanel.add(pollField);
		votePanel.add(voteField);
		votePanel.add(submitButton);
		votePanel.setBorder(BorderFactory.createTitledBorder("Vote Information"));
		
		return votePanel;
	}
	
	/**
	 * helper method initializes a Panel with a text label and the passed text field.
	 * @param label The Label for the field
	 * @param inputField The field for input
	 * @return JPanel containing a label and a text field
	 */
	private JPanel labeledInputField(String label, JTextField inputField) {
		JPanel inputPanel = new JPanel(new GridLayout(1, 2));
		inputPanel.setToolTipText(label);
		
		JLabel inputLabel = new JLabel(label+": ");
		
		inputField.setToolTipText(label);
		
		inputPanel.add(inputLabel);
		inputPanel.add(inputField);
		
		return inputPanel;
	}

	@Override
	public void actionPerformed(ActionEvent event) { // Submit button pressed
		
		System.out.println(pollTextField.getText()+" "+voteTextField.getText());
		
		
		long pollID = Long.parseLong(pollTextField.getText());
		long voteID = Long.parseLong(voteTextField.getText());
		
		System.out.println(pollID+" "+voteID);
		
		
		
		
		//UDP connection
		//Send <pollID> <voteID> to server
	}
	
	public static void main(String[] args) {
		VoterClient client = new VoterClient();
		client.setVisible(true);
	}
}