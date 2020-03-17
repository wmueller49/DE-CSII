/**
 * @author William Mueller
 * DE CSII
 * TicTacToeHashCode
 * 3/16/2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TicTacToeHashCode extends Board {

	/**
	 * Field is either true or false, 
	 * true at indexes where the hashcode of a tictactoe string is a winner
	 * false where it is a loser
	 */
	boolean[] winners;
	
	TicTacToeHashCode(String s) {
		super(s);
		winners = new boolean[19683];
	}

	
	@Override
	/**
	 * Hashcode to place a board string in winners with no collisions
	 * @return an int hashcode
	 */
	public int myHashCode() {
		int result = 0;
		int count = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				char c = charAt(this.getBoardString(), i, j);
				
				switch (c) {
				case 'x':
					c = '1';
					break;
				case 'o':
					c = '2';
					break;
				case ' ':
					c = '0';
					break;
				}
				
				result = result + (((int)Math.pow(3, count)) * Integer.parseInt(String.valueOf(c)));
				count++;
			}
		}
		return result;
	}

	@Override
	/**
	 * @param s is the input string
	 * @return whether s is a win or not
	 */
	public boolean isWin(String s) {
		this.setBoardString(s);
		return winners[myHashCode()];
      }
      
	@Override
	/**
	 * @return whether or not the current board string is a winner or not
	 */
	public boolean isWin() {
		return winners[myHashCode()];
      }
      
	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		 
		String input = "TicTacToeWinners.txt";
		String input2 = "TTT_Tests.txt";
		
		File inputFile = new File(input);
		File inputFile2 = new File(input2);
		
		Scanner inputScanner = null;
		Scanner inputScanner2 = null;
		try {
			inputScanner = new Scanner(inputFile);
			inputScanner2 = new Scanner(inputFile2);
		}
		catch(FileNotFoundException ex) {
			
		}
		
		for(int i = 0; i < board.winners.length; i++) {
			board.winners[i] = false;
		}
		
		while(inputScanner.hasNextLine()) {
			board.setBoardString(inputScanner.nextLine());
			board.winners[board.myHashCode()] = true;
		}
		
		inputScanner.close();
	 
		String outputPath = "output.txt";
		File outputFile = new File(outputPath);
		PrintWriter output = null;
		
		try {
			output = new PrintWriter(outputFile);
		}
		catch(FileNotFoundException x) {
			
		}
		
		while(inputScanner2.hasNextLine()) {
			String s = inputScanner2.nextLine();
			output.println(s + ": " + board.isWin(s));
		}
		
		
		output.close();
		
		while (true) {
			   board.displayRandomString();
			   Thread.sleep(4000);
		}		
	}

}
