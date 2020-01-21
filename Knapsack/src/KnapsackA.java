/**
 * @author William Mueller
 * DE CSII
 * Knapsack Problem A
 */

public class KnapsackA {
	
	private static int globalCount = 0;
	private static int globalMax = 0;

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
		
		if(n == 1) {
			for(int i = globalCount; i < w.length; i++) {
				if(w[i] < limit && w[i] > 1) {
					return w[i];
				}
			}
			return 0;
		}
		
		for(int j = globalCount; j < w.length; j++) {
			int current = w[j];
			
			if(current < 0) {
				current = 0;
			}
			
			int sum = knapsackSum(w, n-1, limit-current);
			int localMax = sum + current;
			
			if(localMax > globalMax && localMax <= limit) {
				globalMax = localMax;
			}
			
			if(localMax == limit) {
				return localMax;
			}
		}
		globalCount++;
		return globalMax;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[] {7, 16, 20, 8, 1, 9};
		System.out.println(knapsackSum(arr, 6, 30));
	}

}
