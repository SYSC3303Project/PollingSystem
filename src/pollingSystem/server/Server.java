/**
 * SYSC3303 - Term Project
 * The server will receive connections and log the information into the proper poll
 * @author Matthew Smith - 100 827 363
 * @version 02/5/2013
 */

package pollingSystem.server;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

	private DatagramSocket sendSocket, receiveSocket;
	
	public Server() {
		
	}
	
	public void startServerListening() {
		Thread listenThread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					// socket will be used to send UDP packets.
					sendSocket = new DatagramSocket();

					// bind to port 5000, socket will be used to receive UDP packets.
					receiveSocket = new DatagramSocket(5000);
					
					
				} catch (SocketException se) {
					se.printStackTrace();
					System.exit(1);
				}
			}
		});
		
		listenThread.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
