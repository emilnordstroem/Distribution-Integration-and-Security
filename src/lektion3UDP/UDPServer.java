package lektion3UDP;

import java.net.*;

class UDPServer {

	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9876);

		// Receive and send data byte arrays
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];

		// While the process is open
		while (true) {
			// Received data and length
			DatagramPacket receivePacket = new DatagramPacket(
					receiveData,
					receiveData.length
			);
			serverSocket.receive(receivePacket);

			// Get data and IP address from packet
			String sentence = new String(receivePacket.getData());
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String capitalizedSentence = sentence.toUpperCase();

			// Ititialize data to be send
			sendData = capitalizedSentence.getBytes();
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
