/**
 * @author Owner
 *
 */
public class SudokuSquare {
	
	private boolean defaultNum;
	private int value;

	/**
	 * 
	 */
	public SudokuSquare() {
		defaultNum = false;
	}
	
	public SudokuSquare(boolean b) {
		defaultNum = b;
	}
	
	public SudokuSquare(boolean b, int v) {
		defaultNum = b;
		value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isDefaultNum() {
		return defaultNum;
	}
	
	public void setValue(int v) {
		if(defaultNum) {
			
		}
		else {
			value = v;
		}
	}
	
	public String toString() {
		return value + "";
	}
	
}
