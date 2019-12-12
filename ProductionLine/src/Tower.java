/**
 * William Mueller
 * DE CSII
 * Tower
 */

import java.util.Stack;

public class Tower {
	
	//Field
	private Stack<Disk> towerStack;

	/**
	 * Initializes tower with an empty stack
	 */
	public Tower() {
		towerStack = new Stack<Disk>();
	}
	
	/**
	 * @return the field towerStack
	 */
	public Stack getTower() {
		return towerStack;
	}
	
	
	/**
	 * @return the Tower as a String
	 */
	public String toString() {
		String result = "";
		for(Disk d: towerStack) {
			result += d.toString();
		}
		return result;
	}
}
