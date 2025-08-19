package lektion2WebServer;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		ServerSocket welcomeSocket = new ServerSocket(6789);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			// save input in server through a variable
			(new ServerThread(connectionSocket)).start();
		}
	}

}
