/**
 * @author William Mueller
 * DE CSII
 * TicTacToeHashCode
 * 3/16/2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TTT_HC {
	
	//Field of all winners
	private Object[][] winners;
	private String currentString;

	/**
	 * default constructor instantiates winners
	 */
	public TTT_HC() {
		winners = new Object[8000][1];
	}

	/**
	 * hashcode method for this class
	 * @return the index for the current string
	 */
	public int tttHashCode() {
		int primeNum = 17;
		int result = 1;
		
		for(int i = 0; i < 9; i++) {
			result = i * result + ((int)currentString.charAt(i));
		}
		result *= primeNum;
		
		if(result > winners.length) {
			result %= winners.length;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		TTT_HC h = new TTT_HC();
		
		String input = "TicTacToeWinners.txt";
		String input2 = "TTT_Tests.txt";
		
		File inputFile = new File(input);
		File inputFile2 = new File(input2);
		
		Scanner inputScanner = null;
		Scanner inputScanner2 = null;
		
		try {
			inputScanner = new Scanner(inputFile);
			inputScanner2 = new Scanner(inputFile2);
		}
		catch(FileNotFoundException ex) {
			
		}
		
		while(inputScanner.hasNextLine()) {
			h.currentString = inputScanner.nextLine();
			int index = h.tttHashCode();
			
			if(h.winners[index][0] != null) {
				((HashMap)h.winners[index][0]).put(h.currentString, true);
			}
			else {
				h.winners[index][0] = new HashMap();
				((HashMap)h.winners[index][0]).put(h.currentString, true);
			}
		}
		
		inputScanner.close();
		
		String outputPath = "output2.txt";
		File outputFile = new File(outputPath);
		PrintWriter output = null;
		
		try {
			output = new PrintWriter(outputFile);
		}
		catch(FileNotFoundException x) {
			
		}
		
		while(inputScanner2.hasNextLine()) {
			String s = inputScanner2.nextLine();
			h.currentString = s;
			
			int index = h.tttHashCode();
			
			if(h.winners[index][0] instanceof HashMap) {
				output.println(s + ": " + ((HashMap)h.winners[index][0]).containsKey(s));
			}
			else if(h.winners[index][0] == null) {
				output.println(s + ": false");
			}
		}
		
		int itemsStored = 0;
		int numBuckets = 0;
		int numCollisions = 0;
		int maxChain = 0;
		int[] numQuarter = new int[4];
		int[] numTenth = new int[10];
		ArrayList<Integer> chains = new ArrayList<Integer>();
		
		for(int i = 0; i < h.winners.length; i++) {
			if(h.winners[i][0] instanceof HashMap) {
				itemsStored += ((HashMap)h.winners[i][0]).size();
				double place = (double) i / h.winners.length;
				
				if(place < .25) {
					numQuarter[0]++;
				}
				
				if(place < .5 && place >= .25) {
					numQuarter[1]++;
				}
				
				if(place < .75 && place >= .5) {
					numQuarter[2]++;
				}
				
				if(place >= .75) {
					numQuarter[3]++;
				}
				
				numBuckets++;
				if(((HashMap)h.winners[i][0]).size() > 1) {
					int chain = ((HashMap)h.winners[i][0]).size();
					
					if(place < .1) {
						numTenth[0] += chain;
					}
					
					if(place < .2 && place >= .1) {
						numTenth[1] += chain;
					}
					
					if(place < .3 && place >= .2) {
						numTenth[2] += chain;
					}
					
					if(place < .4 && place >= .3) {
						numTenth[3] += chain;
					}
					
					if(place < .5 && place >= .4) {
						numTenth[4] += chain;
					}
					
					if(place < .6 && place >= .5) {
						numTenth[5] += chain;
					}
					
					if(place < .7 && place >= .6) {
						numTenth[6] += chain;
					}
					
					if(place < .8 && place >= .7) {
						numTenth[7] += chain;
					}
					
					if(place < .9 && place >= .8) {
						numTenth[8] += chain;
					}
					
					if(place >= .9) {
						numTenth[9] += chain;
					}
					
					numCollisions += chain;
					chains.add(chain);
					if(chain > maxChain) {
						maxChain = chain;
					}
				}
			}	
		}
		
		int avg = 0;
		
		for(int num : chains) {
			avg += num;
		}
		
		avg = avg/chains.size();
		
		output.println();
		output.println("Size of array: " + h.winners.length);
		output.println("Load factor: " + ((double) itemsStored/numBuckets));
		output.println("Number of collisions: " + numCollisions);
		output.println("Maximum chain length: " + maxChain);
		output.println("Average chain length: " + avg);
		
		output.println();
		output.println("Number of entries in Quarter 1: " + numQuarter[0]);
		output.println("Number of entries in Quarter 2: " + numQuarter[1]);
		output.println("Number of entries in Quarter 3: " + numQuarter[2]);
		output.println("Number of entries in Quarter 4: " + numQuarter[3]);
		
		output.println();
		output.println("Number of collisions in Tenth 1: " + numTenth[0]);
		output.println("Number of collisions in Tenth 2: " + numTenth[1]);
		output.println("Number of collisions in Tenth 3: " + numTenth[2]);
		output.println("Number of collisions in Tenth 4: " + numTenth[3]);
		output.println("Number of collisions in Tenth 5: " + numTenth[4]);
		output.println("Number of collisions in Tenth 6: " + numTenth[5]);
		output.println("Number of collisions in Tenth 7: " + numTenth[6]);
		output.println("Number of collisions in Tenth 8: " + numTenth[7]);
		output.println("Number of collisions in Tenth 9: " + numTenth[8]);
		output.println("Number of collisions in Tenth 10: " + numTenth[9]);
		
		output.close();
	}

}
