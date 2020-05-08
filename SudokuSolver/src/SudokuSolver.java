/**
 * @author Owner
 *
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SudokuSolver implements ActionListener{	

	private final int ROWS = 9;

	private final int COLS = 9;   	

	private final int HEIGHT = 100*ROWS;

	private final int WIDTH = 100*COLS;
	
	private JFrame window;
	
	private JPanel container;
	private JPanel sudokuPanel;
	private JPanel buttonPanel;
	
	private JButton b;
	

	private SudokuGrid sudoku;
	
	/**
	 * 
	 */
	public SudokuSolver(ArrayList<ArrayList<SudokuSquare>> startBoard) {
		window = new JFrame("Sudoku");
		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		sudokuPanel = new JPanel();
		sudokuPanel.setLayout(new GridLayout(ROWS,COLS));
		
		sudoku = new SudokuGrid();
		int count = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				sudoku.fill(i, j, new SudokuGrid(startBoard.get(count)));
				count++;
			}
		}
		
		
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 3; c++) {
				if(sudoku.get(r/3, c) instanceof SudokuGrid) {
					SudokuGrid currentGrid = (SudokuGrid) sudoku.get(r/3, c);
					
					
					for(int c2 = 0; c2 < 3; c2++) {
						if(currentGrid.get(r%3, c2) instanceof SudokuSquare) {
							SudokuSquare currentSquare = (SudokuSquare) currentGrid.get(r%3, c2);
							sudokuPanel.add(currentSquare);
						}
					}
					
				}
			}
		}
		
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(100, 100));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		
		b = new JButton("Solve");
		buttonPanel.add(b);
		
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		container.add(sudokuPanel);
		container.add(buttonPanel);
		
		window.add(container);
		
		window.setVisible(true);
	}
	
	public boolean baseCase() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(sudoku.get(i, j) instanceof SudokuGrid) {
					SudokuGrid checkGrid = (SudokuGrid) sudoku.get(i, j);
					if(checkGrid.checkBox(-1)) {
						
					}
					else {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public boolean solve() {
		//Base case
		
		if(baseCase()) {
			return true;
		}
	
		
		//Recursive case
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				if(sudoku.get(i, j) instanceof SudokuGrid) {
					SudokuGrid checkGrid = (SudokuGrid) sudoku.get(i, j);
					
					for(int r = 0; r < 3; r++) {
						for(int c = 0; c < 3; c++) {
							
							if(checkGrid.get(r, c) instanceof SudokuSquare) {
								
								SudokuSquare currentSquare = (SudokuSquare) checkGrid.get(r, c);
								
								if(currentSquare.isDefaultNum() || currentSquare.getValue() != -1) {
									
								}
								else {
									for(int k = 1; k < 10; k++) {
										if(checkGrid.checkBox(k) && sudoku.checkCol(j, c, k) && sudoku.checkRow(i, r, k)) {
											currentSquare.setValue(k);
											
											updateBoard(i, j, r, c);
											
											if(solve()) {
												updateBoard();
												return true;
											}
											
										}
									}
									currentSquare.setValue(-1);
									return false;
								}
							}
						}
					}
					
				}
			}
		}
		
		return false;
	}
	
	public static ArrayList<ArrayList<SudokuSquare>> generateStartBoard(ArrayList<String> s){
		ArrayList<ArrayList<SudokuSquare>> startBoard = new ArrayList<ArrayList<SudokuSquare>>(9);
		
		for(int i = 0; i < s.size(); i++) {
			ArrayList<SudokuSquare> currentList = new ArrayList<SudokuSquare>();
			String currentString = s.get(i);
			
			for(int start = 0, end = 1; end <= s.size(); start++, end++) {
				String stringNum = currentString.substring(start, end);
				
				if(stringNum.equals("_")) {
					SudokuSquare currentSquare = new SudokuSquare(false, -1);
					currentList.add(currentSquare);
				}
				else {
					int num = Integer.parseInt(stringNum);
					SudokuSquare currentSquare = new SudokuSquare(true, num);
					currentList.add(currentSquare);
				}
			}
			startBoard.add(i, currentList);
		}
		
		return startBoard;
	}
	
	public void updateBoard() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(sudoku.get(i, j) instanceof SudokuGrid) {
					SudokuGrid currentGrid = (SudokuGrid) sudoku.get(i, j);
					
					for(int r = 0; r < 3; r++) {
						for(int c = 0; c < 3; c++) {
							if(currentGrid.get(r, c) instanceof SudokuSquare) {
								SudokuSquare currentSquare = (SudokuSquare) currentGrid.get(r, c);
								currentSquare.setSolved(true);
								currentSquare.repaint();
							}
						}
					}	
				}	
			}
		}
		sudokuPanel.repaint();
		buttonPanel.repaint();
		container.repaint();
	}
	
	public void updateBoard(int i, int j, int r, int c) {
		if(sudoku.get(i, j) instanceof SudokuGrid) {
			SudokuGrid currentGrid = (SudokuGrid) sudoku.get(i, j);
			if(currentGrid.get(r, c) instanceof SudokuSquare) {
				SudokuSquare currentSquare = (SudokuSquare) currentGrid.get(r, c);
				currentSquare.repaint();
			}
		}
		sudokuPanel.repaint();
		buttonPanel.repaint();
		container.repaint();
		
		try {
			Thread.sleep(250);
		}
		catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
	}
	
	public String toString() {
		String result = "";
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(sudoku.get(i, j) instanceof SudokuGrid) {
					
					SudokuGrid currentGrid = (SudokuGrid) sudoku.get(i, j);
					
					for(int r = 0; r < 3; r++) {
						for(int c = 0; c < 3; c++) {
							if(currentGrid.get(r, c) instanceof SudokuSquare) {
								SudokuSquare currentSquare = (SudokuSquare) currentGrid.get(r, c);
								result += currentSquare.toString();
							}
							result += " ";
						}
						result += "\n";
					}
				}
				result += "\n";
			}
			result += "\n";
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String box1 = "__84295_1";
		String box2 = "___7__9_8";
		String box3 = "962185_7_";
		String box4 = "__21__7__";
		String box5 = "__728_396";
		String box6 = "4___5___8";
		String box7 = "__4____1_";
		String box8 = "67_8__5__";
		String box9 = "_316___2_";
		
		/**
		String box1 = "118429511";
		String box2 = "111711918";
		String box3 = "962185171";
		String box4 = "112111711";
		String box5 = "117281396";
		String box6 = "411151118";
		String box7 = "114111111";
		String box8 = "671811511";
		String box9 = "131611121";
		**/
		
		ArrayList<String> startingBoxes = new ArrayList<String>();
		startingBoxes.add(0, box1);
		startingBoxes.add(1, box2);
		startingBoxes.add(2, box3);
		startingBoxes.add(3, box4);
		startingBoxes.add(4, box5);
		startingBoxes.add(5, box6);
		startingBoxes.add(6, box7);
		startingBoxes.add(7, box8);
		startingBoxes.add(8, box9);
		
		SudokuSolver board = new SudokuSolver(generateStartBoard(startingBoxes));
		
		board.b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(board.solve());
			}
		});
		
		board.solve();
		
	}

}
