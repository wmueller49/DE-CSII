
//TODO Make sure you remove all of the TODO comments from this file before turning itin
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class TicTacToeHashCode extends Board {

	boolean[] winners; // True if the hash string that maps to this index is a winner, false otherwise

	TicTacToeHashCode(String s) {
		super(s);
      //TODO Instantiate winners array
		winners = new boolean[19683];
	}

	@Override
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
	public boolean isWin(String s) {
      // TODO write an isWin method that takes in a String.  This should not change the board.  Board has an additional charAt 
      // TODO method to facilitate this
		this.setBoardString(s);
		return winners[myHashCode()];
      }
      
	@Override
	public boolean isWin() {
		return winners[myHashCode()];
      // TODO write an isWin method that uses boardString
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
