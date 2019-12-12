/**
 * William Mueller
 * DE CSII
 * Tower
 */

import java.util.Stack;

public class Tower {
	
	private Stack<Disk> towerStack;

	/**
	 * Initializes tower with an empty stack
	 */
	public Tower() {
		towerStack = new Stack<Disk>();
	}
	
	public Stack getTower() {
		return towerStack;
	}
	
	public String toString() {
		String result = "";
		for(Disk d: towerStack) {
			result += d.toString();
		}
		return result;
	}
}
