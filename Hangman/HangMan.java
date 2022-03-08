package Object;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HangMan { 
	public static String previousGuess = "";
	public static Character guess;
	public static int guesses = 6;
	public static Scanner x;
	public static String word;
	public static char[] board;
	// Opens the file
	public static void openFile() {
		try {
			x = new Scanner(new File("/* Your File Name Here*/"));
		}
		catch (Exception e) {
			System.out.println("File not found.");
		}
	}
	// Assigns the String word a word randomly selected from the file. The file has 354933 lines.
	public static void assignWord() {
		int temp = (int) (Math.random() * 354933 + 1);
		int counter = 0;
		while(x.hasNext()) {
			if(temp == counter) {
				word = x.next();
				break;
			} else {
				counter++;
				x.nextLine();
			}
		}
	}
	// Makes the board object equal to _ based on the length of word.
	public static void boardSetup(char board[]) {
		for (int i = 0; i < word.length(); i++) {
			board[i] = '_';
		}
	}
	// Prints the board.
	public static void printBoard() {
		for (int i = 0; i < word.length(); i++) {
			System.out.print(board[i] + " ");
		}
		System.out.println();
	}
	// Sends out a message based on your guesses left and what you previously inputted.
	public static void gameMessage() {
		System.out.println("Here is what you have so far: ");
		printBoard();
		System.out.println("You have already guessed: " + previousGuess);
		System.out.print("You have " + guesses + " guesses remaining. \nWhat is your guess? ");
	}
	// Checks the input of the user based on the their previous inputs and the word. 
	public static void checkInput() {
		boolean checker = false;
		for (int i = 0; i < word.length(); i++) {
			if (guess.equals(word.charAt(i))) { 
				board[i] = word.charAt(i);
				checker = true;
			}
		}
		if (!checker) {
			guesses--;
			previousGuess += guess;
		}
		if (guesses == 0) {
			System.out.println("\n");
			printBoard();
			System.out.println("You have already guessed: " + previousGuess);
			System.out.println("You lost.");
			System.exit(0);
		}
		 if (word.compareTo(new String(board)) == 0) {
				System.out.println("\n");
				printBoard();
				System.out.println("You won! Congratulations!");
				System.exit(0);
		 }
		System.out.println("\n");
		gameMessage();
	}

	public static void main(String[] args) {
		openFile();
		assignWord();
		board = new char[word.length()];
		Scanner input = new Scanner(System.in);
		boardSetup(board);
		gameMessage();
		while (guesses > 0) {
			guess = input.next().charAt(0);
			guess = Character.toLowerCase(guess);
			for (int i = 0; i < previousGuess.length(); i++) {
				while (guess.equals(previousGuess.charAt(i))) {
					System.out.println("Try another letter: ");
					guess = input.next().charAt(0);
					guess = Character.toLowerCase(guess);
				}
			}
			checkInput();
		}
		input.close();	
		x.close();
	}
}