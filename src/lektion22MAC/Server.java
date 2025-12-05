package lektion22MAC;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		ServerSocket welcomeSocket = new ServerSocket(10000);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			// save input in server through a variable
			(new ServerThread(connectionSocket)).start();
		}
	}

}
