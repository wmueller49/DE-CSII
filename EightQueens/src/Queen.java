/**
 * 
 */

/**
 * @author Owner
 *
 */
public class Queen {

	private int row;
	private int col;
	
	public Queen() {
		
	}
	
	public Queen(int r, int c) {
		row = r;
		col = c;
	}
	
	public void setRow(int r) {
		row = r;
	}
	
	public void setCol(int c) {
		col = c;
	}
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public String toString() {
		return "Col: " + col + "\nRow: " + row;
	}
	
}
