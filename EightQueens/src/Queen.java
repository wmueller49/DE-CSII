/**
 * @author William Mueller
 * DE CSII
 * EightQueens
 *
 * Queen
 */

public class Queen {

	/**
	 * the row of the Queen
	 */
	private int row;
	
	/**
	 * the column of the Queen
	 */
	private int col;
	
	
	/**
	 * creates a Queen at row r and column c
	 * @param r the row of the Queen
	 * @param c the column of the Queen
	 */
	public Queen(int r, int c) {
		row = r;
		col = c;
	}
	
	/**
	 * sets row to value r
	 * @param r the new value of row
	 */
	public void setRow(int r) {
		row = r;
	}
	
	/**
	 * sets col to value c
	 * @param c the new value of col
	 */
	public void setCol(int c) {
		col = c;
	}
	
	/**
	 * returns which row the queen is in
	 * @return the value of row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * returns which column the queen is in
	 * @return the value of col
	 */
	public int getCol() {
		return col;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Col: " + col + "\nRow: " + row;
	}
	
}
