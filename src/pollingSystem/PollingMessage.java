/**
 * SYSC3303 - Term Project
 * The Message represents the different type of messages being sent to and from the server.
 * @author Matthew Smith - 100 827 363
 * @version 02/5/2013
 */
package pollingSystem;

public class PollingMessage {

	private String text;
	
	public PollingMessage(String messageText) {
		text = messageText;
	}
	
	public boolean isCreatePollMessage() {
		return true; //temporary fake logic
	}

	@Override
	public String toString() {
		return text;
	}
}
