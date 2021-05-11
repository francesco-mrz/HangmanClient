package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import player.NetPlayer;

public class HangmanClient {

	public static void main(String[] args) throws IOException {
		String host = "127.0.0.1";
		int port = 8888;
		Socket socket = null;

		try {
			socket = new Socket(host, port);
			System.out.println("Connection extablished with the server");
		} catch (IOException e) {
			System.out.println("Error while loading");
			System.exit(-1);
		}

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		NetPlayer player = new NetPlayer();
		String secretWord = in.readLine();

		int exit;
		while (true) {

			String knownLettersg = in.readLine();
			int missingLetters = Integer.valueOf(in.readLine());
			int failedAttempts = Integer.valueOf(in.readLine());
			player.update(secretWord, missingLetters, failedAttempts, knownLettersg);

			exit = Integer.valueOf(in.readLine());
			// System.out.println(exit);
			if (exit == 0) {
				break;
			}

			char c = player.chooseLetter();
			out.println(c);
			out.flush();
		}
		System.out.println("Gioco concluso");
	}
}
