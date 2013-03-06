package src.pollingSystem.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Observable;


public class VoteListener extends Observable implements Runnable {

	/**Default port for UDP connection*/
	public static final int DEFAULT_PORT = 1500;

	/**Default data size for the data received*/
	public static final int DEFAULT_DATA_SIZE = 100;
	
	private boolean serverRunning;

	@Override
	public void run() {
		
		byte data[] = new byte[DEFAULT_DATA_SIZE];
		
		DatagramPacket receivePacket;
		DatagramSocket receiveSocket;

		// Construct a DatagramPacket for receiving packets
		receivePacket = new DatagramPacket(data, data.length);
		serverRunning = true;
		
		try {
			receiveSocket = new DatagramSocket(DEFAULT_PORT);
			
			while(serverRunning) { //Loop until something tells the listener to stop				
				// Block until a datagram packet is received from receiveSocket.
				receiveSocket.receive(receivePacket);
	
				notifyObservers(new String(receivePacket.getData(),0,receivePacket.getLength()));
			}
			receiveSocket.close();
		} 
		catch(SocketException se) {  se.printStackTrace(); System.exit(1); } 
		catch (IOException ioe) {  ioe.printStackTrace(); System.exit(1); }

		//notifyObservers(stringRecieved);

	}

	public void stopListening() {
		serverRunning = false;
		//should send a udp message on DEFAULT_PORT to interrupt the socket
	}

}
