package lektion2WebServer;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
	private final Socket connSocket;
	private static final String baseDirectory = "C:\\Users\\Emil Nordstrøm\\IdeaProjects\\ComputerScience(AP)\\semester_3\\DIS\\LektionsOpgaver\\src\\lektion2WebServer";
	
	public ServerThread(Socket connSocket) {
		this.connSocket = connSocket;
	}

	public void run() {
		try {
			// Read input from the clients socket
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
			// Output stream for the clients socket
			DataOutputStream outToClient = new DataOutputStream(connSocket.getOutputStream());
			
			// Do the work and the communication with the client here
			String[] httpRequestMessage = inFromClient.readLine().split(" ");
			String httpRequestURL = httpRequestMessage[1];
			byte[] fileData = read(baseDirectory + File.separator + httpRequestURL);

			outToClient.writeBytes("HTTP/1.1 200 OK\n");
			outToClient.writeBytes(contentType(httpRequestURL));
			outToClient.writeBytes("Connection: Close\n");
			outToClient.writeBytes("\n");
			outToClient.write(fileData, 0, fileData.length);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		// do the work here
	}

	public String contentType(String documentName) {
		//returns the Content-Type headerline for a given document-name
		if (documentName.endsWith(".html")){
			return ("Content-Type: text/html\n");
		} else if (documentName.endsWith(".gif")) {
			return ("Content-Type: image/gif\n");
		} else if (documentName.endsWith(".png")) {
			return ("Content-Type: image/png\n");
		} else if (documentName.endsWith(".jpg")) {
			return ("Content-Type: image/jpg\n");
		} else if (documentName.endsWith(".js")) {
			return ("Content-Type: text/javascript\n");
		} else if (documentName.endsWith(".css")) {
			return ("Content-Type: text/css\n");
		} else {
			return ("Content-Type: text/plain\n");
		}
	}

	public byte[] read(String aInputFileName) throws FileNotFoundException {
		// returns the content of a file in a binary array
		System.out.println("Reading in binary file named : " + aInputFileName);
		File file = new File(aInputFileName);
		System.out.println("File size: " + file.length());
		byte[] result = new byte[(int) file.length()];
		try {
			InputStream input = null;
			try {
				int totalBytesRead = 0;
				input = new BufferedInputStream(new FileInputStream(file));
				while (totalBytesRead < result.length) {
					int bytesRemaining = result.length - totalBytesRead;
					int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
					// input.read() returns -1, 0, or more :
					if (bytesRead > 0) {
						totalBytesRead = totalBytesRead + bytesRead;
					}
				}
				System.out.println("Num bytes read: " + totalBytesRead);
			} finally {
				System.out.println("Closing input stream.");
				input.close();
			}
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}

}