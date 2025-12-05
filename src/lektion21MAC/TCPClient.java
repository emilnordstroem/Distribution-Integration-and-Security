package lektion21MAC;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Base64;

public class TCPClient {

	public static void main(String argv[]) throws Exception {
		String sentence;

		BufferedReader inFromUser = new BufferedReader(
				new InputStreamReader(System.in)
		);

		// Sæt op forbindelse
		Socket clientSocket = new Socket("localhost", 10000);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream()
		);
		BufferedReader inFromServer = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream())
		);

		// Generer den delte hemmelighed
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
		SecureRandom secureRandom = new SecureRandom(); // generer nummerisk sekvens
		keyGen.init(secureRandom); // tilføj til nøgle
		SecretKey secretKey = keyGen.generateKey(); // genere en nøgle på baggrund af nøgle genereringen

		// Input fra brugeren
		System.out.print("Indtast besked: ");
		sentence = inFromUser.readLine();

		// Opret MAC initialiser med nøglen
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(secretKey);
		byte[] macResult = mac.doFinal(sentence.getBytes());

		// Send nøglen til serveren (Base64 encoded)
		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		outToServer.writeBytes(encodedKey + '\n');
		System.out.println("Nøgle sendt til server");

		// Send beskeden til serveren
		outToServer.writeBytes(sentence + '\n');
		System.out.println("Besked sendt til server");

		// Send MAC'et til serveren (Base64 encoded)
		String encodedMac = Base64.getEncoder().encodeToString(macResult);
		outToServer.writeBytes(encodedMac + '\n');
		System.out.println("MAC sendt til server");

		clientSocket.close();
	}

}