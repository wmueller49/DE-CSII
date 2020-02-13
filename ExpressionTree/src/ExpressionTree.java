/**
 * @author William Mueller
 *
 */

import java.util.Stack;

public class ExpressionTree extends TreeNode implements Expressions {
	
	/**
	 * @param initValue
	 * uses TreeNode's Constructor to create an ExpressionTree with the initial value initValue
	 */
	public ExpressionTree(Object initValue) {
		super(initValue);
	}
	
	/**
	 * @param initValue the value of the node
	 * @param initLeft the left tree
	 * @param initRight the right tree
	 * uses TreeNode's Constructor to create an Expression tree with the initial value initValue and left and right branches
	 */
	public ExpressionTree(Object initValue, TreeNode initLeft, TreeNode initRight) {
		super(initValue, initLeft, initRight);
	}
	
	/**
	 * @param exp an array of Strings which holds the values of the tree
	 * @return an ExpressionTree built from the values in exp
	 */
	public static ExpressionTree buildTree(String[] exp) {
		Stack<Object> objStack = new Stack<Object>();
		int i = 0;
		
		while(i < exp.length) {
			ExpressionTree right = new ExpressionTree(null);
			ExpressionTree left = new ExpressionTree(null);
			
			if(exp[i].equals("+") || exp[i].equals("-") || exp[i].equals("*") || exp[i].equals("/")) {
				if(!(objStack.empty())){
						if(objStack.peek() instanceof ExpressionTree) {
							left = (ExpressionTree) objStack.pop();
						}
						else {
							if(objStack.peek().equals("+") || objStack.peek().equals("*") || objStack.peek().equals("-") || objStack.peek().equals("/")) {
								left = new ExpressionTree(objStack.pop());
							}
							else {
								left = new ExpressionTree(Integer.parseInt((String)objStack.pop()));	
							}
						}
				}
				if(!(objStack.empty())){
					if(objStack.peek() instanceof ExpressionTree) {
						right = (ExpressionTree) objStack.pop();
						objStack.push(new ExpressionTree(exp[i], left, right));
					}
					else {
							if(objStack.peek().equals("+") || objStack.peek().equals("*") || objStack.peek().equals("-") || objStack.peek().equals("/")) {
								right = new ExpressionTree(objStack.pop());
							}
							else {
								right = new ExpressionTree(Integer.parseInt((String)objStack.pop()));	
							}
						objStack.push(new ExpressionTree(exp[i], left, right));
					}
				}
				else {
					ExpressionTree tree = new ExpressionTree(exp[i]);
					tree.setLeft(left);
					objStack.push(tree);
				}
			}
			else {
				objStack.push(exp[i]);
			}
			i++;
		}
		
		return (ExpressionTree) objStack.pop();
		
	}
	
	/**
	 * evaluates the ExpressionTree's value
	 * @return an int of that value
	 */
	public int evalTree() {
		if(this == null) {
			return 0;
		}
		if(this.getValue() instanceof Integer) {
			return (int)this.getValue();
		}
		
		if(this.getValue().equals("*")) {
			return ((ExpressionTree) this.getLeft()).evalTree() * ((ExpressionTree) this.getRight()).evalTree();
		}
		
		if(this.getValue().equals("/")) {
			return ((ExpressionTree) this.getLeft()).evalTree() / ((ExpressionTree) this.getRight()).evalTree();
		}
		
		if(this.getValue().equals("-")) {
			return ((ExpressionTree) this.getLeft()).evalTree() - ((ExpressionTree) this.getRight()).evalTree();
		}
		
		if(this.getValue().equals("+")) {
			return ((ExpressionTree) this.getLeft()).evalTree() + ((ExpressionTree) this.getRight()).evalTree();
		}
		else {
			return 0;
		}
	}
	
	/**
	 * @return a String of the tree in prefix notation
	 */
	public String toPrefixNotation() {
		String s = "";
		
		if(this.getLeft() == null && this.getRight() == null) {
			return this.getValue().toString();
		}
		
		ExpressionTree right = (ExpressionTree) this.getRight();
		ExpressionTree left = (ExpressionTree) this.getLeft();
		
		s += this.getValue() + ", ";
		s += right.toPrefixNotation() + ", ";
		s += left.toPrefixNotation();
		
		return s;
	}
	
	/**
	 * @return a String of the tree in infix notation
	 */
	public String toInfixNotation() {
		String s = "";
		
		if(this.getLeft() == null && this.getRight() == null) {
			return this.getValue().toString();
		}
		
		ExpressionTree right = (ExpressionTree) this.getRight();
		ExpressionTree left = (ExpressionTree) this.getLeft();
		
		s += right.toInfixNotation() + ", ";
		s += this.getValue() + ", ";
		s += left.toInfixNotation();
		
		return s;
	}
	
	/**
	 * @return a String of the tree in postfix notation
	 */
	public String toPostfixNotation() {	
		String s = "";
		
		if(this.getLeft() == null && this.getRight() == null) {
			return this.getValue().toString();
		}
		
		ExpressionTree right = (ExpressionTree) this.getRight();
		ExpressionTree left = (ExpressionTree) this.getLeft();
		
		s += right.toPostfixNotation() + ", ";
		s += left.toPostfixNotation() + ", ";
		s += this.getValue();
		
		return s;
	}
	
	/**
	 * @param exp which holds values to be calculated
	 * @return an int of the value of the array
	 */
	public int postfixEval(String[] exp) {
		Stack<Integer> numStack = new Stack();
		
		for(int i = 0; i < exp.length; i++) {
			if(exp[i].equals("*")){
				int num1 = numStack.pop();
				int num2 = numStack.pop();
				numStack.push(num1 * num2);
			}
			else if(exp[i].equals("+")) {
				int num1 = numStack.pop();
				int num2 = numStack.pop();
				numStack.push(num1 + num2);
			}
			else if(exp[i].equals("/")){
				int num1 = numStack.pop();
				int num2 = numStack.pop();
				numStack.push(num1 / num2);
			}
			else if(exp[i].equals("-")) {
				int num1 = numStack.pop();
				int num2 = numStack.pop();
				numStack.push(num1 + num2);
			}
			else {
				numStack.push(Integer.valueOf(exp[i]));
			}
		}
		
		return numStack.pop();
	}

}
