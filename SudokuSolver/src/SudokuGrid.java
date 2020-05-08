/**
 * @author Owner
 *
 */

import java.util.ArrayList;

public class SudokuGrid {
	
	private ArrayList<ArrayList<Object>> grid;

	/**
	 * 
	 */
	public SudokuGrid() {
		grid = new ArrayList<ArrayList<Object>>(3);
		ArrayList row1 = new ArrayList<Object>();
		Object r1Col1 = new Object();
		Object r1Col2 = new Object();
		Object r1Col3 = new Object();
		
		row1.add(r1Col1);
		row1.add(r1Col2);
		row1.add(r1Col3);
		
		ArrayList row2 = new ArrayList<Object>();
		Object r2Col1 = new Object();
		Object r2Col2 = new Object();
		Object r2Col3 = new Object();
		
		row1.add(r2Col1);
		row1.add(r2Col2);
		row1.add(r2Col3);
		
		
		
		ArrayList row3 = new ArrayList<Object>();
		Object r3Col1 = new Object();
		Object r3Col2 = new Object();
		Object r3Col3 = new Object();
		
		row1.add(r3Col1);
		row1.add(r3Col2);
		row1.add(r3Col3);
		
		grid.add(row1);
		grid.add(row2);
		grid.add(row3);
	}
	
	public SudokuGrid(ArrayList<SudokuSquare> a) {
		grid = new ArrayList<ArrayList<Object>>(3);
		ArrayList row1 = new ArrayList<Object>();
		Object r1Col1 = new Object();
		Object r1Col2 = new Object();
		Object r1Col3 = new Object();
		
		row1.add(r1Col1);
		row1.add(r1Col2);
		row1.add(r1Col3);
		
		ArrayList row2 = new ArrayList<Object>();
		Object r2Col1 = new Object();
		Object r2Col2 = new Object();
		Object r2Col3 = new Object();
		
		row1.add(r2Col1);
		row1.add(r2Col2);
		row1.add(r2Col3);
		
		
		
		ArrayList row3 = new ArrayList<Object>();
		Object r3Col1 = new Object();
		Object r3Col2 = new Object();
		Object r3Col3 = new Object();
		
		row1.add(r3Col1);
		row1.add(r3Col2);
		row1.add(r3Col3);
		
		grid.add(row1);
		grid.add(row2);
		grid.add(row3);
		
		int count = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				grid.get(i).add(j, a.get(count));
				count++;
			}
		}
	}
	
	public void fill(int r, int c, Object obj) {
		grid.get(r).add(c, obj);
	}
	
	public Object get(int r, int c) {
		return grid.get(r).get(c);
	}
	
	public boolean checkRow(int r1, int r2, int n) {
		
		for(int c = 0; c < 3; c++) {
			if(get(r1, c) instanceof SudokuGrid) {
				SudokuGrid checkGrid = (SudokuGrid) get(r1, c);
				
				if(!(checkGrid.checkRow(r2, n))) {
					return false;
				}
			}
		}
		return true;
		
	}
	
	public boolean checkRow(int r, int n) {
		for(int c = 0; c < 3; c++) {
			if(get(r, c) instanceof SudokuSquare) {
				SudokuSquare checkSquare = (SudokuSquare) get(r,c);
				
				if(checkSquare.getValue() == n) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean checkCol(int c1, int c2, int n) {
		
		for(int r = 0; r < 3; r++) {
			if(get(r, c1) instanceof SudokuGrid) {
				SudokuGrid checkGrid = (SudokuGrid) get(r, c1);
				
				if(!(checkGrid.checkCol(c2, n))) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkCol(int c, int n) {
		for(int r = 0; r < 3; r++) {
			if(get(r, c) instanceof SudokuSquare) {
				SudokuSquare checkSquare = (SudokuSquare) get(r,c);
				
				if(checkSquare.getValue() == n) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean checkBox(int n) {
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(get(r, c) instanceof SudokuSquare) {
					SudokuSquare checkSquare = (SudokuSquare) get(r,c);
					
					if(checkSquare.getValue() == n) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public String toString() {
		String result = "";
		
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(this.get(r, c) instanceof SudokuSquare) {
					SudokuSquare currentSquare = (SudokuSquare) this.get(r, c);
					result += currentSquare.getValue() + " ";
				}
			}
			result += "\n";
		}
		
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
