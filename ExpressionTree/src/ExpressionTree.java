/**
 * @author William Mueller
 *
 */

import java.util.Stack;

public class ExpressionTree extends TreeNode implements Expressions {
	
	/**
	 * this doesn't even work
	 */
	public ExpressionTree(Object initValue) {
		super(initValue);
	}
	
	public ExpressionTree(Object initValue, TreeNode initLeft, TreeNode initRight) {
		super(initValue, initLeft, initRight);
	}
	
	public static ExpressionTree buildTree(String[] exp) {
		Stack<Object> objStack = new Stack<Object>();
		int i = 0;
		
		while(i < exp.length) {
			ExpressionTree right = new ExpressionTree(null);
			ExpressionTree left = new ExpressionTree(null);
			
			if(exp[i].equals("+")) {
				if(!(objStack.empty())){
						if(objStack.peek() instanceof ExpressionTree) {
							left = (ExpressionTree) objStack.pop();
						}
						else {
							if(objStack.peek().equals("+") || objStack.peek().equals("*")) {
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
							if(objStack.peek().equals("+") || objStack.peek().equals("*")) {
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
			
			else if(exp[i].equals("*")) {
				if(!(objStack.empty())){
						if(objStack.peek() instanceof ExpressionTree) {
							left = (ExpressionTree) objStack.pop();
						}
						else {
							if(objStack.peek().equals("+") || objStack.peek().equals("*")) {
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
						if(objStack.peek().equals("+") || objStack.peek().equals("*")) {
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
		
		if(this.getValue().equals("+")) {
			return ((ExpressionTree) this.getLeft()).evalTree() + ((ExpressionTree) this.getRight()).evalTree();
		}
		else {
			return 0;
		}
	}
	
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
			else {
				numStack.push(Integer.valueOf(exp[i]));
			}
		}
		
		return numStack.pop();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] s = new String[] {"3", "4", "+", "8", "9", "+", "*"};
		ExpressionTree e = buildTree(s);
		System.out.println(e.postfixEval(s));
		System.out.println(e.toPostfixNotation());
		System.out.println(e.toInfixNotation());
		System.out.println(e.toPrefixNotation());
		System.out.println(e.evalTree());
	}

}
