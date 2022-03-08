package String;
import java.util.Scanner;

public class FourConnect {
	private static int turnCounter = 0;  
	private static char[][] boardArray = new char[6][7];
	private static int row[] = {5,5,5,5,5,5,5,5};
	private static int tempCounter = 0;

	// Creates And Fills The Playing Board (6Rows7Columns) with '.'.
	private static void boardSetup(char[][] boardArray) {
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				boardArray[r][c] = '.';
				System.out.print(boardArray[r][c] + " ");
			}
			System.out.println();
		}
	}

	// Prints the board with the field variable boardArray.
	private static void printBoard(char[][] boardArray) {
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				System.out.print(boardArray[r][c] + " ");
			}
			System.out.println();
		}
	}

	// Decides which player turn it is depending on if the number is odd or even. Even is X, Odd is O.
	private static char playerTurn(int playerTurn) {
		if (playerTurn % 2 == 0) {
			return 'X';
		} else if (playerTurn % 2 != 0) {
			return 'O';
		}
		return ' ';
	}

	// Prints to the console whose turn it is.
	private static void printTurn() {
		int turnCounter = FourConnect.turnCounter;
		System.out.println(playerTurn(turnCounter) + ", it is your turn. Type the column you want to play in (1-7): ");
	}

	// If row is in an invalid range it will print the board again and ask the user to put their piece in another column. 
	private static void errorStatemnt(int playerCol) {
		printBoard(boardArray);
		System.out.println("Please place your piece in another column: ");
	}

	// Updates the boardArray with whomever's move it is and places the corresponding object in the array.
	private static void switchStatement(int playerCol) {
		FourConnect.boardArray[row[playerCol]][playerCol - 1] = playerTurn(turnCounter);
		printBoard(boardArray);
		FourConnect.row[playerCol] -= 1;
		FourConnect.turnCounter++;
	}

	// Checks the board for 4 in a row. 0 == Horizontal, 1 == Vertical, 2 == Left to right
	private static void	checker (int checker) {
		int tempCol = 4, tempRow = 6;
		if (checker == 1) {
			tempCol = 3;
			tempRow = 7;
		} else if (checker == 2) {
			tempRow = 3;
			tempCol = 4;
		}
		for (int r = 0; r < tempRow; r++) { 
			for (int c = 0; c < tempCol; c++){
				for (int cAdd = 0; cAdd < 4; cAdd++) {
					if (checker == 0) {
						tempCounter += (int)boardArray[r][c + cAdd];
					} else if (checker == 1) {
						tempCounter += (int)boardArray[c + cAdd][r];
					} else if (checker == 2) {
						tempCounter += (int)boardArray[r + cAdd][c + cAdd];
					}
					if (tempCounter == 352) {
						System.out.println("X Wins!");
						System.exit(0);
					} else if (tempCounter == 316) {
						System.out.println("O Wins!");
						System.exit(0);
					}	
				}
				tempCounter = 0;
			}
		}
	}

	public static void main(String[] args) {
		boardSetup(FourConnect.boardArray);
		printTurn();
		Scanner playerMove = new Scanner(System.in);
		int playerCol = playerMove.nextInt();
		while (turnCounter < 42) {
			if (playerCol >= 1 && playerCol <= 7) {
				if (row[playerCol] == -1) {
					errorStatemnt(playerCol);
				} else {
					switchStatement(playerCol);
				}
				if (turnCounter == 42) {
					System.out.println("Tie!");
					break;
				}
			} else System.out.println("Invalid Column!");
			checker(0);
			checker(1);
			checker(2);
			// The for loop checks diagonally for 4 in a row.
			for (int row = 0; row < 3; row++) {
				for (int col = 6; col > 2; col--){
					for (int c = 0; c < 4; c++) {
						tempCounter += (int)boardArray[row + c][col - c];
						if (tempCounter == 352) {
							System.out.println("X Wins!");
							System.exit(0);
						} else if (tempCounter == 316) {
							System.out.println("O Wins!");
							System.exit(0);
						}
					}
					tempCounter = 0;
				}
			}
			printTurn();
			playerMove = new Scanner(System.in);
			playerCol = playerMove.nextInt();
		}
	}
}
