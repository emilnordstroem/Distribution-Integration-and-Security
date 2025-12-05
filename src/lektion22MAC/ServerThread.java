package lektion22MAC;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Base64;
import java.util.Arrays;

public class ServerThread extends Thread {
	Socket connSocket;

	public ServerThread(Socket connSocket) {
		this.connSocket = connSocket;
	}

	public void run() {
		try {
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connSocket.getInputStream())
			);
			DataOutputStream outToClient = new DataOutputStream(
					connSocket.getOutputStream()
			);

			// Modtag nøglen fra klienten - første besked
			String encodedKey = inFromClient.readLine();
			byte[] keyBytes = Base64.getDecoder().decode(encodedKey);

			// Opret nøgle med nøglen og algoritmen - ligner nu den fra client siden
			SecretKey secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
			System.out.println("Nøgle modtaget fra klient");

			// Modtag beskeden fra klienten
			String clientMessage = inFromClient.readLine();
			System.out.println("Besked modtaget: " + clientMessage);

			// Modtag MAC fra klienten
			String encodedMac = inFromClient.readLine();
			byte[] receivedMac = Base64.getDecoder().decode(encodedMac);
			System.out.println("MAC modtaget fra klient");

			// Beregn MAC for den modtaget besked med den oprettet nøgle
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(secretKey);
			byte[] calculatedMac = mac.doFinal(clientMessage.getBytes());
			// Verificer at de to beskeder er ens: h(m, k) == m
			boolean isValid = Arrays.equals(receivedMac, calculatedMac);

			if (isValid) {
				System.out.println("Beskeden er autentisk");
			} else {
				System.out.println("Beskeden er ikke autentisk");
			}

		} catch (IOException e) {
			System.err.println("IO fejl: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Kryptografisk fejl: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				connSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}