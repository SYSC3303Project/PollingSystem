/**
 * Voter Client
 * This Lab will print out a message every two seconds for one minute
 * @author Matthew Smith - 100 827 363
 * @version 01/17/2013
 */
package pollingSystem.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class VoterClient extends JFrame implements ActionListener{
	
	/**Generated Serial ID*/
	private static final long serialVersionUID = -5817022285820394734L;
	private JTextField pollTextField;
	private static final String POLL_LABEL = "PollID";
	private JTextField voteTextField;
	private static final String VOTE_LABEL = "VoteID";
	
	public VoterClient() {
		super("Voter Client");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.add(votePanel());
		this.pack();
	}
	
	/**
	 * Helper Method sets up the Vote Panel which contains the fields for what 
	 * is being voted on and the vote cast.
	 * @return 
	 */
	private JPanel votePanel() {
		JPanel votePanel = new JPanel(new GridLayout(3,1));
		
		JPanel pollField = labeledInputField(POLL_LABEL, pollTextField);
		
		JPanel voteField = labeledInputField(VOTE_LABEL, voteTextField);
		
		
		JButton submitButton = new JButton("Submit Vote");
		submitButton.addActionListener(this);
		
		votePanel.add(pollField);
		votePanel.add(voteField);
		votePanel.add(submitButton);
		
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
		
		inputField = new JTextField();
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