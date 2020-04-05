/**
 * @author William Mueller
 * DE CSII
 * Eight Queens
 * 
 * ChessSquarePanel
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessSquarePanel extends JPanel{

	private final static int FONTSIZE = 40;
	private static final int ROWS = 8;
	private static final int COLS = 8;   
	private static final int HEIGHT = 120*ROWS;
	private static final int WIDTH = 120*COLS;
	
	private Color backgroundColor;
	private boolean flag;
	private ChessSquarePanel[][] squares = new ChessSquarePanel[ROWS][COLS];
	private static ArrayList<ArrayList<Queen>> winningLists = new ArrayList<ArrayList<Queen>>();
	
	
	/**
	 * 
	 */
	public ChessSquarePanel(Color c, boolean f) {
		backgroundColor = c;
		flag = f;
	}
	
	public void setFlag(boolean b) {
		flag = b;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, FONTSIZE));
		g.setColor(Color.gray);
		this.setBackground(backgroundColor);
		
		if(flag) {
			int x = (this.getWidth() / 2) - FONTSIZE/4; // - letter.length()/2;
            int y = (this.getHeight() / 2) + FONTSIZE/4;
            
            g.drawString("\u2655", x, y);
		}
		
	}
	
	public void createBoard() {
		JFrame window = new JFrame("Eight Queens");
		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel();
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
	
	public void createBoard(ArrayList<Queen> queenList) {
		//clearBoard();
		createBoard();
		for(Queen q: queenList) {
			update(q, true);
		}
	}
	
	public void update(Queen q, boolean b) {
		int i = q.getCol();
		int j = q.getRow();
		
		squares[i][j].setFlag(b);
	}
	
	public void clearBoard() {
		for(int i = 0; i < squares.length; i++) {
			for(int j = 0; j < squares[i].length; j++) {
				squares[i][j].setFlag(false);
			}
		}
	}
	
	public void myFirstSolution() {
		JFrame window = new JFrame("Eight Queens");
		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel();
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
	
	public boolean checkRowsAndCols(ArrayList<Queen> queenList) {
		
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
	
	public boolean checkDiagonals(ArrayList<Queen> queenList) {
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
	
	
	public boolean legalMove(ArrayList<Queen> queenList) {
		return checkRowsAndCols(queenList) && checkDiagonals(queenList);
	}
	
	public boolean addQueens(ArrayList<Queen> queenList) {
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
		ChessSquarePanel s = new ChessSquarePanel(Color.blue, false);
		s.addQueens(emptyList);
		s.createBoard();
		
		System.out.println(winningLists.size());
		
		for(int i = 0; i < winningLists.size(); i++) {
			s.createBoard(winningLists.get(i));
			try {
				Thread.sleep(3000);
			}
			catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		
		
	}

}
