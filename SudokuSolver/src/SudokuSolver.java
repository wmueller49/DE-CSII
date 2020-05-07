/**
 * @author Owner
 *
 */

import java.util.ArrayList;

public class SudokuSolver {

	private SudokuGrid sudoku;
	
	/**
	 * 
	 */
	public SudokuSolver(ArrayList<ArrayList<SudokuSquare>> startBoard) {
		sudoku = new SudokuGrid();
		int count = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				sudoku.fill(i, j, new SudokuGrid(startBoard.get(count)));
				count++;
			}
		}
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
											
											if(solve()) {
												return true;
											}
										}
									}
									currentSquare.setValue(-1);
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
					currentList.add(new SudokuSquare(false, -1));
				}
				else {
					int num = Integer.parseInt(stringNum);
					currentList.add(new SudokuSquare(true, num));
				}
			}
			startBoard.add(i, currentList);
		}
		
		return startBoard;
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

		System.out.println(board.toString());		
		System.out.println("----");
		System.out.println(board.solve());
		System.out.println(board.toString());
	}

}
