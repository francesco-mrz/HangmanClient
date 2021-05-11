package player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class NetPlayer {
	BufferedReader console;
	public static final int MAX_FAILED_ATTEMPTS = 7;

	public NetPlayer() {
		console = new BufferedReader(new InputStreamReader(System.in));
	}

	public void update(String secretWord, int missingLetters, int failedAttempts, String knownLetters) {

		if (failedAttempts == MAX_FAILED_ATTEMPTS) {
			printBanner("Hai perso!  La parola da indovinare era '" + secretWord + "'");
		} else if (missingLetters == 0) {
			printBanner("Hai indovinato!   (" + secretWord + ")");
		} else {
			int rem = MAX_FAILED_ATTEMPTS - failedAttempts;
			System.out.print("\n" + rem + " tentativi rimasti\n");
			System.out.println(this.gameRepresentation(failedAttempts));
			System.out.println(knownLetters);
		}
	}

	private String gameRepresentation(int a) {
		String s = "   ___________\n  /       |   \n  |       ";
		s += (a == 0 ? "\n" : "O\n");
		s += "  |     " + (a == 0 ? "\n" : (a < 5 ? "  +\n" : (a == 5 ? "--+\n" : "--+--\n")));
		s += "  |       " + (a < 2 ? "\n" : "|\n");
		s += "  |      " + (a < 3 ? "\n" : (a == 3 ? "/\n" : "/ \\\n"));
		s += "  |\n================\n";
		return s;
	}

	private void printBanner(String message) {
		System.out.println("");
		for (int i = 0; i < 80; i++)
			System.out.print("*");
		System.out.println("\n***  " + message);
		for (int i = 0; i < 80; i++)
			System.out.print("*");
		System.out.println("\n");
	}

	public char chooseLetter() {
		for (;;) {
			System.out.print("Inserisci una lettera: ");
			String line = null;
			try {
				line = console.readLine().trim();
			} catch (IOException e) {
				line = "";
			}
			if (line.length() == 1 && Character.isLetter(line.charAt(0))) {
				return line.charAt(0);
			} else {
				System.out.println("Lettera non valida.");
			}
		}
	}
}
