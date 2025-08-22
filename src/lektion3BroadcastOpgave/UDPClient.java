package lektion3BroadcastOpgave;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

class UDPClient {
	public static void main(String args[]) throws Exception {
		// Console input
		BufferedReader inFromUser = new BufferedReader(
				new InputStreamReader(System.in)
		);

		// Socket creation and IP address
		DatagramSocket clientSocket = new DatagramSocket();
		// enable socket broadcast
		clientSocket.setBroadcast(true);
		// Broadcast ip address
		InetAddress IPAddress = InetAddress.getByName("255.255.255.255");

		// Send and Receive data part
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];

		// sendData byte array filled with data
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();

		// Send packet created
		DatagramPacket sendPacket = new DatagramPacket(
				sendData,
				sendData.length,
				IPAddress,
				9876
		);

		// packet for received data to client socket
		DatagramPacket receivePacket = new DatagramPacket(
				receiveData,
				receiveData.length
		);

		// send and receive
		int sendTaeller = 1;
		boolean messageModtaget = false;

		while (sendTaeller <= 3 && !messageModtaget) {
			try {
				clientSocket.send(sendPacket);
				clientSocket.setSoTimeout(2000);
				// ==> To go Server
				clientSocket.receive(receivePacket);
				printModtagetMessage(receivePacket);
				messageModtaget = true;
			} catch (SocketTimeoutException e) {
				System.out.println("Ingen response");
				sendTaeller++;
			}
		}
		if (!messageModtaget) {
			System.out.println("Ingen response efter 3 forsøg");
		}
		clientSocket.close();
	}

	private static void printModtagetMessage(DatagramPacket receivePacket){
		String responseFromServer = new String(receivePacket.getData()).trim();
		System.out.println("FROM SERVER: " + responseFromServer);
	}

}