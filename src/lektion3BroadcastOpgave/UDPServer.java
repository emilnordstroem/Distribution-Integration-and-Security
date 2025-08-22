package lektion3BroadcastOpgave;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPServer {

	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9876);

		// Receive and send data byte arrays
		byte[] receiveData = new byte[1024];
		String sentence = "Welcome from server";
		byte[] sendData = sentence.getBytes();

		// While the process is open
		while (true) {
			// Received data and length of received data
			DatagramPacket receivePacket = new DatagramPacket(
					receiveData,
					receiveData.length
			);
			serverSocket.receive(receivePacket);

			// IP address and port from received packet'
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			// What to respond with

			// Ititialize data to be send
			DatagramPacket sendPacket = new DatagramPacket(
					sendData,
					sendData.length,
					IPAddress,
					port
			);
			serverSocket.send(sendPacket);
			// ==> Go to Client
		}
	}

}
