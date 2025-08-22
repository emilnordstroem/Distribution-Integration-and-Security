package lektion3UDP;

import java.io.*;
import java.net.*;

class UDPClient {
	public static void main(String args[]) throws Exception {
		BufferedReader inFromUser = new BufferedReader(
				new InputStreamReader(System.in)
		);

		// UDP Socket: Datagram
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");

		// Send and receive byte array
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];

		// Read line
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();

		// new datagram from socket
		DatagramPacket sendPacket = new DatagramPacket(
				sendData,
				sendData.length,
				IPAddress,
				9876
		);
		clientSocket.send(sendPacket);

		// ==> To go Server
		// new datagram for received datagram from server
		DatagramPacket receivePacket = new DatagramPacket(
				receiveData,
				receiveData.length
		);
		clientSocket.receive(receivePacket);

		// Task
		String modifiedSentence = new String(receivePacket.getData()).trim();
		System.out.println("FROM SERVER:" + modifiedSentence);
		clientSocket.close();
	}

}
