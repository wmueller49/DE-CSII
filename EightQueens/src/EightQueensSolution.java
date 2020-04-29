import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author William Mueller
 * DE CSII
 * EightQueens
 * 
 * EightQueensSolution
 */
public class EightQueensSolution {
	
	/**
	 * Field that says how many rows are on the board
	 */
	private static final int ROWS = 8;
	
	/**
	 * Field that says how many columns are on the board
	 */
	private static final int COLS = 8;   
	
	/**
	 * Field that makes the height of the board based on the number of rows
	 */
	private static final int HEIGHT = 120*ROWS;
	
	/**
	 * Field that makes the width of the board based on the number of columns
	 */
	private static final int WIDTH = 120*COLS;
	
	/**
	 * Field that holds the JFrame
	 */
	private static JFrame window;
	
	/**
	 * Field that holds the JPanel which is displayed on the JFrame
	 */
	private static JPanel p;
	
	/**
	 * Field that holds all of the chessSquarePanels which will make up the board
	 */
	private static ChessSquarePanel[][] squares = new ChessSquarePanel[ROWS][COLS];
	
	/**
	 * Field that holds the lists of every possible solution
	 */
	private static ArrayList<ArrayList<Queen>> winningLists = new ArrayList<ArrayList<Queen>>();
	
	/**
	 * Creates an empty chess board
	 */
	public static void createBoard() {
		window = new JFrame("Eight Queens");
		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new JPanel();
		p.setLayout(new GridLayout(ROWS,COLS));
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(i % 2 == 0) {
					if(j % 2 == 0) {
						ChessSquarePanel square = new ChessSquarePanel(Color.white, false);
						squares[i][j] = square;
						p.add(square);
					}
					else {
						ChessSquarePanel square = new ChessSquarePanel(Color.black, false);
						squares[i][j] = square;
						p.add(square);
					}
				}
				else {
					if(j % 2 == 0) {
						ChessSquarePanel square = new ChessSquarePanel(Color.black, false);
						squares[i][j] = square;
						p.add(square);
					}
					else {
						ChessSquarePanel square = new ChessSquarePanel(Color.white, false);
						squares[i][j] = square;
						p.add(square);
					}
				}
			}
		}
		
		window.add(p);
		window.setVisible(true);
	}
	
	/**
	 * clears the board and displays the given list of queens at their respective locations on the board
	 * @param queenList the queens to be displayed on the board
	 */
	public static void createBoard(ArrayList<Queen> queenList) {
		clearBoard();
		for(Queen q: queenList) {
			update(q, true);
		}
		p.repaint();
	}
	
	/**
	 * sets the value of the chessSquarePanel at the location of the given queen to the value of b
	 * @param q the queen to be displayed
	 * @param b the value of the queen
	 */
	public static void update(Queen q, boolean b) {
		int i = q.getCol();
		int j = q.getRow();
		
		squares[i][j].setFlag(b);
		squares[i][j].repaint();
	}
	
	/**
	 * clears the board of all values
	 */
	public static void clearBoard() {
		for(int i = 0; i < squares.length; i++) {
			for(int j = 0; j < squares[i].length; j++) {
				squares[i][j].setFlag(false);
			}
		}
		p.repaint();
	}
	
	/**
	 * displays the first solution I found before writing the recursive solution
	 */
	public static void myFirstSolution() {
		window = new JFrame("Eight Queens First Solution");
		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new JPanel();
		p.setLayout(new GridLayout(ROWS,COLS));
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(
					(i == 0 && j == 3) ||
					(i == 1 && j == 1) ||
					(i == 2 && j == 6) ||
					(i == 3 && j == 2) ||
					(i == 4 && j == 5) ||
					(i == 5 && j == 7) ||
					(i == 6 && j == 4) ||
					(i == 7 && j == 0)
						) {
					if(i % 2 == 0) {
						if(j % 2 == 0) {
							ChessSquarePanel square = new ChessSquarePanel(Color.white, true);
							squares[i][j] = square;
							p.add(square);
						}
						else {
							ChessSquarePanel square = new ChessSquarePanel(Color.black, true);
							squares[i][j] = square;
							p.add(square);
						}
					}
					else {
						if(j % 2 == 0) {
							ChessSquarePanel square = new ChessSquarePanel(Color.black, true);
							squares[i][j] = square;
							p.add(square);
						}
						else {
							ChessSquarePanel square = new ChessSquarePanel(Color.white, true);
							squares[i][j] = square;
							p.add(square);
						}
					}
				}
				
				else if(i % 2 == 0) {
					if(j % 2 == 0) {
						ChessSquarePanel square = new ChessSquarePanel(Color.white, false);
						squares[i][j] = square;
						p.add(square);
					}
					else {
						ChessSquarePanel square = new ChessSquarePanel(Color.black, false);
						squares[i][j] = square;
						p.add(square);
					}
				}
				else {
					if(j % 2 == 0) {
						ChessSquarePanel square = new ChessSquarePanel(Color.black, false);
						squares[i][j] = square;
						p.add(square);
					}
					else {
						ChessSquarePanel square = new ChessSquarePanel(Color.white, false);
						squares[i][j] = square;
						p.add(square);
					}
				}
			}
		}
		
		window.add(p);
		window.setVisible(true);
	}
	
	/**
	 * returns whether the given list of queens can attack each other on rows or columns
	 * @param queenList the list of queens to check
	 * @return true if none of the queens can attack each other on a row or column, false otherwise
	 */
	public static boolean checkRowsAndCols(ArrayList<Queen> queenList) {
		
		for(int i = 0; i < queenList.size(); i++) {
			Queen currentQueen = queenList.get(i);
			
			for(int j = i+1; j < queenList.size(); j++) {
				Queen checkQueen = queenList.get(j);
				if(currentQueen.getRow() == checkQueen.getRow() || currentQueen.getCol() == checkQueen.getCol()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * returns whether the given list of queens can attack each other on diagonals
	 * @param queenList the list of queens to check
	 * @return true if none of the queens can attack each other diagonally, false otherwise
	 */
	public static boolean checkDiagonals(ArrayList<Queen> queenList) {
		for(int i = 0; i < queenList.size(); i++) {
			Queen currentQueen = queenList.get(i);
			int r = currentQueen.getRow();
			int c = currentQueen.getCol();
			
			while(r < 8 && c < 8) {
				r++;
				c++;
				for(int j = i+1; j < queenList.size(); j++) {
					Queen checkQueen = queenList.get(j);
					if(checkQueen.getCol() == c && checkQueen.getRow() == r) {
						return false;
					}
				}
			}
			
			r = currentQueen.getRow();
			c = currentQueen.getCol();
			
			while(r > 0 && c < 8) {
				r--;
				c++;
				for(int j = i+1; j < queenList.size(); j++) {
					Queen checkQueen = queenList.get(j);
					if(checkQueen.getCol() == c && checkQueen.getRow() == r) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	
	/**
	 * calls checkRowsAndCols and checkDiagonals on queenList and returns whether the list of queens is valid
	 * @param queenList the list of queens to check
	 * @return true if none of the queens can attack each other, false otherwise
	 */
	public static boolean legalMove(ArrayList<Queen> queenList) {
		return checkRowsAndCols(queenList) && checkDiagonals(queenList);
	}
	
	/**
	 * recursive method that finds all of the possible solutions for the EightQueens problem and adds each solution list to winningLists
	 * @param queenList starts as an empty list
	 * @return true if the list has size 8, thus being a winner, otherwise returns false
	 */
	public static boolean addQueens(ArrayList<Queen> queenList) {
		if(queenList.size() == 8) {
			return true;
		}
		//System.out.println("List: " + queenList);
		
		Queen currentQueen = null;
		int c = 0;
		int maxCols = 1;
		
		if(queenList.size() > 0) {
			currentQueen = queenList.get(queenList.size()-1);
			c = currentQueen.getCol() + 1;
			maxCols = 8;
		}
		
		
		for(; c < maxCols; c++) {
			for(int r = 0; r < 8; r++) {
				Queen testQueen = new Queen(r, c);
				queenList.add(testQueen);
				if(legalMove(queenList)) {
					if(addQueens(queenList)) {
						winningLists.add(new ArrayList(queenList));
					}
				}
				queenList.remove(testQueen);
			}
		}
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		ArrayList<Queen> emptyList = new ArrayList<Queen>();
		addQueens(emptyList);
		createBoard();
		
		System.out.println("Number of solutions found: " + winningLists.size());
		
		for(int i = 0; i < winningLists.size(); i++) {
			createBoard(winningLists.get(i));
			
			try {
				Thread.sleep(3000);
			}
			catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
