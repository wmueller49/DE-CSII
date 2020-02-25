/**
 * William Mueller
 * DE CSII
 * Knapsack
 * 1/23/20
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KnapsackA {
	
	private static String outputPath = "knapsack.txt";
	private static File outputFile = new File(outputPath);
	private static PrintWriter output = null;

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
			return 0;
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
	
	public static int knapsackSumB(int[] w, int n, int limit, List<Integer> lst) {
		if(n <= 0) {
			lst.add(w[n]);
			return w[n];
		}
		
		if(limit <= 0) { 
			return 0;
		}
		
		List<Integer> withoutList = new ArrayList();
		List<Integer> withList = new ArrayList();
		
		int withoutNum = knapsackSumB(w, n-1, limit, withoutList);
		int withNum = knapsackSumB(w, n-1, limit-w[n], withList);
		int withNumSum = w[n] + withNum;
		
		withList.add(w[n]);
		
		if(withNumSum <= limit && withNumSum > withoutNum) {
			lst.addAll(withList);
			return withNumSum;
		}
		else if(withoutNum <= limit && withoutNum > 0){
			lst.addAll(withoutList);
			return withoutNum;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		try {
			output = new PrintWriter(outputFile);
		}
		catch(FileNotFoundException ex) {
			
		}
		
		ArrayList<String> paths = new ArrayList();
		String inputString;
		File inputFile;
		Scanner inputScanner = null;
		
		if(args.length > 0) {
			inputString = args[0];
			inputFile = new File(inputString);
			
			try {
				inputScanner = new Scanner(inputFile);
			}
			catch(FileNotFoundException ex) {
				output.println("Invalid input file.");
				output.close();
				System.exit(1);
			}
			
			while(inputScanner.hasNextLine()) {
				paths.add(inputScanner.nextLine());
			}
		}
		else {
			System.out.println("What is the input filename: ");
			inputString = kb.next();
			inputFile = new File(inputString);
			
			try {
				inputScanner = new Scanner(inputFile);
			}
			
			catch(FileNotFoundException ex) {
				output.println("Invalid input file.");
				output.close();
				System.exit(1);
			}
			
			while(inputScanner.hasNextLine()) {
				paths.add(inputScanner.nextLine());
			}
		}
		
		File tempInputFile = null;
		Scanner tempInputScanner = null;
		ArrayList<Integer> tempList = new ArrayList();
		int[] tempArray;
		int tempLimit = 0;
		boolean catchException = true;
		
		for(String s : paths) {
			tempInputFile = new File(s);
			
			try {
				tempInputScanner = new Scanner(tempInputFile);
			}
			catch(FileNotFoundException ex){
				catchException = false;
			}
			
			if(!(tempInputScanner.hasNextLine())) {
				catchException = false;
			}
			
			if(catchException) {
				if(tempInputScanner.hasNextLine()) {
					tempLimit = Integer.parseInt(tempInputScanner.nextLine());
				}
				
				while(tempInputScanner.hasNextLine()) {
					tempList.add(Integer.parseInt(tempInputScanner.nextLine()));
				}
				
				tempArray = new int[tempList.size()];
				
				output.print(s + "	" + tempLimit + "	");
				
				for(int i = 0; i < tempArray.length; i++) {
					tempArray[i] = tempList.get(i);
					if(i == tempArray.length-1) {
						output.print(tempArray[i]);
					}
					else {
						output.print(tempArray[i] + ", ");
					}	
				}
				output.print("\n");
				output.println();
				
				tempList = new ArrayList();
				int j = knapsackSumB(tempArray, tempArray.length-1, tempLimit, tempList);
			}
			
			for(int i: tempList) {
				output.println(i + " pound watermelon");
			}
			
			if(tempList.size() > 0 && catchException) {
				output.println();
				output.println();
			}
			
			if(tempList.size() == 0 && catchException) {
				output.println("No possible watermelons");
				output.println();
				output.println();
			}
			
			tempList = new ArrayList();
			tempArray = null;
			catchException = true;
		}
		output.close();
		
		/**
		int[] arr = new int[] {18, 7, 12, 9, 13, 6};
		//System.out.println(knapsackSum(arr, 4, 22));
		List<Integer> list = new ArrayList<Integer>();		
		System.out.println(knapsackSumB(arr, 5, 5, list));
		System.out.println(list);
		**/
	}

}
