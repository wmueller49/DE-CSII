/**
 * William Mueller
 * DE CSII
 * Knapsack
 * 1/23/20
 */

import java.util.ArrayList;
import java.util.List;

public class KnapsackA {
	
	/**
	 * 
	 */
	public KnapsackA() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * w contains n positive integers (n <= w.length).
	 * Returns the sum of some of these integers such that
	 * it has the largest possible value without exceeding limit.
	*/
	public static int knapsackSum(int[] w, int n, int limit) {
		if(n <= 0) {
			return w[n];
		}
		
		if(limit <= 0) { 
			return w[n];
		}
		
		int num1 = knapsackSum(w, n-1, limit);
		int num2 = knapsackSum(w, n-1, limit-w[n]);
		int num3 = w[n] + num2;
		
		if(num3 <= limit && num3 > num1) {
			return num3;
		}
		else if(num1 <= limit){
			return num1;
		}
		else {
			return 0;
		}
	}
	
	public static int knapsackSumB(int[] w, int n, int limit, List<Integer> l) {
		if(n <= 0) {
			return w[n];
		}
		
		if(limit <= 0) { 
			return w[n];
		}
		
		int num1 = knapsackSumB(w, n-1, limit, l);
		int num2 = knapsackSumB(w, n-1, limit-w[n], l);
		int num3 = w[n] + num2;
		
		if(num3 <= limit && num3 >= num1) {
			l.add(w[n]);
			return num3;
		}
		else if(num1 <= limit){
			l.add(num1);
			return num1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[] {20, 9, 6, 10, 8, 2};
		//System.out.println(knapsackSum(arr, 3, 30));
		List<Integer> list = new ArrayList<Integer>();		
		System.out.println(knapsackSumB(arr, 5, 47, list));
		System.out.println(list);
	}

}
